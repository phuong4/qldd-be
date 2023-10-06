/*
****************************************************
*
*			TTPM-EVNICT
*
****************************************************
 */
package com.evnit.ttpm.AuthApp.service.admin;

import com.evnit.ttpm.AuthApp.exception.TokenRefreshException;
import com.evnit.ttpm.AuthApp.model.admin.UserDevice;
import com.evnit.ttpm.AuthApp.model.payload.DeviceInfo;
import com.evnit.ttpm.AuthApp.model.token.RefreshToken;
import com.evnit.ttpm.AuthApp.repository.admin.UserDeviceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDeviceService {

	private final UserDeviceRepository userDeviceRepository;

	@Autowired
	public UserDeviceService(UserDeviceRepository userDeviceRepository) {
		this.userDeviceRepository = userDeviceRepository;
	}

	public Optional<UserDevice> findByUserId(String userId) {
		return userDeviceRepository.findByUser_Userid(userId);
	}

	public Optional<UserDevice> findByRefreshToken(RefreshToken refreshToken) {
		return userDeviceRepository.findByRefreshToken(refreshToken);
	}

	public UserDevice createUserDevice(DeviceInfo deviceInfo) {
		UserDevice userDevice = new UserDevice();
		userDevice.setDeviceId(deviceInfo.getDeviceId());
		userDevice.setDeviceType(deviceInfo.getDeviceType());
		userDevice.setNotificationToken(deviceInfo.getNotificationToken());
		userDevice.setRefreshActive(true);
		return userDevice;
	}

	public void verifyRefreshAvailability(RefreshToken refreshToken) {
		UserDevice userDevice = findByRefreshToken(refreshToken)
				.orElseThrow(() -> new TokenRefreshException(refreshToken.getToken(),
						"Không tìm thấy thiết bị nào cho mã thông báo phù hợp. Xin vui lòng đăng nhập lại"));

		if (!userDevice.getRefreshActive()) {
			throw new TokenRefreshException(refreshToken.getToken(),
					"Có lỗi refresh token .Vui lòng đăng nhập thông qua một thiết bị khác");
		}
	}
}
