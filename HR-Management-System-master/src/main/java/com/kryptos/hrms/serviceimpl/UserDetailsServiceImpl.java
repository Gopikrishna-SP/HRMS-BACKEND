package com.kryptos.hrms.serviceimpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kryptos.hrms.model.User;
import com.kryptos.hrms.repository.UserRepository;
import com.kryptos.hrms.security.services.UserDetailsImpl;

import jakarta.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User not with username : "+ username));
		return UserDetailsImpl.build(user);
	}

}
