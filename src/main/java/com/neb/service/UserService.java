package com.neb.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.neb.domain.MyUser;
import com.neb.domain.Role;
import com.neb.domain.enums.RoleType;
import com.neb.dto.request.RegisterRequest;
import com.neb.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

	private UserRepository userRepo;
	
	private PasswordEncoder passwordEncoder;
	
	private RoleService roleService;
	
	public void saveUser(RegisterRequest userRequest) {
		MyUser myUser=new MyUser();
		
		myUser.setFirstName(userRequest.getFirstName());
		myUser.setLastName(userRequest.getLastName());
		myUser.setUserName(userRequest.getUserName());
		myUser.setPassword(passwordEncoder.encode(userRequest.getPassword()));
		
		Role role=roleService.getRoleByType(RoleType.ROLE_INSTRUCTOR);
		Set<Role> roles=new HashSet<>();
		roles.add(role);
		myUser.setRoles(roles);
		userRepo.save(myUser);
	}
	
	
	
	
}
