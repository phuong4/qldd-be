/*
****************************************************
*
*			TTPM-EVNICT
*
****************************************************
 */
package com.evnit.ttpm.AuthApp.service;

import com.evnit.ttpm.AuthApp.exception.TokenRefreshException;
import com.evnit.ttpm.AuthApp.model.admin.AuthLoginlog;
import com.evnit.ttpm.AuthApp.model.admin.CustomUserDetails;
import com.evnit.ttpm.AuthApp.model.admin.User;
import com.evnit.ttpm.AuthApp.model.admin.UserDevice;
import com.evnit.ttpm.AuthApp.model.payload.LoginRequest;
import com.evnit.ttpm.AuthApp.model.payload.RegistrationRequest;
import com.evnit.ttpm.AuthApp.model.payload.TokenRefreshRequest;
import com.evnit.ttpm.AuthApp.model.token.RefreshToken;
import com.evnit.ttpm.AuthApp.security.JwtTokenProvider;
import com.evnit.ttpm.AuthApp.service.admin.RefreshTokenService;
import com.evnit.ttpm.AuthApp.service.admin.UserDeviceService;
import com.evnit.ttpm.AuthApp.service.admin.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

	private final UserService userService;
	private final JwtTokenProvider tokenProvider;
	private final RefreshTokenService refreshTokenService;

	private final AuthenticationManager authenticationManager;

	private final UserDeviceService userDeviceService;

	@Autowired
	public AuthService(UserService userService, JwtTokenProvider tokenProvider, RefreshTokenService refreshTokenService,
			AuthenticationManager authenticationManager,

			UserDeviceService userDeviceService) {
		this.userService = userService;
		this.tokenProvider = tokenProvider;
		this.refreshTokenService = refreshTokenService;

		this.authenticationManager = authenticationManager;

		this.userDeviceService = userDeviceService;

	}

	public Optional<User> registerUser(RegistrationRequest newRegistrationRequest) {

		User newUser = userService.createUser(newRegistrationRequest);
		User registeredNewUser = userService.save(newUser);
		return Optional.ofNullable(registeredNewUser);
	}

	public Boolean usernameAlreadyExists(String username) {
		return userService.existsByUsername(username);
	}

	public Optional<Authentication> authenticateUser(LoginRequest loginRequest) {
		try {
			Optional<Authentication> authentication = Optional.ofNullable(authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())));
			AuthLoginlog loginlog = userService.createLoginlog(loginRequest, true);
			userService.save(loginlog);
			return authentication;
		} catch (BadCredentialsException e) {
			AuthLoginlog loginlog = userService.createLoginlog(loginRequest, false);
			userService.save(loginlog);
			throw new BadCredentialsException("Bad credentials", e);
		}
	}

	public String generateToken(CustomUserDetails customUserDetails, Long expiration) {
		return tokenProvider.generateToken(customUserDetails, expiration);
	}

	private String generateTokenFromUserId(User user) {
		return tokenProvider.generateTokenFromUserId(user);
	}

	// private String generateTokenFromUserId(Long userId) {
	// return tokenProvider.generateTokenFromUserId(userId);
	// }

	public Optional<RefreshToken> createAndPersistRefreshTokenForDevice(Authentication authentication,
			LoginRequest loginRequest) {
		User currentUser = (User) authentication.getPrincipal();
		userDeviceService.findByUserId(currentUser.getUserid()).map(UserDevice::getRefreshToken)
				.map(RefreshToken::getId).ifPresent(refreshTokenService::deleteById);

		UserDevice userDevice = userDeviceService.createUserDevice(loginRequest.getDeviceInfo());
		RefreshToken refreshToken = refreshTokenService.createRefreshToken();
		userDevice.setUser(currentUser);
		userDevice.setRefreshToken(refreshToken);
		refreshToken.setUserDevice(userDevice);
		refreshToken = refreshTokenService.save(refreshToken);
		return Optional.ofNullable(refreshToken);
	}

	// .map(User::getId)
	public Optional<String> refreshJwtToken(TokenRefreshRequest tokenRefreshRequest) {
		String requestRefreshToken = tokenRefreshRequest.getRefreshToken();

		return Optional.of(refreshTokenService.findByToken(requestRefreshToken).map(refreshToken -> {
			refreshTokenService.verifyExpiration(refreshToken);
			userDeviceService.verifyRefreshAvailability(refreshToken);
			refreshTokenService.increaseCount(refreshToken);
			return refreshToken;
		}).map(RefreshToken::getUserDevice).map(UserDevice::getUser).map(this::generateTokenFromUserId))
				.orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
						"Mã refresh token không hợp lệ hoặc đã hết hạn. Vui lòng đăng nhập lại"));
	}

}
