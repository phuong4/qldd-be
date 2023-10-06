package com.evnit.ttpm.AuthApp.model.request.giaonhan;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@ApiModel(value = "GNDN Create Request", description = "Thông tin giao nhận điện năng")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GiaoNhanReportUpdateRequest {

	private String maGn;
	private String rptid;
	private BigInteger stt;
	private String moTa;
	private boolean thang;
	private boolean ngay;
	public String getMaGn() {
		return maGn;
	}
	public void setMaGn(String maGn) {
		this.maGn = maGn;
	}
	public String getRptid() {
		return rptid;
	}
	public void setRptid(String rptid) {
		this.rptid = rptid;
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
	public boolean isThang() {
		return thang;
	}
	public void setThang(boolean thang) {
		this.thang = thang;
	}
	public boolean isNgay() {
		return ngay;
	}
	public void setNgay(boolean ngay) {
		this.ngay = ngay;
	}
	
	
}
