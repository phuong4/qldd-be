/*
 ****************************************************
 *
 *			TTPM-EVNICT
 *
 ****************************************************
 */
package com.evnit.ttpm.AuthApp.repository.attr;

import com.evnit.ttpm.AuthApp.entity.attr.S_Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AttrRepository extends JpaRepository<S_Attribute, String> {

	@Query(value = "SELECT b.*\n" + "  FROM (SELECT *\n" + "          FROM S_Attribute_Group_Assobj\n"
			+ "         WHERE OBJID = ? AND OBJTYPEID = ?) a\n"
			+ "       JOIN S_ATTRIBUTE b ON a.ATTRGROUPID = b.ATTRGROUPID", nativeQuery = true)
	List<S_Attribute> getAttrGroupView(String objID, String objTypeID);
}
