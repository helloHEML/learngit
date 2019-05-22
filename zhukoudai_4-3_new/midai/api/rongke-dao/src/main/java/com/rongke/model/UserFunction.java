package com.rongke.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

@TableName("user_function")
public class UserFunction {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * @备注:用户表id
     */
    @TableId(type = IdType.AUTO)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    /**
     * @备注:用户ID
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;

    /**
     * @备注:理想金额
     */
    private String contentmentMoney;

    /**
     * @备注:默认银行卡
     */
    private String bankDefault;

    /**
     * @备注:当前登陆App版本
     */
    private String appVersion;

    /**
     * @备注:创建时间
     */
    private Date createTime;

    /**
     * @备注:修改时间
     */
    private Date updateTime;

    /**
     * @备注:状态
     */
    private Integer status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContentmentMoney() {
        return contentmentMoney;
    }

    public void setContentmentMoney(String contentmentMoney) {
        this.contentmentMoney = contentmentMoney;
    }

    public String getBankDefault() {
        return bankDefault;
    }

    public void setBankDefault(String bankDefault) {
        this.bankDefault = bankDefault;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
