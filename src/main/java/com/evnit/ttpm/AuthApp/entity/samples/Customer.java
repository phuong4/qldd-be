/*
****************************************************
*
*			TTPM-EVNICT
*
****************************************************
 */
package com.evnit.ttpm.AuthApp.entity.samples;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "CUSTOMER")
public class Customer {

	@Id
	@Column(name = "CUSTOMERID")
	private String customerid;
	@Column(name = "NAME")
	private String name;
	@Column(name = "BIRTHDAY")
	@Temporal(TemporalType.TIMESTAMP)
	private Date birthday;
	@Column(name = "AGE")
	private Integer age;
	@Column(name = "NUM")
	private BigDecimal num;
	@Column(name = "CRDTIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date crdtime;
	@Column(name = "UDDTIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date uddtime;
	@Column(name = "USERCRID")
	private String usercrid;
	@Column(name = "USERUDID")
	private String userudid;

	@JsonProperty("STRNAME")
	@Column(name = "STRNAME") //nếu để strName thì sẽ báo lỗi thành str_Name is not valid
	private String strName;

	@JsonProperty("BOOL_OLD")
	@Column(name = "bool_old")
	private Boolean bool_old;

	public Customer() {

	}

	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public BigDecimal getNum() {
		return num;
	}

	public void setNum(BigDecimal num) {
		this.num = num;
	}

	public Date getCrdtime() {
		return crdtime;
	}

	public void setCrdtime(Date crdtime) {
		this.crdtime = crdtime;
	}

	public Date getUddtime() {
		return uddtime;
	}

	public void setUddtime(Date uddtime) {
		this.uddtime = uddtime;
	}

	public String getUsercrid() {
		return usercrid;
	}

	public void setUsercrid(String usercrid) {
		this.usercrid = usercrid;
	}

	public String getUserudid() {
		return userudid;
	}

	public void setUserudid(String userudid) {
		this.userudid = userudid;
	}

//	public String getStrName() {
//		return strName;
//	}
//
//	public void setStrName(String strName) {
//		this.strName = strName;
//	}
}
