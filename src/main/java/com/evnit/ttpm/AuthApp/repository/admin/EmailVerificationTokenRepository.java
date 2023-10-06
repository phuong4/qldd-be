/*
****************************************************
*
*			TTPM-EVNICT
*
****************************************************
 */
package com.evnit.ttpm.AuthApp.repository.admin;

import com.evnit.ttpm.AuthApp.model.token.EmailVerificationToken;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailVerificationTokenRepository {

	Optional<EmailVerificationToken> findByToken(String token);
}
