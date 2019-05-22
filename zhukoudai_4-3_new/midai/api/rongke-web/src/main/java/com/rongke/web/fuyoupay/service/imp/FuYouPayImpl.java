package com.rongke.web.fuyoupay.service.imp;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.collect.Maps;
import com.rongke.enums.*;
import com.rongke.model.*;
import com.rongke.service.*;
import com.rongke.utils.*;
import com.rongke.utils.ans.R;
import com.rongke.web.config.ConfigUtil;
import com.rongke.web.fuyou.FuYouUtils;
import com.rongke.web.fuyoupay.service.IFuYouPay;
import com.rongke.web.modules.sys.service.SysGlobalssService;
import com.rongke.web.saas.RSSmsUtils;
import com.rongke.web.sms.SmsUtils;
import net.sf.json.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

@Service
public class FuYouPayImpl implements IFuYouPay {
    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private UserService userService;//用户操作
    @Autowired
    private UserIdentityService userIdentityService;
    @Autowired
    private BankCardService bankCardService;//银行卡
    @Autowired
    private AuthenticationService authenticationService;//认证
    @Autowired
    private EvaluationService evaluationService;
    @Autowired
    private OrderService orderService;//订单
    @Autowired
    private RepayRecordService repayRecordService;//付款记录
    @Autowired
    private ParamSettingService paramSettingService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private TrackLogService trackLogService;//跟踪信息
    @Autowired
    private SysGlobalssService sysGlobalService;//斗鱼
    @Autowired
    private SmsUtils smsUtil;

    /**
     *  验证支付
     */
    public R cardBinQuery(String cardNo) {
        return FuYouUtils.cardBinQuery(cardNo);
    }

    /**
     * 绑卡1
     */
    public R authBangdingCard1(Map<String,String> params) {
        Map<String,String> submit = Maps.newHashMap();
        User user = userService.selectCurrentUser();
        if(!user.getPhone().equals(params.get("mobile"))){
            return R.error("请绑定与登陆号码一致的银行卡");
        }
        UserIdentity Idcard = userIdentityService.selectOne(new EntityWrapper<UserIdentity>().eq("user_id", user.getId()));
        if(Idcard==null){
            return R.error("请进行实名认证");
        }
        submit.put("card",params.get("card"));
        submit.put("mobile",params.get("mobile"));
        submit.put("userId",user.getPhone());
        submit.put("account",Idcard.getUserName());
        submit.put("IdCard",Idcard.getIdentityNum());
        return FuYouUtils.bindMsg(submit);
    }

    /**
     * 绑卡2
     */
    public R authBangdingCard2(Map<String,String> params) {
        Map<String,String> submit = Maps.newHashMap();

        User user = userService.selectCurrentUser();
        UserIdentity Idcard = userIdentityService.selectOne(new EntityWrapper<UserIdentity>().eq("user_id", user.getId()));
        if(Idcard==null){
            return R.error("请进行实名认证");
        }
        submit.put("card",params.get("card"));
        submit.put("mobile",params.get("mobile"));
        submit.put("ssn",params.get("ssn"));
        submit.put("Vcode",params.get("Vcode"));
        submit.put("userId",user.getPhone());
        submit.put("account",Idcard.getUserName());
        submit.put("IdCard", Idcard.getIdentityNum());
        R r = FuYouUtils.bindCommit(submit);
        if(RRUtils.assertCode(r,"0")){
            //判断是否存在银行卡
            BankCard bankCard = new BankCard();
            bankCard.setStatus("废弃");
            bankCardService.update(bankCard,new EntityWrapper<BankCard>().eq("user_id",user.getId()));
            bankCard.setName(Idcard.getUserName());
            bankCard.setBankCardCode(params.get("InsCd"));
            bankCard.setBankCardName(params.get("bankName"));
            bankCard.setBankCardNo(params.get("card"));
            bankCard.setPhone(params.get("mobile"));
            bankCard.setUserId(user.getId());
            bankCard.setStatus("使用");
            bankCard.setGmtDatetime(new Date());
            bankCard.setProtocol(String.valueOf(r.get("xe")));
            bankCard.setIdCardNo(Idcard.getIdentityNum());
            bankCardService.insert(bankCard);
            Authentication auth = authenticationService.selectOne(new EntityWrapper<Authentication>().eq("user_id", user.getId()));
            if(auth!=null){
                if(!"1".equals(auth.getBankCardAuth())){
                    auth.setBankCardAuth("1");
                    authenticationService.updateById(auth);
                }
            }
            return R.ok((String) r.get("msg"));
        }else{
            return r;
        }
    }

    /**
     * 绑卡查询
     */
    public R bindQuery(){
        User user = userService.selectCurrentUser();
        return FuYouUtils.bindQuery(String.valueOf(user.getPhone()));
    }

    /**
     * 解绑
     */
    public R bankUnbind(Map<String,String> params) {
        Map<String,String> submit = Maps.newHashMap();
        User user = userService.selectCurrentUser();
        BankCard bankCard = bankCardService.selectOne(new EntityWrapper<BankCard>()
                .eq("user_id", user.getId())
                .eq("bank_card_no",params.get("card"))
                .orderDesc(Arrays.asList("gmt_datetime"))
        );
        if(bankCard==null){
            return R.error("银行卡列表为空");
        }
        R unbind = FuYouUtils.unbind(String.valueOf(user.getPhone()),bankCard.getProtocol());
        if(RRUtils.assertCode(unbind,"0")){
            bankCardService.deleteById(bankCard.getId());
            return R.ok(String.valueOf(unbind.get("msg")));
        }else {
            return R.error(String.valueOf(unbind.get("msg")));
        }
    }

    /**
     * 支付
     */
    public R orderPay(Map<String,String> params){
        User user = userService.selectCurrentUser();//当前登入用户信息
        Map<String,String> submit = Maps.newHashMap();
        if(params.get("orderId")==null){
            return R.error("订单ID为空");
        }
        BigDecimal money = new BigDecimal("0");
        BankCard bankCard = bankCardService.selectOne(new EntityWrapper<BankCard>()
                .eq("user_id", user.getId())
                .eq("status","使用")
        );
        if(bankCard==null){
            return R.error("请绑定银行卡");
        }
        //付款
        Order  order = orderService.selectById(params.get("orderId"));
        if(order==null){
            return R.error("订单不存在");
        }
        Evaluation evaluation = evaluationService.selectById(order.getEvaluationId());

        if(evaluation==null){
            return R.error("申请单不存在");
        }

        if(OrderStatusEnum.OVER.getTypeName().equals(order.getStatus())){
            return R.error("订单已完结");
        }
        Map<String,String> ct = Maps.newHashMap();
        submit.put("rem1",String.valueOf(order.getId()));//获取订单的id
        //用 用户的当前还款时间减去申请单上的应还时间(按年月日减，时分秒包含在内)
        long days = DateUtils.getDistanceDays(DateUtils.dateSimple(order.getUptDatetime()),DateUtils.dateSimple(new Date()));
        if(params.get("type")==null||"-1".equals(params.get("type"))){//完结订单
            BigDecimal j = evaluation.getRentMoney();
            if(days>1){//逾期
                ParamSetting paramSetting = paramSettingService.selectById(evaluation.getParamSettingId());
                if(paramSetting==null){
                    paramSetting = new ParamSetting();
                    paramSetting.setOvertimeMoney("0.06");
                }
                BigDecimal yuqi = new BigDecimal(String.valueOf(days - 1)).multiply(new BigDecimal(paramSetting.getOvertimeMoney())).multiply(evaluation.getRentMoney());//逾期费
//                逾期天数*逾期费率*租金(打款的金额)
                money = money.add(yuqi).add(evaluation.getRentMoney()).multiply(new BigDecimal("100")).setScale(0,BigDecimal.ROUND_HALF_UP);
//                总金额为租金加逾期金额
                submit.put("money",money.stripTrailingZeros().toPlainString());
                ct.put("repayType","4");
                ct.put("money",yuqi.add(evaluation.getRentMoney()).stripTrailingZeros().toPlainString());
            } else {
                money = evaluation.getRentMoney().multiply(new BigDecimal("100"));
                submit.put("money",money.stripTrailingZeros().toPlainString());

                ct.put("repayType","3");
                ct.put("money",evaluation.getRentMoney().stripTrailingZeros().toPlainString());
            }
        }else{//续期
            /*BigDecimal rzj = order.getDayRent().multiply(new BigDecimal("7"));
            if(days>1){//逾期
                ParamSetting paramSetting = paramSettingService.selectById(evaluation.getParamSettingId());
                if(paramSetting==null){
                    paramSetting = new ParamSetting();
                    paramSetting.setOvertimeMoney("0.06");
                }
                BigDecimal yuqi = new BigDecimal(String.valueOf(days - 1)).multiply(new BigDecimal(paramSetting.getOvertimeMoney())).multiply(evaluation.getRentMoney());//逾期费
                money = yuqi.add(rzj).multiply(new BigDecimal("100")).setScale(0,BigDecimal.ROUND_HALF_UP);
                ct.put("repayType","2");
                ct.put("money",yuqi.add(rzj).stripTrailingZeros().toPlainString());
            }else{
                money = rzj.multiply(new BigDecimal("100")).setScale(0,BigDecimal.ROUND_HALF_UP);
                ct.put("repayType","1");
                ct.put("money",rzj.stripTrailingZeros().toPlainString());
            }
            submit.put("money",money.stripTrailingZeros().toPlainString());*/
            return R.error("无法续期！！！");
        }
        submit.put("rem2", JSON.toJSONString(ct));
        submit.put("userId",String.valueOf(user.getPhone()));
        submit.put("protocol",bankCard.getProtocol());

        R r = FuYouUtils.order(submit);
        log.info("付款返回："+r.toString());
        r.put("result","0");//清除返回结果

        RepayRecord repay = new RepayRecord();//追加用户的还款记录信息
        repay.setUserId(user.getId());
        repay.setOrderId(order.getId());
        repay.setMoney(new BigDecimal(String.valueOf(ct.get("money"))));
        repay.setPayno(String.valueOf(r.get("payOrder")));
        repay.setFuyouno(String.valueOf(r.get("fyOrderId")));
        repay.setPayType(6);//初始状态为 富有支付
        repay.setGmtDatetime(new Date());//付款金额

        if(RRUtils.assertCode(r,"0")){
            if("3".equals(ct.get("repayType"))||"4".equals(ct.get("repayType"))){//完结
                //订单
                order.setMoney(new BigDecimal(String.valueOf(ct.get("money"))));
                order.setUptDatetime(new Date());
                order.setStatus("已完结");
                orderService.updateById(order);
                //申请单
                evaluation.setStatus(OrderStatusEnum.OVER.getTypeName());
                evaluationService.updateById(evaluation);

                //付款记录
                repay.setDays(0);
                repay.setType(Integer.valueOf(ct.get("repayType")));

                repayRecordService.insert(repay);
                user.setFrequency(user.getFrequency()+1);
                userService.updateById(user);
                TrackLog log = new TrackLog();
                log.setTitle("【申请单】 已完结");
                log.setType(1);
                log.setEvaluationId(evaluation.getId());
                log.seteStatus(evaluation.getStatus());
                log.setoStatus(order.getStatus());
                log.setCreateTime(new Date());
                log.setStatus(1);
                trackLogService.insert(log);
            }else{
                //订单
                if("1".equals(ct.get("repayType"))){
                    order.setUptDatetime(DateUtils.dayAdd(6,order.getUptDatetime()));
                    repay.setDays(7);
                }else{
                    order.setUptDatetime(DateUtils.dayAdd(Integer.valueOf(String.valueOf(days-1+7)),order.getUptDatetime()));
                    repay.setDays(Integer.valueOf(String.valueOf(days-1+6)));
                }
                orderService.updateById(order);
                //付款记录
                repay.setType(Integer.valueOf(ct.get("repayType")));
                repayRecordService.insert(repay);
            }
            return R.ok("支付成功");
        }else{
            return r;
        }
    }
    /**
     * 打款
     */
    public R paymentUser(Map<String,String> params) throws Exception{
        Admin admin = adminService.findLoginUser();
        String password = params.get("password");
        SysGlobal key = sysGlobalService.key(GlobalEnums.PAYPASSWORD.getValue());
        if(!key.getSysValues().equals(DigestUtils.md5Hex(password))){
            return R.error("打款密码不正确");
        }
        if(StringUtils.isBlank(params.get("evaluationId"))){
            return R.error("申请单ID为空");
        }
        if(StringUtils.isBlank(params.get("money"))){
            return R.error("打款金额不能为空");
        }
        Evaluation evaluation = evaluationService.selectById(params.get("evaluationId"));

        if (EvaluationEnum.WAITAUDIT.getTypeName().equals(evaluation.getStatus())) {
            if (evaluation == null) {
                return R.error("申请单不存在");
            }
            Order order = orderService.selectOne(new EntityWrapper<Order>()
                    .eq("evaluation_id", evaluation.getId())
            );
            if (order != null) {
                return R.error("该申请单已生成订单");
            }
            //打款金额
            EntityWrapper<ParamSetting> psew = new EntityWrapper<>();
            psew.eq("status", StatusEnum.USED.getTypeName());
            ParamSetting paramSetting = paramSettingService.selectOne(psew);
            String payMoney = new String(ConfigUtil.decode(params.get("money")));//获取解密后的打款金额
            //评估费
            BigDecimal assessMoney = new BigDecimal(paramSetting.getAssessMoney());
            //扣掉7天租金
            BigDecimal rentDayPortion = new BigDecimal(paramSetting.getRentDayMoney()).multiply(new BigDecimal(payMoney)).multiply(new BigDecimal(7));//日租金比例/*paramSettingService.days*/
            //保证金
            BigDecimal deposit = new BigDecimal(paramSetting.getCashPledge()).multiply(new BigDecimal(payMoney));
            BigDecimal paysMoney = new BigDecimal(payMoney).subtract(rentDayPortion).subtract(deposit).subtract(assessMoney).setScale(2, BigDecimal.ROUND_HALF_UP);
            if (paysMoney.compareTo(new BigDecimal("5")) == -1) {
                return R.error("实际打款不能小于5元");
            }
            BankCard bankCard = bankCardService.selectOne(new EntityWrapper<BankCard>()
                    .eq("user_id", evaluation.getUserId())
                    .eq("status", StatusEnum.USED.getTypeName())
            );
            if (bankCard == null) {
                return R.error("银行卡不存在");
            }


            Map<String, String> sumbit = Maps.newHashMap();
            sumbit.put("card", bankCard.getBankCardNo());
            sumbit.put("account", bankCard.getName());
            sumbit.put("money", (paysMoney.multiply(new BigDecimal("100")).setScale(0, BigDecimal.ROUND_HALF_UP)).stripTrailingZeros().toPlainString());
            sumbit.put("meno", "备注");
            R r = FuYouUtils.payUser(sumbit);
            log.info("打款返回：" + r.toString());
            User user = userService.selectById(evaluation.getUserId());
            //短信推送
            try {
                if (user != null) {
                    //您的借款申请已打款
                    Boolean sendsms = RSSmsUtils.sendsms(user.getPhone(), "", "42345");
                    System.out.println(sendsms);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (RRUtils.assertCode(r, "0")) {
                evaluation.setEvaluationPrice(new BigDecimal(payMoney)); //修改评估金额
                evaluation.setRentMoney(new BigDecimal(payMoney));//设置回租价值
                evaluation.setPayMoney(paysMoney); //应打金额
                evaluation.setBatchNo(String.valueOf(r.get("orderId")));
                evaluation.setAdminId(admin.getId());//打款人
                evaluation.setFinalMoney(paysMoney);
                evaluation.setStatus(StatusEnum.WAITMONEY.getTypeName());
                evaluationService.updateById(evaluation);
                //日租金
                BigDecimal dayRent = evaluation.getEvaluationPrice().multiply(new BigDecimal(paramSetting.getRentDayMoney()));
                //生成订单
                order = new Order();
                order.setEvaluationId(evaluation.getId());
                order.setStatus(OrderStatusEnum.NOT_ARRIVAL.getTypeName());
                order.setDayRent(dayRent);
                order.setOrderNumber(OrderUtils.getOrderNo());
                order.setGmtDatetime(new Date());
                order.setDepositStatus(DepositStatusEnum.NOTREFUND.getDesc());
                order.setHmTime(user.getFrequency());
                orderService.insert(order);

                TrackLog log = new TrackLog();
                log.setTitle("【申请单】 打款");
                log.setType(1);
                log.setAdminId(admin.getId().longValue());
                log.setEvaluationId(evaluation.getId());
                log.seteStatus(EvaluationEnum.PASS.getTypeName());
                log.setoStatus(OrderStatusEnum.NOT_APPLYING.getTypeName());
                log.setCreateTime(new Date());
                log.setStatus(1);
                trackLogService.insert(log);
                return R.ok();
            } else {
                return r;
            }
        }else{
            return R.error("申请单不符合打款条件，打款失败");
        }
    }

    /**
     * 打款回调
     */
    public R paymentCallbackSuces(Map<String,String> params){
        log.error("打款回调信息"+ JSONObject.fromObject(params).toString());
        if(params.get("state")!=null&&params.get("state").equals("1")){
            String orderPay = params.get("orderno");
            Evaluation evaluation = evaluationService.selectOne(new EntityWrapper<Evaluation>().eq("batch_no", orderPay));
            if(evaluation!=null){
                if("待出款".equals(evaluation.getStatus())){
                    evaluation.setStatus(StatusEnum.PASS.getTypeName());
                    evaluation.setUptDatetime(new Date());
                    evaluationService.updateById(evaluation);
                    Order order = orderService.selectOne(new EntityWrapper<Order>().eq("evaluation_id",evaluation.getId()));
                    order.setUptDatetime(DateUtils.dayAdd(5,new Date()));
                    order.setStatus(OrderStatusEnum.NOT_APPLYING.getTypeName());
                    orderService.updateById(order);
                }
            }
        }
        return R.ok();
    }

    public static void main(String[] args) throws Exception {
        String asdas = "æ¸ é\u0081\u0093èµ\u0084é\u0087\u0091å\u0088°è´¦å·²å¤\u008Dæ ¸,äº¤æ\u0098\u0093å·²å\u008F\u0091é";
        System.out.println(new String(asdas.getBytes("ISO-8859-1"),"UTF-8"));
    }
}
