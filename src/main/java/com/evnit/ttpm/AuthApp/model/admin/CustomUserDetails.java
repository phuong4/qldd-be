/*
****************************************************
*
*			TTPM-EVNICT
*
****************************************************
 */
package com.evnit.ttpm.AuthApp.model.admin;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

public class CustomUserDetails extends User implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 736305024202035651L;

	public CustomUserDetails(final User user) {
		super(user);

//        if(currentOrg==null) {
//            if (user.getOrganizations().size() > 0) {
//                List<S_Organization> lst = user.getOrganizations().stream().sorted(Comparator.comparing(S_Organization::getOrgord)).sorted(Comparator.comparing(S_Organization::getOrgid)).collect(Collectors.toList());
//                currentOrg = lst.get(0);
//            }
//        }
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRole().name()))
				.collect(Collectors.toList());
	}

	// @Override
	// public String getPassword() {
	// return super.getPassword();
	// }

	@Override
	public String getUsername() {
		return super.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return super.getEnable();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return super.getEnable();// super.getEmailVerified();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getUserid());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		CustomUserDetails that = (CustomUserDetails) obj;
		return Objects.equals(getUserid(), that.getUserid());
	}
}
