/*
****************************************************
*
*			TTPM-EVNICT
*
****************************************************
 */
package com.evnit.ttpm.AuthApp.model.payload;

import javax.validation.constraints.NotBlank;

public class TokenRefreshRequest {

	@NotBlank(message = "Refresh token không được để trống")
	private String refreshToken;

	public TokenRefreshRequest(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public TokenRefreshRequest() {
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
}
