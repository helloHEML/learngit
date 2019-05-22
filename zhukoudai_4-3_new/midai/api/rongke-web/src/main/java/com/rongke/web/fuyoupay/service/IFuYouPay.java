package com.rongke.web.fuyoupay.service;

import com.rongke.utils.ans.R;

import java.util.Map;

public interface IFuYouPay {

    /**
     *  查询是否支持支付
     */
    R cardBinQuery(String cardNo);
    /**
     * 绑卡
     */
    R authBangdingCard1(Map<String, String> params);

    /**
     * 绑卡
     */
    R authBangdingCard2(Map<String, String> params);

    /**
     *  绑卡查询
     */
    R bindQuery();

    /**
     * 解绑
     */
    R bankUnbind(Map<String, String> params);


    /**
     * 支付
     */
    R orderPay(Map<String, String> params);

    /**
     * 打款
     */
    R paymentUser(Map<String, String> params) throws Exception;

    /**
     * 打款回调
     */
    R paymentCallbackSuces(Map<String, String> params);
}
