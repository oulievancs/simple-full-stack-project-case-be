package com.my.simplefullstackprojectcase.security.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Oulis Evangelos on 11/16/25.
 */
@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		User user = new User(username, username, new ArrayList<>());
		if (user == null) {
			throw new UsernameNotFoundException("User Not Found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(
				user.getUsername(),
				user.getPassword(),
				Collections.emptyList()
		);
	}
}
