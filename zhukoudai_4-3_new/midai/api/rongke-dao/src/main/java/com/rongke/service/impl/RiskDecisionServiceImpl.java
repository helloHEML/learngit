package com.rongke.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.rongke.model.*;
import com.rongke.service.*;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("RiskDecisionService-wed")
public class RiskDecisionServiceImpl implements RiskDecisionService {

    @Autowired
    private DecisionRuleSetService decisionRuleSetService;

    @Autowired
    private DecisionRuleService decisionRuleService;

    @Autowired
    private TongDunMessageService tongDunMessageService;

    @Autowired
    private TianzhenMessageService tianzhenMessageService;

    @Autowired
    private DecisionTableExplainService decisionTableExplainService;
    //获得ruleset，然后判断


    @Override
    public Map<String, String> getUserRiskStatus(Long userId) {
        Map<String, String> hr;
        UserRiskData userRiskData = getUserRiskData(userId);//根据id获取数据源(数据库中同盾的与探针的数据)
        List<DecisionRuleSet> decisionRuleSets = decisionRuleSetService.getDecisionRuleSets();//获取正在使用的规则集
        if (decisionRuleSets == null){
            return null;
        }
        for (DecisionRuleSet decisionRuleSet: decisionRuleSets){
            List<DecisionRule> decisionRules =  decisionRuleService.getDecisionRules(decisionRuleSet.getId());
//            通过规则集的id获取规则集中的所有规则
            if (decisionRules == null){
                continue;
            }
            for (DecisionRule decisionRule: decisionRules){
                hr = getUserRiskDecisionResult(decisionRule.getDecisionRule(), userRiskData);
//                判断每一条决策 返回1表示通过
                if("-1".equals(hr.get("code")) || "0". equals(hr.get("code"))){
                    System.out.println(hr);
                    return hr;
                }
            }
        }

        return setHr(1);
    }

    //规则集 用开闭区间表示 设一个最大值
    boolean baseRangeCondition(double value, double min, boolean isMinClose, double max, boolean isMaxClose)
    {
        if (isMinClose && isMaxClose) {
            return value >= min && value <= max;
        }
        else if (isMinClose && !isMaxClose) {
            return value >= min && value < max;
        }
        else if (!isMinClose && isMaxClose){
            return value > min && value <= max;
        }
        else {//(!isMinClose && !isMaxClose)
            return value > min && value < max;
        }
    }


    //风控模型有很多，包括运营商数据，入网年龄等。通过userId获取数据魔盒中的所有数据并转换为JSONObject格式
    UserRiskData getUserRiskData(Long userId) {
        UserRiskData userRiskData = new UserRiskData();
//        获取同盾的风控信息
        TongDunMessage tongDunMessage = tongDunMessageService.selectOne(new EntityWrapper<TongDunMessage>().eq("user_id",userId));
        userRiskData.setTongdunMessage(tongDunMessage);

        TianZhenMessage tianZhenMessage = tianzhenMessageService.selectOne(new EntityWrapper<TianZhenMessage>().eq("user_id",userId).orderBy("create_time",false));
        userRiskData.setTianzhenData(tianZhenMessage);
        return userRiskData;
    }

    int getCondRangeValue(String conditionValue, double[] rangeValue)
    {
        String condValueItem[] = conditionValue.split(",");
        if(condValueItem.length !=2 ){
            return -1;
        }
        double min = Double.valueOf(condValueItem[0]);
        double max = Double.valueOf(condValueItem[1]);
        if (min >= max){
            return -1;
        }

        rangeValue[0] = min;
        rangeValue[1] = max;
        return 1;
    }

    //对一条决策的判断
    //返回-1.表示参数出错，1表示条件成立， 0表示条件不成立。
    //modulename.fieldname.>=.1
    //modulename.fieldname.=.hangzhou
    Map<String, String> setHr(int code, String status, String type, String name)
    {
        Map<String, String> hr = new HashMap<String, String>();
        hr.put("code", String.valueOf(code));
//        hr.put("status", "Assert:" + "DecisionModule:"+type+ ",DecisionField:"+name + status);
        hr.put("status",type+"`"+name+status);
        return hr;
    }

    Map<String, String> setHr(int code, String status)
    {
        Map<String, String> hr = new HashMap<String, String>();
        hr.put("code", String.valueOf(code));
        hr.put("status", "Assert:" + status);
        return hr;
    }

    Map<String, String> setHr(int code)
    {
        Map<String, String> hr = new HashMap<String, String>();
        hr.put("code", String.valueOf(code));
        hr.put("status", "");
        System.out.println(hr);
        return hr;
    }

    /** 判断每一条规则 */
    Map<String, String> getOneUserRiskDecision(String decisionRule, UserRiskData userRiskData)
    {
        Map<String, String> hr = new HashMap<String, String>();
        String[] items  = decisionRule.split("\\`");
//        以"`"分割每一条规则获取其中的字段
        if (items.length != 4) {
            return setHr(-1, "length!=4");
        }
        String name = StringUtils.strip(items[0])+StringUtils.strip(items[1]);

        DecisionTableExplain decisionTableExplain =
                decisionTableExplainService.selectOne(new EntityWrapper<DecisionTableExplain>().eq("china_name",name));
        if(decisionTableExplain == null){
            return null;
        }
        String type = decisionTableExplain.getTableType();
        name = decisionTableExplain.getEnglishName();
        String condition = StringUtils.strip(items[2]);
        String conditionValue = StringUtils.strip(items[3]);//如果是区间值，则用逗号分隔:1,2
        String userRiskValue =  String.valueOf(userRiskData.getRiskValue(type, name));
         if ("".equals(userRiskValue) || userRiskValue == null){
             userRiskValue="0";
         }
        //等号可以是数值也可以是字符，比如归属地 =福建、=江西
        //如果用户的值符合表达式则返回1
        if (condition.equals("=")) {
            if(userRiskValue.equals(conditionValue)){
                return setHr(1);
            }else{
                    return setHr(0, userRiskValue + condition + conditionValue, items[0], items[1]);
            }
        }
        else if (condition.equals(">=")){
            if(baseRangeCondition(Double.valueOf(userRiskValue), Double.valueOf(conditionValue), true, Double.MAX_VALUE, false)){
                return setHr(1);
            }
            else{
                return setHr(0, userRiskValue + condition + conditionValue, items[0], items[1]);
            }
        }
        else if (condition.equals(">")){
            if(baseRangeCondition(Double.valueOf(userRiskValue), Double.valueOf(conditionValue), false, Double.MAX_VALUE, false)){
                return setHr(1);
            }
            else{
                return setHr(0, userRiskValue + condition + conditionValue, items[0], items[1]);

            }
        }
        else if (condition.equals("<=")){
//            if(baseRangeCondition(Double.valueOf(userRiskValue), Double.MIN_VALUE, false, Double.valueOf(conditionValue), true)){
                if(baseRangeCondition(Double.valueOf(userRiskValue), 0.0, true, Double.valueOf(conditionValue), true)){
                return setHr(1);
            }
            else{
                return setHr(0, userRiskValue + condition + conditionValue, items[0], items[1]);

            }
        }
        else if (condition.equals("<")){
//            if(baseRangeCondition(Double.valueOf(userRiskValue), Double.MIN_VALUE, false, Double.valueOf(conditionValue), false)){
                if(baseRangeCondition(Double.valueOf(userRiskValue), 0.0, true, Double.valueOf(conditionValue), false)){
                return setHr(1);
            }
            else{
                return setHr(0, userRiskValue + condition + conditionValue, items[0], items[1]);
            }
        }
        else{
            double[] rangeValue = new double[2];
            if(-1 == getCondRangeValue(conditionValue, rangeValue)) {
                return setHr(-1, "min, max:" + conditionValue, items[0], items[1]);
            }
            Map map=new HashMap();
            map.put("[]", Arrays.asList(true,true));
            map.put("[)", Arrays.asList(true,false));
            map.put("(]", Arrays.asList(false,true));
            map.put("()", Arrays.asList(false,false));
            List temp = (List)map.get(condition);
            Boolean minClose = Boolean.parseBoolean(String.valueOf(temp.get(0)));
            Boolean maxClose = Boolean.parseBoolean(String.valueOf(temp.get(1)));

            if( baseRangeCondition(Double.valueOf(userRiskValue), rangeValue[0], minClose, rangeValue[1], maxClose)){
                return setHr(1);
            }
            else{
                return setHr(0, userRiskValue + condition + conditionValue, items[0], items[1]);
            }
        }

    }



    //一个多条决策中，都是且  或者 都是或
    Map<String, String> getUserRiskDecisionResult(String decisionRules, UserRiskData userRiskData)
    {
        Map<String, String> hr;

        if (decisionRules.contains("&")) {
            //多条,一条不成立，就返回0
            String[] items = decisionRules.split("&");
            for (String decisionRule: items){
                hr = getOneUserRiskDecision(decisionRule, userRiskData);

                if("-1".equals(hr.get("code")) || "0". equals(hr.get("code"))){
                    return hr;
                }
            }
            return setHr(1);
        }
        else if ( decisionRules.contains("|")){
            //多条,所有决策都不成立，就返回0
            String[] items = decisionRules.split("\\|");
            String hrs = "";
            for (String decisionRule: items){
                hr = getOneUserRiskDecision(decisionRule, userRiskData);
                if("-1".equals(hr.get("code"))){
                    return hr;
                }
                else if ("1". equals(hr.get("code"))){
                    return setHr(1);
                }
                hrs += hr;
            }
            return setHr(0, hrs);
        }
        else{
            //只有一条
            hr = getOneUserRiskDecision(decisionRules, userRiskData);
            return hr;
        }
    }




}
