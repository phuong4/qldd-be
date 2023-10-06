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

@ApiModel(value = "Logout request", description = "Thông tin logout")
public class LogOutRequest {

	@Valid
	@NotNull(message = "Thông tin thiết bị không được để null")
	@ApiModelProperty(value = "Device info", required = true, dataType = "object", allowableValues = "AAAAAAAAAa BBBBBBB CCCCCCCCcc")
	private DeviceInfo deviceInfo;

	public LogOutRequest() {
	}

	public LogOutRequest(DeviceInfo deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

	public DeviceInfo getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(DeviceInfo deviceInfo) {
		this.deviceInfo = deviceInfo;
	}
}
