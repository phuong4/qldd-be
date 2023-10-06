package com.evnit.ttpm.AuthApp.model.request.giaonhan;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@ApiModel(value = "Phuong thuc Attr Update Request", description = "Thông tin điểm đo thuộc phương thức")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhuongThucAttrUpdateRequest {
	private String maPthuc;
	private String maDiemdo;
	private String mota;
	private int giao;
	private int nhan;
	private BigDecimal hsn_his_g;
	private BigDecimal hsn_his_n;
	private BigDecimal hsn_load_g;
	private BigDecimal hsn_load_n;
	public String getMaPthuc() {
		return maPthuc;
	}
	public void setMaPthuc(String maPthuc) {
		this.maPthuc = maPthuc;
	}
	public String getMaDiemdo() {
		return maDiemdo;
	}
	public void setMaDiemdo(String maDiemdo) {
		this.maDiemdo = maDiemdo;
	}
	public String getMota() {
		return mota;
	}
	public void setMota(String mota) {
		this.mota = mota;
	}
	public int getGiao() {
		return giao;
	}
	public void setGiao(int giao) {
		this.giao = giao;
	}
	public int getNhan() {
		return nhan;
	}
	public void setNhan(int nhan) {
		this.nhan = nhan;
	}
	public BigDecimal getHsn_his_g() {
		return hsn_his_g;
	}
	public void setHsn_his_g(BigDecimal hsn_his_g) {
		this.hsn_his_g = hsn_his_g;
	}
	public BigDecimal getHsn_his_n() {
		return hsn_his_n;
	}
	public void setHsn_his_n(BigDecimal hsn_his_n) {
		this.hsn_his_n = hsn_his_n;
	}
	public BigDecimal getHsn_load_g() {
		return hsn_load_g;
	}
	public void setHsn_load_g(BigDecimal hsn_load_g) {
		this.hsn_load_g = hsn_load_g;
	}
	public BigDecimal getHsn_load_n() {
		return hsn_load_n;
	}
	public void setHsn_load_n(BigDecimal hsn_load_n) {
		this.hsn_load_n = hsn_load_n;
	}
	
	
	
}
