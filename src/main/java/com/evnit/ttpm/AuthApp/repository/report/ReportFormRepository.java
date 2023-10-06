/*
 ****************************************************
 *
 *			TTPM-EVNICT
 *
 ****************************************************
 */
package com.evnit.ttpm.AuthApp.repository.report;

import com.evnit.ttpm.AuthApp.entity.system.S_Report_Form;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ReportFormRepository extends JpaRepository<S_Report_Form, String> {
	List<S_Report_Form> findAllByRptid(String rptID);

	//List<S_Report_Form> getAllByRptid(String rptID);

}
