package com.order.it.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.order.it.entity.User;
import com.order.it.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

	Logger logger = LoggerFactory.getLogger(CustomUserDetailService.class);
	@Autowired
	UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> OptionalUser = userRepo.findById(username);
		User user = OptionalUser.orElseThrow(() -> 
				new UsernameNotFoundException("User not found in table: User"));
		if (null == user) {
			throw new UsernameNotFoundException("no user present");
		}
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		String rolesOfUser[] = user.getRoles().split("#");
		
		for(String role : rolesOfUser)
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role));
		
		return new org.springframework.security.core.userdetails.User(user.getMobileNo(), user.getPassword(), true,
				true, true, true, grantedAuthorities);
	}

}
