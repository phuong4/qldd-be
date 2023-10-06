package com.evnit.ttpm.AuthApp.model.request.giaonhan;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "Phuong thuc Update Request", description = "Thông tin Phương thức giao nhận")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhuongThucUpdateRequest {
	// private String maPthuc;
	private String tenPthuc;
	private String orgidG;
	private String orgidN;
	private String loai;
	// private Date tuNgay;
	// private Date denNgay;
	private String moTa;
	private boolean trangThai;
	// private String userCrId;
	// private Date userCrDtime;
	// private String userMdfId;
	// private Date userMdfDtime;
	private boolean isFormula;
	public String getTenPthuc() {
		return tenPthuc;
	}
	public void setTenPthuc(String tenPthuc) {
		this.tenPthuc = tenPthuc;
	}
	public String getOrgidG() {
		return orgidG;
	}
	public void setOrgidG(String orgidG) {
		this.orgidG = orgidG;
	}
	public String getOrgidN() {
		return orgidN;
	}
	public void setOrgidN(String orgidN) {
		this.orgidN = orgidN;
	}
	public String getLoai() {
		return loai;
	}
	public void setLoai(String loai) {
		this.loai = loai;
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
	public boolean isFormula() {
		return isFormula;
	}
	public void setFormula(boolean isFormula) {
		this.isFormula = isFormula;
	}
	
	
}
