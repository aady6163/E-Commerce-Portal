package com.springboot.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, String>{

}
