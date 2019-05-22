package com.rongke.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

@TableName("tb_constant")
public class TConstant implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


    @TableId(type = IdType.AUTO)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    /**访问地址*/
    private String accessUrl;
    /**短信 签名*/
    private String smsSignId;
    /**短信 KEY*/
    private String smsAccessKey;
    /**短信 密钥*/
    private String smsSecret;
    /**短信 验证码模板*/
    private String smsCodeId;
    /**短信 基础通知模板ID*/
    private String smsNoticeId;
    /**短信 今日待付通知模板ID*/
    private String smsTodayId;
    /**短信 逾期通知模板ID*/
    private String smsOverdueId;


    /**魔盒 code*/
    private String moheCode;
    /**魔盒 moheKey*/
    private String moheKey;

    /**云慧眼 SecurityKey*/
    private String yhySecurityKey;
    /**云慧眼 yhyPubKey*/
    private String yhyPubKey;

    /**天贝 token*/
    private String tianbeiToken;

    /**探针 tzAppId*/
    private String tzAppId;
    /**探针 tzTokenId*/
    private String tzTokenId;


    /**阿里 KeyId*/
    private String aliyunAccessKeyId;
    /**阿里 AccessKeySecret*/
    private String aliyunAccessKeySecret;
    /**阿里 绑定域名*/
    private String aliyunDomain;
    /**阿里 路径前缀*/
    private String aliyunPrefix;
    /**阿里 地域节点*/
    private String aliyunEndPoint;
    /**阿里 Bucket*/
    private String aliyunBucketName;

    /**富有 商户号*/
    private String fuyouMchntCd;
    /**富有 快捷支付Key*/
    private String fuyouMchntKey;
    /**富有 代付key*/
    private String fuyouMchntUserPay;
    /**富有 服务器IP*/
    private String fuyouIpadder;
    /**富有 支付回调地址*/
    private String fuyouCallback;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccessUrl() {
        return accessUrl;
    }

    public void setAccessUrl(String accessUrl) {
        this.accessUrl = accessUrl;
    }

    public String getSmsSignId() {
        return smsSignId;
    }

    public void setSmsSignId(String smsSignId) {
        this.smsSignId = smsSignId;
    }

    public String getSmsAccessKey() {
        return smsAccessKey;
    }

    public void setSmsAccessKey(String smsAccessKey) {
        this.smsAccessKey = smsAccessKey;
    }

    public String getSmsSecret() {
        return smsSecret;
    }

    public void setSmsSecret(String smsSecret) {
        this.smsSecret = smsSecret;
    }

    public String getSmsCodeId() {
        return smsCodeId;
    }

    public void setSmsCodeId(String smsCodeId) {
        this.smsCodeId = smsCodeId;
    }

    public String getSmsNoticeId() {
        return smsNoticeId;
    }

    public void setSmsNoticeId(String smsNoticeId) {
        this.smsNoticeId = smsNoticeId;
    }

    public String getSmsTodayId() {
        return smsTodayId;
    }

    public void setSmsTodayId(String smsTodayId) {
        this.smsTodayId = smsTodayId;
    }

    public String getSmsOverdueId() {
        return smsOverdueId;
    }

    public void setSmsOverdueId(String smsOverdueId) {
        this.smsOverdueId = smsOverdueId;
    }

    public String getMoheCode() {
        return moheCode;
    }

    public void setMoheCode(String moheCode) {
        this.moheCode = moheCode;
    }

    public String getMoheKey() {
        return moheKey;
    }

    public void setMoheKey(String moheKey) {
        this.moheKey = moheKey;
    }

    public String getYhySecurityKey() {
        return yhySecurityKey;
    }

    public void setYhySecurityKey(String yhySecurityKey) {
        this.yhySecurityKey = yhySecurityKey;
    }

    public String getYhyPubKey() {
        return yhyPubKey;
    }

    public void setYhyPubKey(String yhyPubKey) {
        this.yhyPubKey = yhyPubKey;
    }

    public String getTianbeiToken() {
        return tianbeiToken;
    }

    public void setTianbeiToken(String tianbeiToken) {
        this.tianbeiToken = tianbeiToken;
    }

    public String getTzAppId() {
        return tzAppId;
    }

    public void setTzAppId(String tzAppId) {
        this.tzAppId = tzAppId;
    }

    public String getTzTokenId() {
        return tzTokenId;
    }

    public void setTzTokenId(String tzTokenId) {
        this.tzTokenId = tzTokenId;
    }

    public String getAliyunAccessKeyId() {
        return aliyunAccessKeyId;
    }

    public void setAliyunAccessKeyId(String aliyunAccessKeyId) {
        this.aliyunAccessKeyId = aliyunAccessKeyId;
    }

    public String getAliyunAccessKeySecret() {
        return aliyunAccessKeySecret;
    }

    public void setAliyunAccessKeySecret(String aliyunAccessKeySecret) {
        this.aliyunAccessKeySecret = aliyunAccessKeySecret;
    }

    public String getAliyunDomain() {
        return aliyunDomain;
    }

    public void setAliyunDomain(String aliyunDomain) {
        this.aliyunDomain = aliyunDomain;
    }

    public String getAliyunPrefix() {
        return aliyunPrefix;
    }

    public void setAliyunPrefix(String aliyunPrefix) {
        this.aliyunPrefix = aliyunPrefix;
    }

    public String getAliyunEndPoint() {
        return aliyunEndPoint;
    }

    public void setAliyunEndPoint(String aliyunEndPoint) {
        this.aliyunEndPoint = aliyunEndPoint;
    }

    public String getAliyunBucketName() {
        return aliyunBucketName;
    }

    public void setAliyunBucketName(String aliyunBucketName) {
        this.aliyunBucketName = aliyunBucketName;
    }

    public String getFuyouMchntCd() {
        return fuyouMchntCd;
    }

    public void setFuyouMchntCd(String fuyouMchntCd) {
        this.fuyouMchntCd = fuyouMchntCd;
    }

    public String getFuyouMchntKey() {
        return fuyouMchntKey;
    }

    public void setFuyouMchntKey(String fuyouMchntKey) {
        this.fuyouMchntKey = fuyouMchntKey;
    }

    public String getFuyouMchntUserPay() {
        return fuyouMchntUserPay;
    }

    public void setFuyouMchntUserPay(String fuyouMchntUserPay) {
        this.fuyouMchntUserPay = fuyouMchntUserPay;
    }

    public String getFuyouIpadder() {
        return fuyouIpadder;
    }

    public void setFuyouIpadder(String fuyouIpadder) {
        this.fuyouIpadder = fuyouIpadder;
    }

    public String getFuyouCallback() {
        return fuyouCallback;
    }

    public void setFuyouCallback(String fuyouCallback) {
        this.fuyouCallback = fuyouCallback;
    }
}
