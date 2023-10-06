package com.evnit.ttpm.AuthApp.model.request.giaonhan;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GiaoNhanViewRequest {
	private String orgid;
	private int thang;
	private int ngay;
	private String kieu;
	public String getOrgid() {
		return orgid;
	}
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	public int getThang() {
		return thang;
	}
	public void setThang(int thang) {
		this.thang = thang;
	}
	public int getNgay() {
		return ngay;
	}
	public void setNgay(int ngay) {
		this.ngay = ngay;
	}
	public String getKieu() {
		return kieu;
	}
	public void setKieu(String kieu) {
		this.kieu = kieu;
	}
	
	
}
