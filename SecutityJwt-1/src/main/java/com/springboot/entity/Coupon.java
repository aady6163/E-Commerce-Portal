package com.springboot.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Coupon {

	@Id
	private String coupon;
	
	private int discount;

	
	
	public Coupon() {
		super();
	}


	public Coupon(String coupon, int discount) {
		super();
		this.coupon = coupon;
		this.discount = discount;
	}


	public String getCoupon() {
		return coupon;
	}


	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}


	public int getDiscount() {
		return discount;
	}


	public void setDiscount(int discount) {
		this.discount = discount;
	}
	
	
	
}
