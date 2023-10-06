/*
 ****************************************************
 *
 *			TTPM-EVNICT
 *
 ****************************************************
 */
package com.evnit.ttpm.AuthApp.repository.report;

import com.evnit.ttpm.AuthApp.entity.system.S_Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReportRepository extends JpaRepository<S_Report, String> {
	@Query(value = "select * from s_report where rptgroupid=? and enable =1 order by rptord", nativeQuery = true)
	List<S_Report> getALLByGroup(String rptGroupID);

	@Query(value = "select * from s_report where is_system=1 and enable =1 order by rptord", nativeQuery = true)
	List<S_Report> getALLSystem();

	@Query(value = "select * from s_report where rptgroupid=? and enable =1 order by rptord", nativeQuery = true)
	List<S_Report> getALLByUser(String rptGroupID,String userid);

}
