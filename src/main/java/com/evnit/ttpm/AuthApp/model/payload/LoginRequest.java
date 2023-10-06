/*
****************************************************
*
*			TTPM-EVNICT
*
****************************************************
 */
package com.evnit.ttpm.AuthApp.model.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.evnit.ttpm.AuthApp.validation.annotation.NullOrNotBlank;

@ApiModel(value = "Login Request", description = "Thông tin đăng nhập")
public class LoginRequest {

	@NullOrNotBlank(message = "Login Userid can be null but not blank")
	@ApiModelProperty(value = "Tên đăng nhập", required = true, allowableValues = "NonEmpty String")
	private String userid;

	@NullOrNotBlank(message = "Login Username can be null but not blank")
	@ApiModelProperty(value = "Tên người dùng ", required = true, allowableValues = "NonEmpty String")
	private String username;

	@NullOrNotBlank(message = "Email đăng nhập có thể để trắng nhưng không được để trống")
	@ApiModelProperty(value = "Email đăng nhập", allowableValues = "NonEmpty String", allowEmptyValue = false)
	private String email;

	@NotNull(message = "Login expiration can be null but not blank")
	@ApiModelProperty(value = "JWT expiration time", required = false, allowableValues = "NonEmpty String")
	private Long expiration = 0L;

	@NotNull(message = "Mật khẩu không được để trống")
	@ApiModelProperty(value = "Mật khẩu người dùng hợp lệ", required = true, allowableValues = "NonEmpty String")
	private String password;

	@Valid
	@NotNull(message = "Thông tin thiết bị không được null")
	@ApiModelProperty(value = "Thông tin thiết bị", required = true, dataType = "object", allowableValues = "Thông tin thiết bị hợp lệ")
	private DeviceInfo deviceInfo;

	public LoginRequest() {
	}

	public LoginRequest(String userid, String username, String email, String password, DeviceInfo deviceInfo) {
		this.userid = userid;
		this.username = username;
		this.email = email;
		this.password = password;
		this.deviceInfo = deviceInfo;
	}

	public LoginRequest(String userid, String username, String email, Long expiration, String password,
			DeviceInfo deviceInfo) {
		this.userid = userid;
		this.username = username;
		this.email = email;
		this.expiration = expiration;
		this.password = password;
		this.deviceInfo = deviceInfo;
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

	public Long getExpiration() {
		return expiration;
	}

	public void setExpiration(Long expiration) {
		this.expiration = expiration;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public DeviceInfo getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(DeviceInfo deviceInfo) {
		this.deviceInfo = deviceInfo;
	}
}
