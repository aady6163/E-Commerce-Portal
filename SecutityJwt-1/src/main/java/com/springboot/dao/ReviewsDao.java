package com.springboot.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.entity.Reviews;

@Repository
public interface ReviewsDao extends CrudRepository<Reviews, Integer>{

	
	public List<Reviews>  findByProductId(Integer product);
}
