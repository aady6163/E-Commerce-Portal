package com.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.dao.CategoryDao;
import com.springboot.entity.Category;

@Service
public class CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    public Category saveCategory(Category category) {
        Category category2 = new Category();
        category2.setCategoryName(category.getCategoryName());
        category2.setCategoryDescription(category.getCategoryDescription());

        // Ensure categoryName is set before saving
        return categoryDao.save(category2);
    }
    
    public List<Category> getAllCategory() {
    return	(List<Category>) categoryDao.findAll();
    }
}