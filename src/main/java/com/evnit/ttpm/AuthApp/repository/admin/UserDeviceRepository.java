/*
****************************************************
*
*			TTPM-EVNICT
*
****************************************************
 */
package com.evnit.ttpm.AuthApp.repository.admin;

import com.evnit.ttpm.AuthApp.model.admin.UserDevice;
import com.evnit.ttpm.AuthApp.model.token.RefreshToken;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDeviceRepository extends JpaRepository<UserDevice, Long> {

	@Override
	Optional<UserDevice> findById(Long id);

	Optional<UserDevice> findByRefreshToken(RefreshToken refreshToken);

	Optional<UserDevice> findByUser_Userid(String userId);
}
