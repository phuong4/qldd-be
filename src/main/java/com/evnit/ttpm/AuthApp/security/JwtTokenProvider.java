/*
****************************************************
*
*			TTPM-EVNICT
*
****************************************************
 */
package com.evnit.ttpm.AuthApp.security;

import com.evnit.ttpm.AuthApp.model.admin.CustomUserDetails;
import com.evnit.ttpm.AuthApp.model.admin.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {

	// HaNV - Class gen token

	private final String jwtSecret;
	private final long jwtExpirationInMs;

	public JwtTokenProvider(@Value("${app.jwt.secret}") String jwtSecret,
			@Value("${app.jwt.expiration}") long jwtExpirationInMs) {
		this.jwtSecret = jwtSecret;
		this.jwtExpirationInMs = jwtExpirationInMs;
	}

	public String generateToken(CustomUserDetails customUserDetails, Long expiration) {

		// Thời gian time out
		Instant expiryDate = Instant.now().plusMillis(expiration == 0 ? jwtExpirationInMs : expiration * 60 * 1000);

		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("typ", "JWT");

		// Claims claims = Jwts.claims().setSubject(customUserDetails.getUsername());
		// HaNV - Thay sang userid
		Claims claims = Jwts.claims().setSubject(customUserDetails.getUserid());
		String email = customUserDetails.getEmail() != null ? customUserDetails.getEmail() : "NULL";

		claims.put("email", email);
		claims.put("provide", "KDLDD");

		// claims.put("auth",
		// customUserDetails.getRoles().stream().map(role -> new
		// SimpleGrantedAuthority(role.getRole().name()))
		// .filter(Objects::nonNull).collect(Collectors.toList()));

		return Jwts.builder().setClaims(claims).setHeaderParams(headers).setIssuedAt(Date.from(Instant.now()))
				.setExpiration(Date.from(expiryDate)).signWith(SignatureAlgorithm.RS256, RSAKey.getPrivateKey())
				.compact();
	}

	public String generateTokenFromUserId(User user) {

		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("typ", "JWT");

		// Claims claims = Jwts.claims().setSubject(user.getUsername());
		// HaNV - Thay sang userid
		Claims claims = Jwts.claims().setSubject(user.getUserid());
		Long expiration = user.getExpiration();

		String email = user.getEmail() != null ? user.getEmail() : "NULL";
		claims.put("email", email);
		claims.put("provide", "KDLDD");

		// claims.put("auth",
		// user.getRoles().stream().map(role -> new
		// SimpleGrantedAuthority(role.getRole().name()))
		// .filter(Objects::nonNull).collect(Collectors.toList()));

		Instant expiryDate = Instant.now().plusMillis(expiration == 0 ? jwtExpirationInMs : expiration * 60 * 1000);
		return Jwts.builder().setClaims(claims).setHeaderParams(headers).setIssuedAt(Date.from(Instant.now()))
				.setExpiration(Date.from(expiryDate)).signWith(SignatureAlgorithm.RS256, RSAKey.getPrivateKey())
				.compact();
	}

	// public String generateTokenFromUserId(Long userId) {
	// Instant expiryDate = Instant.now().plusMillis(jwtExpirationInMs);
	// return
	// Jwts.builder().setSubject(Long.toString(userId)).setIssuedAt(Date.from(Instant.now()))
	// .setExpiration(Date.from(expiryDate)).signWith(SignatureAlgorithm.HS512,
	// jwtSecret).compact();
	// }

	public String getUserIdFromJWT(String token) {
		Claims claims = Jwts.parser().setSigningKey(RSAKey.getPublicKey()).parseClaimsJws(token).getBody();

		return claims.getSubject();
	}

	// public Long getUserIdFromJWT(String token) {
	// Claims claims =
	// Jwts.parser().setSigningKey(RSAKey.getPublicKey()).parseClaimsJws(token).getBody();
	//
	// return Long.parseLong(claims.getSubject());
	// }

	public Date getTokenExpiryFromJWT(String token) {
		Claims claims = Jwts.parser().setSigningKey(RSAKey.getPublicKey()).parseClaimsJws(token).getBody();

		return claims.getExpiration();
	}

	public long getExpiryDuration() {
		return jwtExpirationInMs;
	}
}
