package com.springboot.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.entity.Cart;
import com.springboot.entity.User;

@Repository
public interface CartDao extends CrudRepository<Cart, Integer> {

	public List<Cart> findByUser(User user);
}
