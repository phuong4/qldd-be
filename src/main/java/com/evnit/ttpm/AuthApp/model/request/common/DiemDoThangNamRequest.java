package com.evnit.ttpm.AuthApp.model.request.common;

public class DiemDoThangNamRequest {
	private String ma_diemdo;
	private int thang;
	private int nam;

	public String getMa_diemdo() {
		return ma_diemdo;
	}

	public void setMa_diemdo(String ma_diemdo) {
		this.ma_diemdo = ma_diemdo;
	}

	public int getThang() {
		return thang;
	}

	public void setThang(int thang) {
		this.thang = thang;
	}

	public int getNam() {
		return nam;
	}

	public void setNam(int nam) {
		this.nam = nam;
	}
}
