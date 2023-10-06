package com.evnit.ttpm.AuthApp.service.report;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.system.S_Report_Group;
import com.evnit.ttpm.AuthApp.repository.report.ReportGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReportGroupViewServiceImpl implements ReportGroupViewService {
	@Autowired
	ReportGroupRepository reportGroupRepository;
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public ResponseData getListReportGroupByUser(String userid) {
		ResponseData responseData = new ResponseData();
		try {
			//String sql ="select * from s_report_group where rptgroupid_parent is null and enable =1 order by rptgroupord";
			List<S_Report_Group> lstData = new ArrayList<>();

			List<S_Report_Group> lstParent = reportGroupRepository.getReportGroup(userid);
			for (S_Report_Group obj:lstParent) {
				List<S_Report_Group> lstChild = reportGroupRepository.getReportGroupByParent(obj.getRptgroupid());
				obj.setListChild(lstChild);
				lstData.add(obj);
			}
			responseData.setState(ResponseData.STATE.OK.toString());
			responseData.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
			responseData.setData(lstData);
			return responseData;
		} catch (Exception ex) {
			responseData.setState(ResponseData.STATE.FAIL.toString());
			responseData.setMessage(ex.getMessage());
			return responseData;
		}
	}

	@Override
	public ResponseData getListReportGroupByParent(String rptGroupIDParent) {
		ResponseData responseData = new ResponseData();
		try {
			//String sql="select * from s_report_group where rptgroupid_parent=? and enable =1 order by rptgroupord";

			//Object lstData = jdbcTemplate.queryForList(sql,rptGroupIDParent);
			List<S_Report_Group> lstData = reportGroupRepository.getReportGroupByParent(rptGroupIDParent);
			responseData.setState(ResponseData.STATE.OK.toString());
			responseData.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
			responseData.setData(lstData);
			return responseData;
		} catch (Exception ex) {
			responseData.setState(ResponseData.STATE.FAIL.toString());
			responseData.setMessage(ex.getMessage());
			return responseData;
		}
	}

}
