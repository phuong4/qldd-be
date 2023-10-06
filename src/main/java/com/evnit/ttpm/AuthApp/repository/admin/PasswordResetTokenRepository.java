/*
****************************************************
*
*			TTPM-EVNICT
*
****************************************************
 */
package com.evnit.ttpm.AuthApp.repository.admin;

import com.evnit.ttpm.AuthApp.model.admin.PasswordResetToken;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordResetTokenRepository {

	Optional<PasswordResetToken> findByToken(String token);
}