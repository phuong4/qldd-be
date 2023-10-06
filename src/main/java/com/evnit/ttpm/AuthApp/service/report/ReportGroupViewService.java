package com.evnit.ttpm.AuthApp.service.report;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import org.springframework.stereotype.Service;

@Service
public interface ReportGroupViewService {

	ResponseData getListReportGroupByUser(String userid);
	ResponseData getListReportGroupByParent(String rptGroupIDParent);

}
