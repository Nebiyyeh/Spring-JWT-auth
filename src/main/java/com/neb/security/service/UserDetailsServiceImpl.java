package com.neb.security.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.neb.domain.MyUser;
import com.neb.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	MyUser myUser=userRepository.findByUserName(username).orElseThrow(()->new UsernameNotFoundException("User not found: "+username));
		return UserDetailsImpl.build(myUser);
				}
	
	
	
	
	
	
	
}
