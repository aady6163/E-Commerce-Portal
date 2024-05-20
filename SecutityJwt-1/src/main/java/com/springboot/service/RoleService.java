package com.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.dao.RoleRepository;
import com.springboot.entity.Role;

@Service
public class RoleService {

	@Autowired
	private  RoleRepository roleRepository;
	
	public Role createNewRole(Role role) {
	return	roleRepository.save(role);
	}
}
