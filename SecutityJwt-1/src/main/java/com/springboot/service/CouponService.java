package com.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.dao.CouponDao;
import com.springboot.entity.Coupon;

@Service
public class CouponService {
	
	@Autowired
	private CouponDao couponDao;

	public Coupon addCoupon(Coupon coupon) {
	  return couponDao.save(coupon);
	}

	public Coupon getCoupon(String coupon) {
		
		return couponDao.findById(coupon).get();
	}
	
	
}
