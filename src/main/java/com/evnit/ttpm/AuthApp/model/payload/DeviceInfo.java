/*
****************************************************
*
*			TTPM-EVNICT
*
****************************************************
 */
package com.evnit.ttpm.AuthApp.model.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.evnit.ttpm.AuthApp.validation.annotation.NullOrNotBlank;

public class DeviceInfo {

	@NotBlank(message = "Device id cannot be blank")
	private String deviceId;

	@NotNull(message = "Device type cannot be null")
	private String deviceType;

	@NotNull(message = "Device appId cannot be null")
	private String appId;

	@NotNull(message = "Device appVersion cannot be null")
	private String appVersion;

	@NullOrNotBlank(message = "Device notification token can be null but not blank")
	private String notificationToken;

	public DeviceInfo() {
	}

	public DeviceInfo(String deviceId, String deviceType, String appId, String appVersion, String notificationToken) {
		this.deviceId = deviceId;
		this.deviceType = deviceType;
		this.appId = appId;
		this.appVersion = appVersion;
		this.notificationToken = notificationToken;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getNotificationToken() {
		return notificationToken;
	}

	public void setNotificationToken(String notificationToken) {
		this.notificationToken = notificationToken;
	}

	@Override
	public String toString() {
		return "DeviceInfo{" + "deviceId='" + deviceId + '\'' + ", deviceType=" + deviceType + ", notificationToken='"
				+ notificationToken + '\'' + '}';
	}
}
