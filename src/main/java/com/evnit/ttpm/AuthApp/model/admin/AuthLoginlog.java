/*
****************************************************
*
*			TTPM-EVNICT
*
****************************************************
 */
package com.evnit.ttpm.AuthApp.model.admin;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "Q_LOGINOUT")
public class AuthLoginlog implements java.io.Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 6698617527696481289L;

	@Id
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Q_LOGINOUT_SEQ")
	//@SequenceGenerator(sequenceName = "Q_LOGINOUT_SEQ", allocationSize = 1, name = "Q_LOGINOUT_SEQ")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LOGID")
	private long logid;

	@NaturalId
	@Column(name = "USERID")
	private String userid;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LOGINTIME")
	private Date logintime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LASTACCESSTIME")
	private Date lastaccesstime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LOGOUTTIME")
	private Date logouttime;

	@Column(name = "DEVICE_ID")
	private String deviceId;

	@Column(name = "DEVICE_TYPE")
	private String deviceType;

	@Column(name = "APP_ID")
	private String appId;

	@Column(name = "APP_VERSION")
	private String appVersion;

	@Column(name = "STATUS")
	private boolean status;

	public long getLogid() {
		return logid;
	}

	public void setLogid(long logid) {
		this.logid = logid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Date getLogintime() {
		return logintime;
	}

	public void setLogintime(Date logintime) {
		this.logintime = logintime;
	}

	public Date getLastaccesstime() {
		return lastaccesstime;
	}

	public void setLastaccesstime(Date lastaccesstime) {
		this.lastaccesstime = lastaccesstime;
	}

	public Date getLogouttime() {
		return logouttime;
	}

	public void setLogouttime(Date logouttime) {
		this.logouttime = logouttime;
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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
