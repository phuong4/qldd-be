/*
****************************************************
*
*			TTPM-EVNICT
*
****************************************************
 */
package com.evnit.ttpm.AuthApp.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evnit.ttpm.AuthApp.model.admin.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
