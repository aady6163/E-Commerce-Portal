package com.springboot.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.entity.User;

@Repository
public interface UserDao extends CrudRepository<User, String> {

}
