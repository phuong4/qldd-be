package com.evnit.ttpm.AuthApp.model.request.diemdo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@ApiModel(value = "NMĐ hoặc TBA Request", description = "Thông tin NMĐ hoặc TBA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiemDoNhomUpdateRequest {
	private String tenNhomDd;
	private BigInteger stt = BigInteger.ZERO;
	private String categoryid;
	private String typeid;
	private String orgid;
	private String ulevelid;
	private String moTa;

	public String getTenNhomDd() {
		return tenNhomDd;
	}

	public void setTenNhomDd(String tenNhomDd) {
		this.tenNhomDd = tenNhomDd;
	}

	public BigInteger getStt() {
		return stt;
	}

	public void setStt(BigInteger stt) {
		this.stt = stt;
	}

	public String getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}

	public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getUlevelid() {
		return ulevelid;
	}

	public void setUlevelid(String ulevelid) {
		this.ulevelid = ulevelid;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
}
