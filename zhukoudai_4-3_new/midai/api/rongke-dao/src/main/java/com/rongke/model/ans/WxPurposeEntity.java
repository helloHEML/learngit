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
 * 微信 ——  借条 借款用途
 * 
 * @author Ans
 * @email 867917319@qq.com
 * @date 2018-08-26 17:51:56
 */
@TableName("wx_purpose")
public class WxPurposeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 借条用途 id 
	 */
	@TableId(type = IdType.AUTO)
	private Long id;
	/**
	 * 借款用途
	 */
	private String title;
	/**
	 * 父id 
	 */
	private Long pid;
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
	private Integer status;

	/**
	 * 设置：借条用途 id 
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：借条用途 id 
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：借款用途
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：借款用途
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：父id 
	 */
	public void setPid(Long pid) {
		this.pid = pid;
	}
	/**
	 * 获取：父id 
	 */
	public Long getPid() {
		return pid;
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
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态
	 */
	public Integer getStatus() {
		return status;
	}
}
