package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.dao.CouponDao;
import com.springboot.entity.Coupon;
import com.springboot.service.CouponService;

@RestController
public class CouponController {
	
	@Autowired
	private CouponService couponService;
	
	@Autowired
	private CouponDao couponDao;

	@PreAuthorize("hasRole('Admin')")
	@PostMapping("/addCoupon")
	public Coupon addCoupon(@RequestBody Coupon coupon) {
		return couponService.addCoupon(coupon);
	}
	
	@GetMapping("/getCoupon/{coupon}")
	public Coupon getCoupon(@PathVariable("coupon") String coupon) {
		return couponService.getCoupon(coupon);
	}
	@PreAuthorize("hasRole('Admin')")
	@GetMapping("/getALLCoupon")
	public List<Coupon>  getAllCoupon() {
		return (List<Coupon>) couponDao.findAll();
	}
	
	@PreAuthorize("hasRole('Admin')")
	@DeleteMapping("/deleteCoupon/{coupon}")
	public void  deleteCoupon(@PathVariable("coupon") String coupon) {
		couponDao.deleteById(coupon) ;
	}
}
