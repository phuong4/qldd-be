/*
****************************************************
*
*			TTPM-EVNICT
*
****************************************************
 */
package com.evnit.ttpm.AuthApp.service.admin;

import com.evnit.ttpm.AuthApp.model.admin.Role;
import com.evnit.ttpm.AuthApp.repository.admin.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RoleService {

	private final RoleRepository roleRepository;

	@Autowired
	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	public Collection<Role> findAll() {
		return roleRepository.findAll();
	}

}
