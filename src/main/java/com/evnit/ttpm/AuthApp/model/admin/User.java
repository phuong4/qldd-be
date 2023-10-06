/*
****************************************************
*
*			TTPM-EVNICT
*
****************************************************
 */
package com.evnit.ttpm.AuthApp.model.admin;

import com.evnit.ttpm.AuthApp.entity.admin.S_Organization;
import com.evnit.ttpm.AuthApp.model.audit.DateAudit;
import com.evnit.ttpm.AuthApp.validation.annotation.NullOrNotBlank;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Q_USER")
public class User extends DateAudit {

	/**
	 *
	 */
	private static final long serialVersionUID = 6698617527696481289L;

	@Id
	@Column(name = "USERID")
	private String userid;

//    @Column(name = "ID")
//    private Long id;

	@NaturalId
	@Column(name = "EMAIL", unique = true)
	@NotBlank(message = "User email cannot be null")
	private String email;

	@Column(name = "USERNAME", unique = true)
	@NullOrNotBlank(message = "Username can not be blank")
	private String username;

	@Column(name = "PASSWORD")
	@NotNull(message = "Password cannot be null")
	private String password;

	@Column(name = "ENABLE", nullable = false)
	private Boolean enable;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "Q_USER_ROLE", joinColumns = {
			@JoinColumn(name = "USERID", referencedColumnName = "USERID") }, inverseJoinColumns = {
					@JoinColumn(name = "ROLEID", referencedColumnName = "ROLEID") })
	private Set<Role> roles = new HashSet<>();

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "Q_ORG_GRANT_USER", joinColumns = {
			@JoinColumn(name = "USERID", referencedColumnName = "USERID") }, inverseJoinColumns = {
					@JoinColumn(name = "ORGID", referencedColumnName = "ORGID") })
	private Set<S_Organization> organizations = new HashSet<>();

	@Column(name = "IS_EMAIL_VERIFIED", nullable = false)
	private Boolean isEmailVerified;

	@Transient
	private Long expiration = 0L;

	public Long getExpiration() {
		return expiration;
	}

	public void setExpiration(Long expiration) {
		this.expiration = expiration;
	}

	public User() {
		super();
	}

	public User(User user) {
//        id = user.getId();
		userid = user.getUserid();
		username = user.getUsername();
		password = user.getPassword();

		email = user.getEmail();
		enable = user.getEnable();
		roles = user.getRoles();
		organizations = user.getOrganizations();

		isEmailVerified = user.getEmailVerified();
	}

	public void addRole(Role role) {
		roles.add(role);
		role.getUserList().add(this);
	}

	public void addRoles(Set<Role> roles) {
		roles.forEach(this::addRole);
	}

	public void removeRole(Role role) {
		roles.remove(role);
		role.getUserList().remove(this);
	}

	public void markVerificationConfirmed() {
		setEmailVerified(true);
	}

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> authorities) {
		roles = authorities;
	}

	public Boolean getEmailVerified() {
		return isEmailVerified;
	}

	public void setEmailVerified(Boolean emailVerified) {
		isEmailVerified = emailVerified;
	}

	public Set<S_Organization> getOrganizations() {
		return organizations;
	}

}
