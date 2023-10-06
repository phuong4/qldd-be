/*
****************************************************
*
*			TTPM-EVNICT
*
****************************************************
 */
package com.evnit.ttpm.AuthApp.service.admin;

import com.evnit.ttpm.AuthApp.annotation.CurrentUser;
import com.evnit.ttpm.AuthApp.exception.UserLogoutException;
import com.evnit.ttpm.AuthApp.model.admin.*;
import com.evnit.ttpm.AuthApp.model.payload.LogOutRequest;
import com.evnit.ttpm.AuthApp.model.payload.LoginRequest;
import com.evnit.ttpm.AuthApp.model.payload.RegistrationRequest;
import com.evnit.ttpm.AuthApp.repository.admin.LoginLogRepository;
import com.evnit.ttpm.AuthApp.repository.admin.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	private final LoginLogRepository loginLogRepository;
	private final RoleService roleService;
	private final UserDeviceService userDeviceService;
	private final RefreshTokenService refreshTokenService;

	@Autowired
	public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleService roleService,
			UserDeviceService userDeviceService, RefreshTokenService refreshTokenService,
			LoginLogRepository loginLogRepository) {
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
		this.roleService = roleService;
		this.userDeviceService = userDeviceService;
		this.refreshTokenService = refreshTokenService;
		this.loginLogRepository = loginLogRepository;
	}

	// HaNV
	public List<User> findAll() {
		return userRepository.findAll();
	}

	public Optional<User> findByUserId(String userid) {
		return userRepository.findByUserid(userid);
	}

//	public Optional<User> findByUsername(String username) {
//		return userRepository.findByUsername(username);
//	}

	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public Optional<User> findById(String Id) {
		return userRepository.findById(Id);
	}

	public User save(User user) {
		return userRepository.save(user);
	}

	public AuthLoginlog save(AuthLoginlog loginlog) {
		return loginLogRepository.save(loginlog);
	}

	public Boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	public Boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	public User createUser(RegistrationRequest registerRequest) {
		User newUser = new User();
		Boolean isNewUserAsAdmin = registerRequest.getRegisterAsAdmin();
		newUser.setEmail(registerRequest.getEmail());
		newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		newUser.setUserid(registerRequest.getUserid());
		newUser.setUsername(registerRequest.getUsername());
		newUser.addRoles(getRolesForNewUser(isNewUserAsAdmin));
		newUser.setEnable(true);
		newUser.setEmailVerified(true);
		return newUser;
	}

	public AuthLoginlog createLoginlog(LoginRequest loginRequest, Boolean status) {
		try {
			AuthLoginlog loginlog = new AuthLoginlog();
			loginlog.setUserid(loginRequest.getUserid());
			loginlog.setLogintime(new Date());
			loginlog.setStatus(status);
			loginlog.setDeviceId(loginRequest.getDeviceInfo().getDeviceId());
			loginlog.setDeviceType(loginRequest.getDeviceInfo().getDeviceType());
			loginlog.setAppId(loginRequest.getDeviceInfo().getAppId());
			loginlog.setAppVersion(loginRequest.getDeviceInfo().getAppVersion());
			return loginlog;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private Set<Role> getRolesForNewUser(Boolean isToBeMadeAdmin) {
		Set<Role> newUserRoles = new HashSet<>(roleService.findAll());
		if (!isToBeMadeAdmin) {
			newUserRoles.removeIf(Role::isAdminRole);
		}
		return newUserRoles;
	}

	public void logoutUser(@CurrentUser CustomUserDetails currentUser, LogOutRequest logOutRequest) {
		String deviceId = logOutRequest.getDeviceInfo().getDeviceId();
		UserDevice userDevice = userDeviceService.findByUserId(currentUser.getUserid())
				.filter(device -> device.getDeviceId().equals(deviceId))
				.orElseThrow(() -> new UserLogoutException(logOutRequest.getDeviceInfo().getDeviceId(),
						"Id thiết bị không hợp lệ. Không tìm thấy thiết bị phù hợp cho người dùng"));

		refreshTokenService.deleteById(userDevice.getRefreshToken().getId());
	}
}
