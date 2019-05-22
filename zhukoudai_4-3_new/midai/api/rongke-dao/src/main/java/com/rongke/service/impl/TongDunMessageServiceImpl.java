package com.rongke.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.rongke.mapper.TongDunMessageMapper;
import com.rongke.model.TongDunMessage;
import com.rongke.service.TongDunMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 同盾保存的数据
 */
@Service
public class TongDunMessageServiceImpl extends ServiceImpl<TongDunMessageMapper, TongDunMessage> implements TongDunMessageService {

    @Autowired
    private TongDunMessageMapper tongDunMessageMapper;


    public Long showTDMUserId(Long userId) {
        return tongDunMessageMapper.selectTDMUserId(userId);
    }

    /**
     * 截取部分的魔盒字典数据
     */
    public String cutOutData(String data){
        if(data==null){
            return null;
        }
        JSONObject jsonObject1 = JSONObject.parseObject(data);
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("report_info",jsonObject1.getJSONObject("report_info"));
        jsonObject2.put("data_completeness",jsonObject1.getJSONObject("data_completeness"));
        jsonObject2.put("user_info",jsonObject1.getJSONObject("user_info"));
        jsonObject2.put("mobile_info",jsonObject1.getJSONObject("mobile_info"));
        jsonObject2.put("info_match",jsonObject1.getJSONObject("info_match"));
        jsonObject2.put("info_check",jsonObject1.getJSONObject("info_check"));
        jsonObject2.put("behavior_score",jsonObject1.getJSONObject("behavior_score"));
        jsonObject2.put("contact_blacklist_analysis",jsonObject1.getJSONObject("contact_blacklist_analysis"));
        jsonObject2.put("contact_manyheads_analysis",jsonObject1.getJSONObject("contact_manyheads_analysis"));
        jsonObject2.put("contact_creditscore_analysis",jsonObject1.getJSONObject("contact_creditscore_analysis"));
        jsonObject2.put("contact_gang_fraud_analysis",jsonObject1.getJSONObject("contact_gang_fraud_analysis"));
        jsonObject2.put("behavior_analysis",jsonObject1.getJSONObject("behavior_analysis"));
        jsonObject2.put("all_contact_stats",jsonObject1.getJSONObject("all_contact_stats"));
        jsonObject2.put("all_contact_stats_per_month",jsonObject1.getJSONArray("all_contact_stats_per_month"));
        jsonObject2.put("risk_contact_stats",jsonObject1.getJSONArray("risk_contact_stats"));
        jsonObject2.put("risk_contact_detail",jsonObject1.getJSONArray("risk_contact_detail"));
        jsonObject2.put("finance_contact_stats",jsonObject1.getJSONArray("finance_contact_stats"));
        jsonObject2.put("finance_contact_detail",jsonObject1.getJSONArray("finance_contact_detail"));
        jsonObject2.put("contact_suspect_collection_analysis",jsonObject1.getJSONArray("contact_suspect_collection_analysis"));
        jsonObject2.put("service_contact_stats",jsonObject1.getJSONArray("service_contact_stats"));
        jsonObject2.put("carrier_consumption_stats",jsonObject1.getJSONObject("carrier_consumption_stats"));
        jsonObject2.put("call_area_stats_per_city",jsonObject1.getJSONArray("call_area_stats_per_city"));
        jsonObject2.put("active_silence_stats",jsonObject1.getJSONObject("active_silence_stats"));
        jsonObject2.put("call_duration_stats_2hour",jsonObject1.getJSONObject("call_duration_stats_2hour"));
        jsonObject2.put("travel_track_analysis_per_city",jsonObject1.getJSONArray("travel_track_analysis_per_city"));
        jsonObject.put("data",jsonObject2);
        System.out.println("**********************************魔盒字典的长度为:"+jsonObject.toString().length());
        return jsonObject.toString();
    }

}
