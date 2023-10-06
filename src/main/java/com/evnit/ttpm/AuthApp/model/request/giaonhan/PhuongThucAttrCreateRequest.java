package com.evnit.ttpm.AuthApp.model.request.giaonhan;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "Phuong thuc Attr Create Request", description = "Thông tin điểm đo thuộc phương thức")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhuongThucAttrCreateRequest {
	private String maPthuc;
	private String maDiemdo;
	// private String userCrId;
	// private Date userCrDtime;
	// private String userMdfId;
	// private Date userMdfDtime;
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
	
	
}
