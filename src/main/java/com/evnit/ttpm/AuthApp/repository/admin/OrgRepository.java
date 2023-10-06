/*
****************************************************
*
*			TTPM-EVNICT
*
****************************************************
 */
package com.evnit.ttpm.AuthApp.repository.admin;

import com.evnit.ttpm.AuthApp.entity.admin.S_Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrgRepository extends JpaRepository<S_Organization, String> {

	List<S_Organization> getAllByOrgidParent(String orgiIDParent);

}
