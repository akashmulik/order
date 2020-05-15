package com.order.it.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.it.entity.User;
import com.order.it.repository.ProductRepository;
import com.order.it.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User save(User user) {
		
		user.setRoles("CUST");
		return userRepository.save(user);
	}

	public Optional<User> findById(String mobileNo) {
		
		return userRepository.findById(mobileNo);
	}
	
}
