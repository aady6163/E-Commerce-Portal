package com.springboot.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.entity.Category;

@Repository
public interface CategoryDao extends CrudRepository<Category, String>{

}
