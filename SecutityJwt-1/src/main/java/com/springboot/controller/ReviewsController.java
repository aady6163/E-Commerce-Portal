package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.entity.Reviews;
import com.springboot.service.ReviewsService;

@RestController
public class ReviewsController {

	@Autowired
	private ReviewsService reviewsService;
	
	@PostMapping("/addReviews/{productId}")
	@PreAuthorize("hasRole('User')")
	public Reviews addReviews(@PathVariable("productId") Integer productId , 
			@RequestBody Reviews reviews) {
		
	return	reviewsService.addReviews(productId, reviews);
	}
	
	@GetMapping("/getReviews/{productId}")
	public List<Reviews> getReviewsByProductId(@PathVariable("productId") Integer productId) {
	return	reviewsService.getReviewsById(productId);
	}
}
