package com.springboot.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Reviews {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer reviewsId;
    
    private Integer ratings;
    
    private String userReviewName;
    
    private String reviewDescription;
    
 
    private Integer productId;
    
    
    

	public Reviews() {
	
	}



	public Reviews(Integer ratings, String userReviewName, String reviewDescription, Integer productId) {
		super();
		this.ratings = ratings;
		this.userReviewName = userReviewName;
		this.reviewDescription = reviewDescription;
		this.productId = productId;
	}



	public Integer getReviewsId() {
		return reviewsId;
	}

	public void setReviewsId(Integer reviewsId) {
		this.reviewsId = reviewsId;
	}

	public Integer getRatings() {
		return ratings;
	}

	public void setRatings(Integer ratings) {
		this.ratings = ratings;
	}

	public String getUserReviewName() {
		return userReviewName;
	}

	public void setUserReviewName(String userReviewName) {
		this.userReviewName = userReviewName;
	}

	public String getReviewDescription() {
		return reviewDescription;
	}

	public void setReviewDescription(String reviewDescription) {
		this.reviewDescription = reviewDescription;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

   
}
