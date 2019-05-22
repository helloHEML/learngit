package com.rongke.model;import com.baomidou.mybatisplus.annotations.TableField;import com.baomidou.mybatisplus.annotations.TableId;import com.baomidou.mybatisplus.annotations.TableName;import com.baomidou.mybatisplus.enums.IdType;import com.fasterxml.jackson.annotation.JsonFormat;import java.io.Serializable;/** * @Protocol * @协议(protocol) * @version : Ver 1.0 */@TableName("protocol")public class Protocol implements Serializable {	@TableField(exist = false)	private static final long serialVersionUID = 1L;	/**	 * @备注:	 * @字段:id BIGINT(19)	 */	@TableId(type = IdType.AUTO)	@JsonFormat(shape = JsonFormat.Shape.STRING)	private java.lang.Long id;	/**	 * @备注:协议类型	 * @字段:type VARCHAR(45)	 */	private java.lang.String type;	/**	 * @备注:状态	 * @字段:status VARCHAR(45)	 */	private java.lang.String status;	/**	 * @备注:内容	 * @字段:content TEXT(65535)	 */	private java.lang.String content;	/**	 * @备注:图片	 * @字段:pic_url VARCHAR(100)	 */	private java.lang.String picUrl;	/**	 * @备注:	 * @字段:gmt_datetime DATETIME(19)	 *///	@JsonSerialize(using=DateJsonSerializer.class)//	@JsonDeserialize(using=DateJsonDeserializer.class)//	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )	private java.util.Date gmtDatetime;	/**	 * @备注:	 * @字段:upt_datetime DATETIME(19)	 *///	@JsonSerialize(using=DateJsonSerializer.class)//	@JsonDeserialize(using=DateJsonDeserializer.class)//	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )	private java.util.Date uptDatetime ;	public Protocol(){	}	public Protocol(		java.lang.Long id	){		this.id = id;	}	public void setId(java.lang.Long id) {		this.id = id;	}	public java.lang.Long getId() {		return this.id;	}	public void setType(java.lang.String type) {		this.type = type;	}	public java.lang.String getType() {		return this.type;	}	public void setStatus(java.lang.String status) {		this.status = status;	}	public java.lang.String getStatus() {		return this.status;	}	public void setContent(java.lang.String content) {		this.content = content;	}	public java.lang.String getContent() {		return this.content;	}	public void setPicUrl(java.lang.String picUrl) {		this.picUrl = picUrl;	}	public java.lang.String getPicUrl() {		return this.picUrl;	}	public void setGmtDatetime(java.util.Date gmtDatetime) {		this.gmtDatetime = gmtDatetime;	}	public java.util.Date getGmtDatetime() {		return this.gmtDatetime;	}	public void setUptDatetime(java.util.Date uptDatetime) {		this.uptDatetime = uptDatetime;	}	public java.util.Date getUptDatetime() {		return this.uptDatetime;	}}