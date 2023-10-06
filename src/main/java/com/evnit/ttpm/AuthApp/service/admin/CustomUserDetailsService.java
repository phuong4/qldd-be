/*
****************************************************
*
*			TTPM-EVNICT
*
****************************************************
 */
package com.evnit.ttpm.AuthApp.service.admin;

import com.evnit.ttpm.AuthApp.model.admin.CustomUserDetails;
import com.evnit.ttpm.AuthApp.model.admin.User;
import com.evnit.ttpm.AuthApp.repository.admin.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	@Autowired
	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
		// Optional<User> dbUser = userRepository.findByUsername(username);
		Optional<User> dbUser = userRepository.findByUserid(userid);
		return dbUser.map(CustomUserDetails::new).orElseThrow(
				() -> new UsernameNotFoundException("Không tìm thấy tài khoản trong CDLS có userid là " + userid));
	}

	public UserDetails loadUserById(String id) {
		Optional<User> dbUser = userRepository.findById(id);
		return dbUser.map(CustomUserDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("Không tim thấy user có id là " + id));
	}
}
