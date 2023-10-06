/*
****************************************************
*
*			TTPM-EVNICT
*
****************************************************
 */
package com.evnit.ttpm.AuthApp.model.admin;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "Q_ROLE")
public class Role {

	@Id
	@Column(name = "ROLEID")
	private String id;

	@Column(name = "ROLEDESC")
	@Enumerated(EnumType.STRING)
	@NaturalId
	private RoleName role;

	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<User> userList = new HashSet<>();

	public Role(RoleName role) {
		this.role = role;
	}

	public Role() {

	}

	public boolean isAdminRole() {
		return null != this && this.role.equals(RoleName.ROLE_ADMIN);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public RoleName getRole() {
		return role;
	}

	public void setRole(RoleName role) {
		this.role = role;
	}

	public Set<User> getUserList() {
		return userList;
	}

	public void setUserList(Set<User> userList) {
		this.userList = userList;
	}
}
