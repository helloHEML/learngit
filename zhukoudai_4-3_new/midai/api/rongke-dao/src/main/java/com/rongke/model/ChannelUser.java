package com.rongke.model;import com.alibaba.fastjson.annotation.JSONField;import com.baomidou.mybatisplus.annotations.TableField;import com.baomidou.mybatisplus.annotations.TableId;import com.baomidou.mybatisplus.annotations.TableName;import com.baomidou.mybatisplus.enums.IdType;import com.fasterxml.jackson.annotation.JsonFormat;import com.fasterxml.jackson.annotation.JsonInclude;import java.io.Serializable;import java.util.Date;@TableName("channel_user")public class ChannelUser implements Serializable {	@TableField(exist = false)	private static final long serialVersionUID = 1L;	/**	 * 主键	 */	@TableId(type = IdType.AUTO)	@JsonFormat(shape = JsonFormat.Shape.STRING)	private Long id;	/**	 *负责用户名	 */	private String name;	/**	 * 登陆账号	 */	private String loginName;	/**	 * 密码	 */	private String password;	/**	 * 登陆TOken	 */	private String token;	/**	 * 联系方式	 */	private String sex;	/**	 * 联系方式	 */	private String phone;	/**	 * QQ	 */	private String qq;	/**	 * 邮箱	 */	private String mail;	/**	 * 负责渠道	 */	private String channelId;	/**	 * 创建时间	 */	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")	private Date createTime;	/**	 * 创建时间	 */	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")	private Date updateTime;	/**	 * 状态	 */	private Integer status;	public Long getId() {		return id;	}	public void setId(Long id) {		this.id = id;	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	public String getLoginName() {		return loginName;	}	public void setLoginName(String loginName) {		this.loginName = loginName;	}	public String getPassword() {		return password;	}	public void setPassword(String password) {		this.password = password;	}	public String getToken() {		return token;	}	public void setToken(String token) {		this.token = token;	}	public String getSex() {		return sex;	}	public void setSex(String sex) {		this.sex = sex;	}	public String getPhone() {		return phone;	}	public void setPhone(String phone) {		this.phone = phone;	}	public String getQq() {		return qq;	}	public void setQq(String qq) {		this.qq = qq;	}	public String getMail() {		return mail;	}	public void setMail(String mail) {		this.mail = mail;	}	public String getChannelId() {		return channelId;	}	public void setChannelId(String channelId) {		this.channelId = channelId;	}	public Date getCreateTime() {		return createTime;	}	public void setCreateTime(Date createTime) {		this.createTime = createTime;	}	public Date getUpdateTime() {		return updateTime;	}	public void setUpdateTime(Date updateTime) {		this.updateTime = updateTime;	}	public Integer getStatus() {		return status;	}	public void setStatus(Integer status) {		this.status = status;	}}