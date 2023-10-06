package com.evnit.ttpm.AuthApp.model.request.sanluongthang;

import java.math.BigInteger;

public class BienDongThangConstraintsRequest {
	private BigInteger solan;
	private String maDiemdo;
	private BigInteger thang;
	private BigInteger nam;
	private String loai;

	public BigInteger getSolan() {
		return solan;
	}

	public void setSolan(BigInteger solan) {
		this.solan = solan;
	}

	public String getMaDiemdo() {
		return maDiemdo;
	}

	public void setMaDiemdo(String maDiemdo) {
		this.maDiemdo = maDiemdo;
	}

	public BigInteger getThang() {
		return thang;
	}

	public void setThang(BigInteger thang) {
		this.thang = thang;
	}

	public BigInteger getNam() {
		return nam;
	}

	public void setNam(BigInteger nam) {
		this.nam = nam;
	}

	public String getLoai() {
		return loai;
	}

	public void setLoai(String loai) {
		this.loai = loai;
	}
}
