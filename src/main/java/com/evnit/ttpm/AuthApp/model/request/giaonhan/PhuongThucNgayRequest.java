package com.evnit.ttpm.AuthApp.model.request.giaonhan;

import io.swagger.annotations.ApiModel;

import java.util.Date;

@ApiModel(value = "Phuong thuc ngày Request", description = "Phuong thuc ngày Request")
public class PhuongThucNgayRequest {
	private String maPthuc;
	private Date ngay;
	public String getMaPthuc() {
		return maPthuc;
	}
	public void setMaPthuc(String maPthuc) {
		this.maPthuc = maPthuc;
	}

	public Date getNgay() {
		return ngay;
	}

	public void setNgay(Date ngay) {
		this.ngay = ngay;
	}
}
