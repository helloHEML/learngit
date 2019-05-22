package com.rongke.model;import com.baomidou.mybatisplus.annotations.TableField;import com.baomidou.mybatisplus.annotations.TableId;import com.baomidou.mybatisplus.annotations.TableName;import com.baomidou.mybatisplus.enums.IdType;import com.fasterxml.jackson.annotation.JsonFormat;import com.fasterxml.jackson.databind.annotation.JsonDeserialize;import com.fasterxml.jackson.databind.annotation.JsonSerialize;import com.rongke.utils.DateJsonDeserializer;import com.rongke.utils.DateJsonSerializer;import org.springframework.format.annotation.DateTimeFormat;import java.io.Serializable;import java.util.Date;/** * @BankCard * @用户银行卡(bank_card) * @version : Ver 1.0 */@TableName("bank_card")public class BankCard implements Serializable {	@TableField(exist = false)	private static final long serialVersionUID = 1L;	/**	 * @备注:	 * @字段:id BIGINT(19)	 */	@TableId(type = IdType.AUTO)	@JsonFormat(shape = JsonFormat.Shape.STRING)	private java.lang.Long id;	/**	 *	 * @备注: 用户	 * @字段:user_id BIGINT(19)	 */	@JsonFormat(shape = JsonFormat.Shape.STRING)	private java.lang.Long userId;	@JsonFormat(shape = JsonFormat.Shape.STRING)	private java.lang.Long protocolId;	/**	 * @备注:身份证号	 * @字段:id_card_no VARCHAR(45)	 */	private java.lang.String idCardNo;	/**	 * @备注:银行卡号	 * @字段:bank_card_no VARCHAR(45)	 */	private java.lang.String bankCardNo;	private java.lang.String status;	/**	 * @备注:预留手机	 * @字段:phone VARCHAR(12)	 */	private java.lang.String phone;	/**	 * @备注:银行卡名称	 * @字段:bank_card_name VARCHAR(45)	 */	private java.lang.String bankCardName;	/**	 * @备注:银行卡机构编码	 * @字段:bank_card_name VARCHAR(45)	 */	private java.lang.String bankCardCode;	private java.lang.String name;	private java.lang.String protocol;	/**	 * @备注:	 * @字段:gmt_datetime DATETIME(19)	 */	@JsonSerialize(using=DateJsonSerializer.class)	@JsonDeserialize(using=DateJsonDeserializer.class)	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )	private java.util.Date gmtDatetime;	/**	 * @备注:	 * @字段:upt_datetime DATETIME(19)	 */	@JsonSerialize(using=DateJsonSerializer.class)	@JsonDeserialize(using=DateJsonDeserializer.class)	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )	private java.util.Date uptDatetime ;	public void setId(java.lang.Long id) {		this.id = id;	}	public java.lang.Long getId() {		return this.id;	}	public void setUserId(java.lang.Long userId) {		this.userId = userId;	}	public java.lang.Long getUserId() {		return this.userId;	}	public void setIdCardNo(java.lang.String idCardNo) {		this.idCardNo = idCardNo;	}	public java.lang.String getIdCardNo() {		return this.idCardNo;	}	public void setBankCardNo(java.lang.String bankCardNo) {		this.bankCardNo = bankCardNo;	}	public java.lang.String getBankCardNo() {		return this.bankCardNo;	}	public String getStatus() {		return status;	}	public void setStatus(String status) {		this.status = status;	}	public void setPhone(java.lang.String phone) {		this.phone = phone;	}	public java.lang.String getPhone() {		return this.phone;	}	public Date getGmtDatetime() {		return gmtDatetime;	}	public void setGmtDatetime(Date gmtDatetime) {		this.gmtDatetime = gmtDatetime;	}	public Date getUptDatetime() {		return uptDatetime;	}	public void setUptDatetime(Date uptDatetime) {		this.uptDatetime = uptDatetime;	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	public Long getProtocolId() {		return protocolId;	}	public void setProtocolId(Long protocolId) {		this.protocolId = protocolId;	}	public void setBankCardName(java.lang.String bankCardName) {		this.bankCardName = bankCardName;	}	public java.lang.String getBankCardName() {		return this.bankCardName;	}	public String getBankCardCode() {		return bankCardCode;	}	public void setBankCardCode(String bankCardCode) {		this.bankCardCode = bankCardCode;	}	public String getProtocol() {		return protocol;	}	public void setProtocol(String protocol) {		this.protocol = protocol;	}}