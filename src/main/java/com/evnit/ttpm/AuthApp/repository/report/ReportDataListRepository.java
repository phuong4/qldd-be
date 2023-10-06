/*
 ****************************************************
 *
 *			TTPM-EVNICT
 *
 ****************************************************
 */
package com.evnit.ttpm.AuthApp.repository.report;

import com.evnit.ttpm.AuthApp.entity.system.S_Report_Datalist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportDataListRepository extends JpaRepository<S_Report_Datalist, String> {
	//List<S_Report_Datalist> findAllByRptid(String rptID);
        @Query(value ="SELECT * FROM S_REPORT_DATALIST WHERE RPTID =?",nativeQuery = true)
        List<S_Report_Datalist> findAllByRptid(String rptID);

}
