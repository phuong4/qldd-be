/*
****************************************************
*
*			TTPM-EVNICT
*
****************************************************
 */
package com.evnit.ttpm.AuthApp.service.admin;

import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.evnit.ttpm.AuthApp.exception.TokenRefreshException;
import com.evnit.ttpm.AuthApp.model.token.RefreshToken;
import com.evnit.ttpm.AuthApp.repository.admin.RefreshTokenRepository;
import com.evnit.ttpm.AuthApp.util.Util;

@Service
public class RefreshTokenService {

	private final RefreshTokenRepository refreshTokenRepository;

	@Value("${app.token.refresh.duration}")
	private Long refreshTokenDurationMs;

	@Autowired
	public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
		this.refreshTokenRepository = refreshTokenRepository;
	}

	public Optional<RefreshToken> findByToken(String token) {
		return refreshTokenRepository.findByToken(token);
	}

	public RefreshToken save(RefreshToken refreshToken) {
		return refreshTokenRepository.save(refreshToken);
	}

	public RefreshToken createRefreshToken() {
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
		refreshToken.setToken(Util.generateRandomUuid());
		refreshToken.setRefreshCount(0L);
		return refreshToken;
	}

	public void verifyExpiration(RefreshToken token) {
		if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
			throw new TokenRefreshException(token.getToken(), "Expired token. Please issue a new request");
		}
	}

	public void deleteById(Long id) {
		refreshTokenRepository.deleteById(id);
	}

	public void increaseCount(RefreshToken refreshToken) {
		refreshToken.incrementRefreshCount();
		save(refreshToken);
	}
}
