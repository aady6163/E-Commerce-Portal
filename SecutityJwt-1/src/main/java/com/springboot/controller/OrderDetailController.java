package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.entity.OrderDetail;
import com.springboot.entity.OrderInput;
import com.springboot.entity.TransactionDetails;
import com.springboot.service.OrderDetailService;

@RestController
public class OrderDetailController {
	
	@Autowired
	private OrderDetailService orderDetailService;

	@PreAuthorize("hasRole('User')")
	@PostMapping("/placeOrder/{isSingleProductCheckout}")
	public void placeOrder(@PathVariable("isSingleProductCheckout") boolean isSingleProductCheckout ,
			@RequestBody OrderInput orderInput) {
		orderDetailService.placeOrder(orderInput , isSingleProductCheckout );
	}
	
	@PreAuthorize("hasRole('User')")
	@GetMapping("/getOrderDetails")
	public List<OrderDetail> getOrderDetails() {
	return	orderDetailService.getorderDetails();
	}
	
	@PreAuthorize("hasRole('Admin')")
	@GetMapping("/getAllOrderDetails/{status}")
	public List<OrderDetail> getAllOrderDetails(@PathVariable("status") String status) {
	return	orderDetailService.getAllOrderDetails(status);
	}
	
	@PreAuthorize("hasRole('Admin')")
	@GetMapping("/markOrderAsDelivered/{orderId}")
	public void markOrderAsDelivered( @PathVariable("orderId") Integer orderId) {
		orderDetailService.markOrderAsDeliverd(orderId);
	}
	
	@PreAuthorize("hasRole('User')")
	@GetMapping("/createTransaction/{amount}")
	public TransactionDetails createTransaction(@PathVariable("amount") Double amount) {
	return	orderDetailService.createTransaction(amount);
	}
}
