/*
****************************************************
*
*			TTPM-EVNICT
*
****************************************************
 */
package com.evnit.ttpm.AuthApp.model.token;

import com.evnit.ttpm.AuthApp.model.admin.UserDevice;
import com.evnit.ttpm.AuthApp.model.audit.DateAudit;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.time.Instant;

@Entity(name = "Q_REFRESH_TOKEN")
public class RefreshToken extends DateAudit {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4320507157723130496L;

	@Id
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Q_REFRESH_TOKEN_SEQ")
	//@SequenceGenerator(sequenceName = "Q_REFRESH_TOKEN_SEQ", allocationSize = 1, name = "Q_REFRESH_TOKEN_SEQ")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TOKEN_ID")
	private Long id;

	@Column(name = "TOKEN", nullable = false, unique = true)
	@NaturalId(mutable = true)
	private String token;

	@OneToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "USER_DEVICE_ID", unique = true)
	private UserDevice userDevice;

	@Column(name = "REFRESH_COUNT")
	private Long refreshCount;

	@Column(name = "EXPIRY_DT", nullable = false)
	private Instant expiryDate;

	public RefreshToken() {
	}

	public RefreshToken(Long id, String token, UserDevice userDevice, Long refreshCount, Instant expiryDate) {
		this.id = id;
		this.token = token;
		this.userDevice = userDevice;
		this.refreshCount = refreshCount;
		this.expiryDate = expiryDate;
	}

	public void incrementRefreshCount() {
		refreshCount = refreshCount + 1;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UserDevice getUserDevice() {
		return userDevice;
	}

	public void setUserDevice(UserDevice userDevice) {
		this.userDevice = userDevice;
	}

	public Instant getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Instant expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Long getRefreshCount() {
		return refreshCount;
	}

	public void setRefreshCount(Long refreshCount) {
		this.refreshCount = refreshCount;
	}
}
