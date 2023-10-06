/*
****************************************************
*
*			TTPM-EVNICT
*
****************************************************
 */
package com.evnit.ttpm.AuthApp.controller;

import com.evnit.ttpm.AuthApp.exception.TokenRefreshException;
import com.evnit.ttpm.AuthApp.exception.UserLoginException;
import com.evnit.ttpm.AuthApp.exception.UserRegistrationException;
import com.evnit.ttpm.AuthApp.model.admin.CustomUserDetails;
import com.evnit.ttpm.AuthApp.model.payload.*;
import com.evnit.ttpm.AuthApp.model.token.RefreshToken;
import com.evnit.ttpm.AuthApp.security.JwtTokenProvider;
import com.evnit.ttpm.AuthApp.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
// @CrossOrigin(origins = "https://abc.evn.com.vn", maxAge = 3600)
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

	// private static final Logger logger =
	// Logger.getLogger(AuthController.class);
	private final AuthService authService;
	private final JwtTokenProvider tokenProvider;

	@Autowired
	public AuthController(AuthService authService, JwtTokenProvider tokenProvider) {
		this.authService = authService;
		this.tokenProvider = tokenProvider;

	}

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authService.authenticateUser(loginRequest)
				.orElseThrow(() -> new UserLoginException("Couldn't login user [" + loginRequest + "]"));

		CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

		SecurityContextHolder.getContext().setAuthentication(authentication);

		return authService.createAndPersistRefreshTokenForDevice(authentication, loginRequest)
				.map(RefreshToken::getToken).map(refreshToken -> {
					String jwtToken = authService.generateToken(customUserDetails, loginRequest.getExpiration());
					return ResponseEntity.ok(
							new JwtAuthenticationResponse(jwtToken, refreshToken, tokenProvider.getExpiryDuration()));
				})
				.orElseThrow(() -> new UserLoginException("Couldn't create refresh token for: [" + loginRequest + "]"));
	}

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody RegistrationRequest registrationRequest) {

		return authService.registerUser(registrationRequest).map(user -> {

			return ResponseEntity
					.ok(new ApiResponse(true, "User registered successfully. Check your email for verification"));
		}).orElseThrow(
				() -> new UserRegistrationException(registrationRequest.getEmail(), "Missing user object in database"));
	}

	@PostMapping("/refresh")

	public ResponseEntity<?> refreshJwtToken(@Valid @RequestBody TokenRefreshRequest tokenRefreshRequest) {

		return authService.refreshJwtToken(tokenRefreshRequest).map(updatedToken -> {
			String refreshToken = tokenRefreshRequest.getRefreshToken();
			// logger.info("Created new Jwt Auth token: " + updatedToken);
			return ResponseEntity
					.ok(new JwtAuthenticationResponse(updatedToken, refreshToken, tokenProvider.getExpiryDuration()));
		}).orElseThrow(() -> new TokenRefreshException(tokenRefreshRequest.getRefreshToken(),
				"Unexpected error during token refresh. Please logout and login again."));
	}
}
