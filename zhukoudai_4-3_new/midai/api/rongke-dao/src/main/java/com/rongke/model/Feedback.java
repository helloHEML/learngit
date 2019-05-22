package com.rongke.model;import com.fasterxml.jackson.annotation.JsonFormat;import com.fasterxml.jackson.databind.annotation.JsonDeserialize;import com.fasterxml.jackson.databind.annotation.JsonSerialize;import com.rongke.utils.DateJsonDeserializer;import com.rongke.utils.DateJsonSerializer;import org.springframework.format.annotation.DateTimeFormat;import com.baomidou.mybatisplus.annotations.TableField;import com.baomidou.mybatisplus.annotations.TableId;import com.baomidou.mybatisplus.annotations.TableName;import com.baomidou.mybatisplus.enums.IdType;import java.io.Serializable;/** * @Feedback * @(feedback) * @version : Ver 1.0 */@TableName("feedback")public class Feedback implements Serializable {	@TableField(exist = false)	private static final long serialVersionUID = 1L;	/**	 * @备注:	 * @字段:id BIGINT(19)	 */	@TableId(type = IdType.AUTO)	@JsonFormat(shape = JsonFormat.Shape.STRING)	private Long id;	/**	 * @备注:	 * @字段:user_id BIGINT(19)	 */	@JsonFormat(shape = JsonFormat.Shape.STRING)	private Long userId;	/**	 * 备注:	 */	@TableField(exist = false)	private User user;	/**	 * @备注:反馈内容	 * @字段:content VARCHAR(2550)	 */	private String content;	/**	 * @备注:预留手机号	 * @字段:phone VARCHAR(13)	 */	private String phone;	/**	 * @备注:图片地址	 * @字段:img_url VARCHAR(2550)	 */	private String imgUrl;	private String status;	/**	 * @备注:	 * @字段:gmt_datetime DATETIME(19)	 */	@JsonSerialize(using=DateJsonSerializer.class)	@JsonDeserialize(using=DateJsonDeserializer.class)	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )	private java.util.Date gmtDatetime;	/**	 * @备注:	 * @字段:upt_datetime DATETIME(19)	 */	@JsonSerialize(using=DateJsonSerializer.class)	@JsonDeserialize(using=DateJsonDeserializer.class)	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )	private java.util.Date uptDatetime ;	public Feedback(){	}	public Feedback(		Long id	){		this.id = id;	}	public void setId(Long id) {		this.id = id;	}	public Long getId() {		return this.id;	}	public void setUserId(Long userId) {			this.userId = userId;			}	public Long getUserId() {			return this.userId;	}	public void setUser(User user) {		this.user = user;	}	public User getUser() {		return this.user;	}	public void setContent(String content) {		this.content = content;	}	public String getContent() {		return this.content;	}	public String getStatus() {		return status;	}	public void setStatus(String status) {		this.status = status;	}	public void setPhone(String phone) {		this.phone = phone;	}	public String getPhone() {		return this.phone;	}	public void setImgUrl(String imgUrl) {		this.imgUrl = imgUrl;	}	public String getImgUrl() {		return this.imgUrl;	}	public void setGmtDatetime(java.util.Date gmtDatetime) {		this.gmtDatetime = gmtDatetime;	}	public java.util.Date getGmtDatetime() {		return this.gmtDatetime;	}	public void setUptDatetime(java.util.Date uptDatetime) {		this.uptDatetime = uptDatetime;	}	public java.util.Date getUptDatetime() {		return this.uptDatetime;	}}