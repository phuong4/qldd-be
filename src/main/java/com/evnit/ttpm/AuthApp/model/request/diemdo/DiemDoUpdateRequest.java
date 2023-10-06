package com.evnit.ttpm.AuthApp.model.request.diemdo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.Date;

@ApiModel(value = "NMĐ hoặc TBA Request", description = "Thông tin NMĐ hoặc TBA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiemDoUpdateRequest {

	// private String maDiemdo;
	private String tenDiemdo;
	private String maLoaiDd;
	private String maTcDd;
	private String moTa;
	private Date ngayVh;
	// private Date ngayKt;
	private boolean itVh;
	private String orgid;
	private String ulevelid;
	private boolean trangThai;
	private BigInteger stt;
	private String maNhomDd;

	public String getTenDiemdo() {
		return tenDiemdo;
	}

	public void setTenDiemdo(String tenDiemdo) {
		this.tenDiemdo = tenDiemdo;
	}

	public String getMaLoaiDd() {
		return maLoaiDd;
	}

	public void setMaLoaiDd(String maLoaiDd) {
		this.maLoaiDd = maLoaiDd;
	}

	public String getMaTcDd() {
		return maTcDd;
	}

	public void setMaTcDd(String maTcDd) {
		this.maTcDd = maTcDd;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public Date getNgayVh() {
		return ngayVh;
	}

	public void setNgayVh(Date ngayVh) {
		this.ngayVh = ngayVh;
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

	public boolean isItVh() {
		return itVh;
	}

	public void setItVh(boolean itVh) {
		this.itVh = itVh;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	public BigInteger getStt() {
		return stt;
	}

	public void setStt(BigInteger stt) {
		this.stt = stt;
	}

	public String getMaNhomDd() {
		return maNhomDd;
	}

	public void setMaNhomDd(String maNhomDd) {
		this.maNhomDd = maNhomDd;
	}

}
