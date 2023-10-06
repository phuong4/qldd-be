/*
 ****************************************************
 *
 *			TTPM-EVNICT
 *
 ****************************************************
 */
package com.evnit.ttpm.AuthApp.repository.report;

import com.evnit.ttpm.AuthApp.entity.system.S_Report_Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReportGroupRepository  extends JpaRepository<S_Report_Group, String> {

    @Query(value = "select * from s_report_group where rptgroupid_parent is null and enable =1 order by rptgroupord", nativeQuery = true)
    List<S_Report_Group> getReportGroup(String userid);
    @Query(value = "select * from s_report_group where rptgroupid_parent=? and enable =1 order by rptgroupord", nativeQuery = true)
    List<S_Report_Group> getReportGroupByParent(String rptgroupid_parent);

}
