package com.rongke.model;import com.fasterxml.jackson.annotation.JsonFormat;import com.fasterxml.jackson.databind.annotation.JsonDeserialize;import com.fasterxml.jackson.databind.annotation.JsonSerialize;import com.rongke.utils.DateJsonDeserializer;import com.rongke.utils.DateJsonSerializer;import org.springframework.format.annotation.DateTimeFormat;import com.baomidou.mybatisplus.annotations.TableField;import com.baomidou.mybatisplus.annotations.TableId;import com.baomidou.mybatisplus.annotations.TableName;import com.baomidou.mybatisplus.enums.IdType;import java.io.Serializable;/** * @UserMessage * @用户消息(user_message) * @version : Ver 1.0 */@TableName("user_message")public class UserMessage implements Serializable {	@TableField(exist = false)	private static final long serialVersionUID = 1L;	/**	 * @备注:	 * @字段:id BIGINT(19)	 */	@TableId(type = IdType.AUTO)	@JsonFormat(shape = JsonFormat.Shape.STRING)	private java.lang.Long id;	/**	 * @备注:	 * @字段:user_id BIGINT(19)	 */	@JsonFormat(shape = JsonFormat.Shape.STRING)	private java.lang.Long userId;	/**	 * 备注:	 */	@TableField(exist = false)	private User user;	/**	 * @备注:消息类型	 * @字段:type VARCHAR(45)	 */	private java.lang.String type;	/**	 * @备注:状态	 * @字段:status VARCHAR(45)	 */	private java.lang.String status;	/**	 * @备注:消息内容	 * @字段:content VARCHAR(45)	 */	private java.lang.String content;	/**	 * @备注:标题	 * @字段:title VARCHAR(45)	 */	private java.lang.String title;	/**	 * @备注:	 * @字段:gmt_datetime DATETIME(19)	 */	@JsonSerialize(using=DateJsonSerializer.class)	@JsonDeserialize(using=DateJsonDeserializer.class)	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )	private java.util.Date gmtDatetime;	/**	 * @备注:	 * @字段:upt_datetime DATETIME(19)	 */	@JsonSerialize(using=DateJsonSerializer.class)	@JsonDeserialize(using=DateJsonDeserializer.class)	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )	private java.util.Date uptDatetime ;	public UserMessage(){	}	public UserMessage(		java.lang.Long id	){		this.id = id;	}	public void setId(java.lang.Long id) {		this.id = id;	}	public java.lang.Long getId() {		return this.id;	}	public void setUserId(java.lang.Long userId) {			this.userId = userId;			}	public java.lang.Long getUserId() {			return this.userId;	}	public void setUser(User user) {		this.user = user;	}	public User getUser() {		return this.user;	}	public void setType(java.lang.String type) {		this.type = type;	}	public java.lang.String getType() {		return this.type;	}	public void setStatus(java.lang.String status) {		this.status = status;	}	public java.lang.String getStatus() {		return this.status;	}	public void setContent(java.lang.String content) {		this.content = content;	}	public java.lang.String getContent() {		return this.content;	}	public void setTitle(java.lang.String title) {		this.title = title;	}	public java.lang.String getTitle() {		return this.title;	}	public void setGmtDatetime(java.util.Date gmtDatetime) {		this.gmtDatetime = gmtDatetime;	}	public java.util.Date getGmtDatetime() {		return this.gmtDatetime;	}	public void setUptDatetime(java.util.Date uptDatetime) {		this.uptDatetime = uptDatetime;	}	public java.util.Date getUptDatetime() {		return this.uptDatetime;	}}