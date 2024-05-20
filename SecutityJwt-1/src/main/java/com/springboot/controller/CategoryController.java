package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.entity.Category;
import com.springboot.service.CategoryService;

@RestController
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@PreAuthorize("hasRole('Admin')")
	@PostMapping("/saveCategory")
	public Category saveCategory(@RequestBody Category category) {
	return	categoryService.saveCategory(category);
	}
	
	@GetMapping("/getAllCategory")
	public List<Category> getAllCategory() {
	return	categoryService.getAllCategory();
	}
}
