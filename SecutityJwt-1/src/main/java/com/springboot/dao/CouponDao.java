package com.springboot.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.entity.Coupon;

@Repository
public interface CouponDao extends CrudRepository<Coupon, String> {

}
