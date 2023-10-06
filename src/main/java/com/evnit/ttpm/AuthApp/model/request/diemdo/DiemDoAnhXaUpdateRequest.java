package com.evnit.ttpm.AuthApp.model.request.diemdo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@ApiModel(value = "NMĐ hoặc TBA Request", description = "Thông tin NMĐ hoặc TBA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiemDoAnhXaUpdateRequest {
	private String maDiemdo;
	private String orgidRef;
	private String maDiemdoRef;
	private int dateid;
	private Date ngayBd;
	private Date ngayKt;
	private String moTa;
	public String getMaDiemdo() {
		return maDiemdo;
	}
	public void setMaDiemdo(String maDiemdo) {
		this.maDiemdo = maDiemdo;
	}
	public String getOrgidRef() {
		return orgidRef;
	}
	public void setOrgidRef(String orgidRef) {
		this.orgidRef = orgidRef;
	}
	public String getMaDiemdoRef() {
		return maDiemdoRef;
	}
	public void setMaDiemdoRef(String maDiemdoRef) {
		this.maDiemdoRef = maDiemdoRef;
	}
	public int getDateid() {
		return dateid;
	}
	public void setDateid(int dateid) {
		this.dateid = dateid;
	}
	public Date getNgayBd() {
		return ngayBd;
	}
	public void setNgayBd(Date ngayBd) {
		this.ngayBd = ngayBd;
	}
	public Date getNgayKt() {
		return ngayKt;
	}
	public void setNgayKt(Date ngayKt) {
		this.ngayKt = ngayKt;
	}
	public String getMoTa() {
		return moTa;
	}
	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	
	

}
