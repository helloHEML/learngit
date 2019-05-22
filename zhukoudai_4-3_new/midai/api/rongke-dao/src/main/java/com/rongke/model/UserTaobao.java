package com.rongke.model;import com.baomidou.mybatisplus.annotations.TableField;import com.baomidou.mybatisplus.annotations.TableId;import com.baomidou.mybatisplus.annotations.TableName;import com.baomidou.mybatisplus.enums.IdType;import com.fasterxml.jackson.annotation.JsonFormat;import com.fasterxml.jackson.databind.annotation.JsonDeserialize;import com.fasterxml.jackson.databind.annotation.JsonSerialize;import com.rongke.utils.DateJsonDeserializer;import com.rongke.utils.DateJsonSerializer;import org.springframework.format.annotation.DateTimeFormat;import java.io.Serializable;import java.util.Date;/** * @UserTaobao * @(user_taobao) * @version : Ver 1.0 */@TableName("user_taobao")public class UserTaobao implements Serializable {	@TableField(exist = false)	private static final long serialVersionUID = 1L;	/**	 * @备注:	 * @字段:id BIGINT(19)	 */	@TableId(type = IdType.AUTO)	@JsonFormat(shape = JsonFormat.Shape.STRING)	private Long id;	@JsonFormat(shape = JsonFormat.Shape.STRING)	private Long userId;    private String name;    private String  gender;    private String mobile;    private  String nickName;     private  String taskId;     private String startus;	public String getStartus() {		return startus;	}	public void setStartus(String startus) {		this.startus = startus;	}	public String getTaskId() {		return taskId;	}	public void setTaskId(String taskId) {		this.taskId = taskId;	}	/**	 * @备注:	 * @字段:gmt_datetime DATETIME(19)	 */	@JsonSerialize(using=DateJsonSerializer.class)	@JsonDeserialize(using=DateJsonDeserializer.class)	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )	private java.util.Date gmtDatetime;	/**	 * @备注:	 * @字段:upt_datetime DATETIME(19)	 */	@JsonSerialize(using=DateJsonSerializer.class)	@JsonDeserialize(using=DateJsonDeserializer.class)	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )	private java.util.Date uptDatetime ;	public Long getId() {		return id;	}	public void setId(Long id) {		this.id = id;	}	public Long getUserId() {		return userId;	}	public void setUserId(Long userId) {		this.userId = userId;	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	public String getGender() {		return gender;	}	public void setGender(String gender) {		this.gender = gender;	}	public String getMobile() {		return mobile;	}	public void setMobile(String mobile) {		this.mobile = mobile;	}	public String getNickName() {		return nickName;	}	public void setNickName(String nickName) {		this.nickName = nickName;	}	public Date getGmtDatetime() {		return gmtDatetime;	}	public void setGmtDatetime(Date gmtDatetime) {		this.gmtDatetime = gmtDatetime;	}	public Date getUptDatetime() {		return uptDatetime;	}	public void setUptDatetime(Date uptDatetime) {		this.uptDatetime = uptDatetime;	}}