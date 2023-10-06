/*
****************************************************
*
*			TTPM-EVNICT
*
****************************************************
 */
package com.evnit.ttpm.AuthApp.model.admin;

import com.evnit.ttpm.AuthApp.model.audit.DateAudit;
import com.evnit.ttpm.AuthApp.model.token.RefreshToken;

import javax.persistence.*;

@Entity(name = "Q_USER_DEVICE")
public class UserDevice extends DateAudit {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8482361031105588571L;

	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Q_USER_DEVICE_SEQ")
//	@SequenceGenerator(sequenceName = "Q_USER_DEVICE_SEQ", allocationSize = 1, name = "Q_USER_DEVICE_SEQ")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_DEVICE_ID")
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USERID", nullable = false)
	private User user;

	@Column(name = "DEVICE_TYPE")
	private String deviceType;
	// @Enumerated(value = EnumType.STRING)
	// private DeviceType deviceType;

	@Column(name = "NOTIFICATION_TOKEN")
	private String notificationToken;

	@Column(name = "DEVICE_ID", nullable = false)
	private String deviceId;

	@OneToOne(optional = false, mappedBy = "userDevice")
	private RefreshToken refreshToken;

	@Column(name = "IS_REFRESH_ACTIVE")
	private Boolean isRefreshActive;

	public UserDevice() {
	}

	public UserDevice(Long id, User user, String deviceType, String notificationToken, String deviceId,
			RefreshToken refreshToken, Boolean isRefreshActive) {
		this.id = id;
		this.user = user;
		this.deviceType = deviceType;
		this.notificationToken = notificationToken;
		this.deviceId = deviceId;
		this.refreshToken = refreshToken;
		this.isRefreshActive = isRefreshActive;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getNotificationToken() {
		return notificationToken;
	}

	public void setNotificationToken(String notificationToken) {
		this.notificationToken = notificationToken;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public RefreshToken getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(RefreshToken refreshToken) {
		this.refreshToken = refreshToken;
	}

	public Boolean getRefreshActive() {
		return isRefreshActive;
	}

	public void setRefreshActive(Boolean refreshActive) {
		isRefreshActive = refreshActive;
	}
}
