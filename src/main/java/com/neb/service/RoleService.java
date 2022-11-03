package com.neb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neb.domain.Role;
import com.neb.domain.enums.RoleType;
import com.neb.exception.ResourceNotFound;
import com.neb.repository.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	public Role getRoleByType(RoleType roleType) {
		Role role=roleRepository.findByType(roleType).orElseThrow(()->new ResourceNotFound("Role not found: "+roleType.name()));
		return role;
	}

}
