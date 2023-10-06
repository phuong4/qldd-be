/*
****************************************************
*
*			TTPM-EVNICT
*
****************************************************
 */
package com.evnit.ttpm.AuthApp.model.payload;

public class RegistrationRequest {

	private String userid;
	private String username;

	private String email;

	private String password;

	private Boolean registerAsAdmin;

	public RegistrationRequest(String username, String email, String password, Boolean registerAsAdmin) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.registerAsAdmin = registerAsAdmin;
	}

	public RegistrationRequest() {
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getRegisterAsAdmin() {
		return registerAsAdmin;
	}

	public void setRegisterAsAdmin(Boolean registerAsAdmin) {
		this.registerAsAdmin = registerAsAdmin;
	}
}
