package com.springboot.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.configuration.JwtRequestFilter;
import com.springboot.dao.CartDao;
import com.springboot.dao.ProductDao;
import com.springboot.dao.UserDao;
import com.springboot.entity.Cart;
import com.springboot.entity.Product;
import com.springboot.entity.User;

@Service
public class CartService {

	@Autowired
	private CartDao cartDao;

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ProductDao productDao;
	
	public Cart addToCart(Integer productId) {
	Product product =	productDao.findById(productId).get();
	
	User user = null;
	
	String username = JwtRequestFilter.CURRENT_USER;
	if(username != null) {
		user = userDao.findById(username).get();
	}
	
	List<Cart> cartList =  cartDao.findByUser(user);
List<Cart> filterList =	cartList.stream().filter(x -> x.getProduct().getProductId() == productId).collect(Collectors.toList());
	
if(filterList.size() > 0) {
	return null;
}
	if(product != null && user != null ) {
		Cart cart = new Cart(product, user);
	return	cartDao.save(cart);
	}
	
	return null;
	}
	
	public List<Cart> getCartDetails(){
		String username = JwtRequestFilter.CURRENT_USER;
		User user = userDao.findById(username).get();
		
	return	cartDao.findByUser(user);
		
	}
	
	public void deleteCartItem(Integer cartId) {
		cartDao.deleteById(cartId);
	}
}
