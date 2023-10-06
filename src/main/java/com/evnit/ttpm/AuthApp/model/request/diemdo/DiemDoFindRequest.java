package com.evnit.ttpm.AuthApp.model.request.diemdo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "DiemDoFindRequest", description = "Thông tin tìm kiếm")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiemDoFindRequest {

	@ApiModelProperty(value = "Tiêu chí tìm kiếm. Lấy danh sách từ DanhMucController", required = true, allowableValues = "String")
	private String columnName;
	@ApiModelProperty(value = "Thông tin tìm kiếm", required = true, allowableValues = "String")
	private String info;
	@ApiModelProperty(value = "Trang cần hiển thị", required = true)
	private int page;
	@ApiModelProperty(value = "Số lượng bản ghi cần hiển thị", required = true)
	private int size;

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
