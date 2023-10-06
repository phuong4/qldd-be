package com.evnit.ttpm.AuthApp.model.request.giaonhan;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "Phuong thuc Attr Key Request", description = "Thông tin điểm đo thuộc phương thức")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GiaoNhanReportKeyRequest {
	private String maGn;
	private String rptid;
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
	
	
}
