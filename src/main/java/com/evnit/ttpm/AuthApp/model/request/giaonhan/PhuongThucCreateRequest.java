package com.evnit.ttpm.AuthApp.model.request.giaonhan;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@ApiModel(value = "Phuong thuc Create Request", description = "Thông tin Phương thức giao nhận")
public class PhuongThucCreateRequest {

	private String orgid;
	@Valid
	@NotNull(message = "Tên phương thức không được trống")
	@ApiModelProperty(value = "Tên phương thức", required = true, dataType = "String", allowableValues = "Tên phương thức")
	private String tenPthuc;
	@Valid
	@NotNull(message = "Mã đơn vị quản lý giao không được trống")
	@ApiModelProperty(value = "Mã đơn vị quản lý giao. Danh sách được lấy từ AdminController", required = true, dataType = "String", allowableValues = "Mã đơn vị giao")
	private String orgidG;
	@Valid
	@NotNull(message = "Mã đơn vị quản lý nhận không được trống")
	@ApiModelProperty(value = "Mã đơn vị quản lý nhận. Danh sách được lấy từ AdminController", required = true, dataType = "String", allowableValues = "Mã đơn vị nhận")
	private String orgidN;

	private String loai;
	// private Date tuNgay;
	// private Date denNgay;
	private String moTa;
	private boolean trangThai;
	// @Valid
	// @ApiModelProperty(value = "Lấy từ userid đăng nhập", required = true,
	// dataType = "String", allowableValues = "Lấy từ userid đăng nhập")
	// private String userCrId;
	// private Date userCrDtime;
	// private String userMdfId;
	// private Date userMdfDtime;
	private Boolean isFormula = false;

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

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

	public Boolean getFormula() {
		return isFormula;
	}

	public void setFormula(Boolean formula) {
		isFormula = formula;
	}
}
