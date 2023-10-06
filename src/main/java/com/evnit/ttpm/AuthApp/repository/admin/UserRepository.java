/*
****************************************************
*
*			TTPM-EVNICT
*
****************************************************
 */
package com.evnit.ttpm.AuthApp.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evnit.ttpm.AuthApp.model.admin.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

	// Optional<User> findByUsername(String username);

	Optional<User> findByUserid(String userid);

	Boolean existsByEmail(String email);

	Optional<User> findByEmail(String email);

	Boolean existsByUsername(String username);

}
