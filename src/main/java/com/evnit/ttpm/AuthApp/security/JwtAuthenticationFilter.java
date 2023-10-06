/*
****************************************************
*
*			TTPM-EVNICT
*
****************************************************
 */
package com.evnit.ttpm.AuthApp.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.evnit.ttpm.AuthApp.service.admin.CustomUserDetailsService;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	// private static final Logger log =
	// Logger.getLogger(JwtAuthenticationFilter.class);

	@Value("${app.jwt.header}")
	private String tokenRequestHeader;

	@Value("${app.jwt.header.prefix}")
	private String tokenRequestHeaderPrefix;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private JwtTokenValidator jwtTokenValidator;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		try {
			String jwt = getJwtFromRequest(request);

			if (StringUtils.hasText(jwt) && jwtTokenValidator.validateToken(jwt)) {
				String userId = jwtTokenProvider.getUserIdFromJWT(jwt);

				UserDetails userDetails = customUserDetailsService.loadUserByUsername(userId);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, jwt, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (Exception ex) {
			// log.error("Failed to set user authentication in security context: ", ex);
			throw ex;
		}

		filterChain.doFilter(request, response);
	}

	private String getJwtFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader(tokenRequestHeader);
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(tokenRequestHeaderPrefix)) {
			// log.info("Extracted Token: " + bearerToken);
			return bearerToken.replace(tokenRequestHeaderPrefix, "");
		}
		return null;
	}
}
