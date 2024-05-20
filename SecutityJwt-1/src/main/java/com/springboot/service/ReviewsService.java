package com.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.dao.ProductDao;
import com.springboot.dao.ReviewsDao;
import com.springboot.entity.Product;
import com.springboot.entity.Reviews;

@Service
public class ReviewsService {

	@Autowired
	private ReviewsDao reviewsDao;
	
	@Autowired
	private ProductDao productDao;
	
	public Reviews addReviews(Integer productId , Reviews reviews) {
	
		Product product = productDao.findById(productId).get();
		
		if(product != null) {
		Reviews reviews2 = new Reviews(
				reviews.getRatings() ,
				reviews.getUserReviewName() ,
				reviews.getReviewDescription()
				,productId);
		
	
		
		return reviewsDao.save(reviews2);
		}
		return null;
		
	}
	
	public List<Reviews> getReviewsById(Integer productId) {
		
Product product = productDao.findById(productId).get();
		
		if(product != null) {

			return reviewsDao.findByProductId(productId);
		}
		return null;
}
}
