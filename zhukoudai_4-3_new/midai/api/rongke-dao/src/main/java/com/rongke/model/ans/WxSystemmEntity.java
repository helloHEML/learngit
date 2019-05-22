package com.rongke.model.ans;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 微信服务费
 * 
 * @author Ans
 * @email 867917319@qq.com
 * @date 2018-08-24 09:21:04
 */
@TableName("wx_systemm")
public class WxSystemmEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 微信 借条服务费 id
	 */
	@TableId(type = IdType.AUTO)
	private Long id;
	/**
	 * 金额
	 */
	private BigDecimal money;
	/**
	 * 1服务费
	 */
	private Integer type;
	/**
	 * 类型描述
	 */
	private String typeRemark;
	/**
	 * 创建时间
	 */
	@TableField(value = "create_time",fill = FieldFill.INSERT)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date createTime;
	/**
	 * 修改时间
	 */
	@TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date updateTime;
	/**
	 * 状态
	 */
	private String status;

	/**
	 * 设置：微信 借条服务费 id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：微信 借条服务费 id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：金额
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	/**
	 * 获取：金额
	 */
	public BigDecimal getMoney() {
		return money;
	}
	/**
	 * 设置：1服务费
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：1服务费
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：类型描述
	 */
	public void setTypeRemark(String typeRemark) {
		this.typeRemark = typeRemark;
	}
	/**
	 * 获取：类型描述
	 */
	public String getTypeRemark() {
		return typeRemark;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：修改时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：状态
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：状态
	 */
	public String getStatus() {
		return status;
	}
}
