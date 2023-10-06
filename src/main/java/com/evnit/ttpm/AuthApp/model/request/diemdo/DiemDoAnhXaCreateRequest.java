package com.evnit.ttpm.AuthApp.model.request.diemdo;

import java.util.Date;

public class DiemDoAnhXaCreateRequest {
	private String maDiemdo;
	private String orgidRef;
	private String maDiemdoRef;
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
