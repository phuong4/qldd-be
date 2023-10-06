/*
****************************************************
*
*			TTPM-EVNICT
*
****************************************************
 */
package com.evnit.ttpm.AuthApp.model.token;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.evnit.ttpm.AuthApp.model.admin.TokenStatus;
import com.evnit.ttpm.AuthApp.model.admin.User;
import com.evnit.ttpm.AuthApp.model.audit.DateAudit;

@Entity(name = "EMAIL_VERIFICATION_TOKEN")
public class EmailVerificationToken extends DateAudit {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2477059853137732303L;

	@Id
	@Column(name = "TOKEN_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "TOKEN", nullable = false, unique = true)
	private String token;

	@OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "USERID")
	private User user;

	@Column(name = "TOKEN_STATUS")
	@Enumerated(EnumType.STRING)
	private TokenStatus tokenStatus;

	@Column(name = "EXPIRY_DT", nullable = false)
	private Instant expiryDate;

	public EmailVerificationToken() {
	}

	public EmailVerificationToken(Long id, String token, User user, TokenStatus tokenStatus, Instant expiryDate) {
		this.id = id;
		this.token = token;
		this.user = user;
		this.tokenStatus = tokenStatus;
		this.expiryDate = expiryDate;
	}

	public void setConfirmedStatus() {
		setTokenStatus(TokenStatus.STATUS_CONFIRMED);
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Instant getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Instant expiryDate) {
		this.expiryDate = expiryDate;
	}

	public TokenStatus getTokenStatus() {
		return tokenStatus;
	}

	public void setTokenStatus(TokenStatus tokenStatus) {
		this.tokenStatus = tokenStatus;
	}
}
