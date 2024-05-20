package com.springboot.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.entity.OrderDetail;
import com.springboot.entity.Product;
import com.springboot.entity.User;

@Repository
public interface OrderDetailDao extends CrudRepository<OrderDetail, Integer>{

	public List<OrderDetail>  findByUser(User user);
	
	public List<OrderDetail>  findByOrderStatus(String orderStatus);

	public List<OrderDetail> findByProduct(Product product);
}
