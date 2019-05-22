package com.rongke.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

@TableName("channel_sum")
public class ChannelSum implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    /** 渠道商id */
    private Long cid;

    /** 渠道商名称 */
    private String name;

    /** 注册人数 */
    private String zc;

    /** 认证人数 */
    private String rzrs;

    /** 认证失败 */
    private String rzsb;

    /** 认证未完成 */
    private String rzwwc;

    /** 认证成功 */
    private String rzcg;

    /** 未认证 */
    private String wrz;

    /** 申请人数 */
    private String sqrs;

    /** 申请人数次数 */
    private String sqrss;

    /** 通过人数 */
    private String tgrs;

    /** 坏账人数(一个月之内为还款) */
    private String hzrs;

    /** 逾期人数 */
    private String yqrs;

    /** 放款人数 */
    private String fkrs;

    /** 放款总次数 */
    private String fkrss;

     /** 认证率 */
    @TableField(exist = false)
    private String rzl;

    /** 申请率 */
    @TableField(exist = false)
    private String sql;

    /** 通过率 */
    @TableField(exist = false)
    private String tgl;

    /** 放款率 */
    @TableField(exist = false)
    private String fkl;

    /** 累计坏账率 */
    @TableField(exist = false)
    private String hzl;

    /** 累计逾期率 */
    @TableField(exist = false)
    private String yql;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZc() {
        return zc;
    }

    public void setZc(String zc) {
        this.zc = zc;
    }

    public String getRzrs() {
        return rzrs;
    }

    public void setRzrs(String rzrs) {
        this.rzrs = rzrs;
    }

    public String getRzsb() {
        return rzsb;
    }

    public void setRzsb(String rzsb) {
        this.rzsb = rzsb;
    }

    public String getRzwwc() {
        return rzwwc;
    }

    public void setRzwwc(String rzwwc) {
        this.rzwwc = rzwwc;
    }

    public String getRzcg() {
        return rzcg;
    }

    public void setRzcg(String rzcg) {
        this.rzcg = rzcg;
    }

    public String getWrz() {
        return wrz;
    }

    public void setWrz(String wrz) {
        this.wrz = wrz;
    }

    public String getSqrs() {
        return sqrs;
    }

    public void setSqrs(String sqrs) {
        this.sqrs = sqrs;
    }

    public String getSqrss() {
        return sqrss;
    }

    public void setSqrss(String sqrss) {
        this.sqrss = sqrss;
    }

    public String getTgrs() {
        return tgrs;
    }

    public void setTgrs(String tgrs) {
        this.tgrs = tgrs;
    }

    public String getHzrs() {
        return hzrs;
    }

    public void setHzrs(String hzrs) {
        this.hzrs = hzrs;
    }

    public String getYqrs() {
        return yqrs;
    }

    public void setYqrs(String yqrs) {
        this.yqrs = yqrs;
    }

    public String getFkrs() {
        return fkrs;
    }

    public void setFkrs(String fkrs) {
        this.fkrs = fkrs;
    }

    public String getFkrss() {
        return fkrss;
    }

    public void setFkrss(String fkrss) {
        this.fkrss = fkrss;
    }

    public String getRzl() {
        return rzl;
    }

    public void setRzl(String rzl) {
        this.rzl = rzl;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public String getFkl() {
        return fkl;
    }

    public void setFkl(String fkl) {
        this.fkl = fkl;
    }

    public String getHzl() {
        return hzl;
    }

    public void setHzl(String hzl) {
        this.hzl = hzl;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getYql() {
        return yql;
    }

    public void setYql(String yql) {
        this.yql = yql;
    }
}
