package com.evnit.ttpm.AuthApp.model.request.giaonhan;

import io.swagger.annotations.ApiModel;

import java.util.Date;

@ApiModel(value = "Giao nhận ngày Request", description = "Giao nhận ngày Request")
public class GiaoNhanNgayRequest {
	private String maGn;
	private Date ngay;

	public String getMaGn() {
		return maGn;
	}
	public void setMaGn(String maGn) {
		this.maGn = maGn;
	}

	public Date getNgay() {
		return ngay;
	}

	public void setNgay(Date ngay) {
		this.ngay = ngay;
	}
}
