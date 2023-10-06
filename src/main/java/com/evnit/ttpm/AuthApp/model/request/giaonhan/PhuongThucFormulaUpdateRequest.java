package com.evnit.ttpm.AuthApp.model.request.giaonhan;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "Phuong thuc Formula Create Request", description = "Thông tin thiết lập công thức")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhuongThucFormulaUpdateRequest {
	private String fG;
	private String fN;
	private boolean trangThai;
	private String moTa;
	public String getfG() {
		return fG;
	}
	public void setfG(String fG) {
		this.fG = fG;
	}
	public String getfN() {
		return fN;
	}
	public void setfN(String fN) {
		this.fN = fN;
	}
	public boolean isTrangThai() {
		return trangThai;
	}
	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}
	public String getMoTa() {
		return moTa;
	}
	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	
	
	
}
