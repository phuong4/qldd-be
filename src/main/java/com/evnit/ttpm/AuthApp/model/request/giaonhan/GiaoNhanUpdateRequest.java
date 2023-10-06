package com.evnit.ttpm.AuthApp.model.request.giaonhan;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GiaoNhanUpdateRequest {

	private String maGn;
	private String tenGn;
	private String kieu;
	private String loai;
	private BigInteger stt = BigInteger.ZERO;
	private String moTa;
	private boolean trangThai;

	private boolean Kwh = false;
	private boolean Kvarh = false;

	private boolean Thang = false;
	private boolean Ngay = false;
	private boolean Invoice = false;
	private boolean Pmis = false;
	private boolean Sign = false;
	private boolean Confirm = false;
	public String getMaGn() {
		return maGn;
	}
	public void setMaGn(String maGn) {
		this.maGn = maGn;
	}
	public String getTenGn() {
		return tenGn;
	}
	public void setTenGn(String tenGn) {
		this.tenGn = tenGn;
	}
	public String getKieu() {
		return kieu;
	}
	public void setKieu(String kieu) {
		this.kieu = kieu;
	}
	public String getLoai() {
		return loai;
	}
	public void setLoai(String loai) {
		this.loai = loai;
	}
	public BigInteger getStt() {
		return stt;
	}
	public void setStt(BigInteger stt) {
		this.stt = stt;
	}
	public String getMoTa() {
		return moTa;
	}
	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	public boolean isTrangThai() {
		return trangThai;
	}
	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}
	public boolean isKwh() {
		return Kwh;
	}
	public void setKwh(boolean kwh) {
		Kwh = kwh;
	}
	public boolean isKvarh() {
		return Kvarh;
	}
	public void setKvarh(boolean kvarh) {
		Kvarh = kvarh;
	}
	public boolean isThang() {
		return Thang;
	}
	public void setThang(boolean thang) {
		Thang = thang;
	}
	public boolean isNgay() {
		return Ngay;
	}
	public void setNgay(boolean ngay) {
		Ngay = ngay;
	}
	public boolean isInvoice() {
		return Invoice;
	}
	public void setInvoice(boolean invoice) {
		Invoice = invoice;
	}
	public boolean isPmis() {
		return Pmis;
	}
	public void setPmis(boolean pmis) {
		Pmis = pmis;
	}
	public boolean isSign() {
		return Sign;
	}
	public void setSign(boolean sign) {
		Sign = sign;
	}
	public boolean isConfirm() {
		return Confirm;
	}
	public void setConfirm(boolean confirm) {
		Confirm = confirm;
	}
	
	

}
