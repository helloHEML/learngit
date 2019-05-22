package com.rongke.model.ans;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 微信 好友
 * 
 * @author Ans
 * @email 867917319@qq.com
 * @date 2018-08-26 17:51:56
 */
@TableName("wx_friends")
public class WxFriendsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 微信 好友id
	 */
	@TableId(type = IdType.AUTO)
	private Long id;
	/**
	 * 添加人
	 */
	private Long accountId;
	/**
	 * 目标id
	 */
	private Long targetId;
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
	 * 设置：微信 好友id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：微信 好友id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：添加人
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	/**
	 * 获取：添加人
	 */
	public Long getAccountId() {
		return accountId;
	}
	/**
	 * 设置：目标id
	 */
	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}
	/**
	 * 获取：目标id
	 */
	public Long getTargetId() {
		return targetId;
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
