package com.evnit.ttpm.AuthApp.service.report;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.admin.ResponseDataReport;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public interface ReportViewService {

	ResponseDataReport getViewReport(String rptID, HashMap<String, Object> pramReport, String orgid,
                                     String userid);

	byte[] getViewReportFileExcel(String rptID, HashMap<String, Object> pramReport, String orgid, String userid);

	ResponseData getListReportByGroup(String rptGroupID);


	ResponseData getListReportSystem();
	ResponseData getListReportByUser(String rptGroupID,String userid);


}
