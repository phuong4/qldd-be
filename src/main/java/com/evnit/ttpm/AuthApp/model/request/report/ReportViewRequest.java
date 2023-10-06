package com.evnit.ttpm.AuthApp.model.request.report;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@ApiModel(value = "View Report Request", description = "Thông tin giao nhận điện năng")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportViewRequest {

	String orgid;
	HashMap<String, Object> pramReport;
	public String getOrgid() {
		return orgid;
	}
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	public HashMap<String, Object> getPramReport() {
		return pramReport;
	}
	public void setPramReport(HashMap<String, Object> pramReport) {
		this.pramReport = pramReport;
	}
	
	

}
