package com.springboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.configuration.JwtRequestFilter;
import com.springboot.dao.CartDao;
import com.springboot.dao.OrderDetailDao;
import com.springboot.dao.ProductDao;
import com.springboot.dao.UserDao;
import com.springboot.entity.Cart;
import com.springboot.entity.OrderDetail;
import com.springboot.entity.Product;
import com.springboot.entity.User;

@Service
public class ProductService {
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private CartDao cartDao;
	
	@Autowired
	private OrderDetailDao orderDetailDao;

	public Product addNewProduct(Product product) {
  return	productDao.save(product);
	
	}
	
	
	public List<Product> getAllProducts(int pageNumber , String searchKey){
		Pageable pageable = PageRequest.of(pageNumber, 6);
		
		if(searchKey.equals("")) {
			
			return (List<Product>) productDao.findAll(pageable);
		}else {
	return	 (List<Product>)productDao.findByProductNameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCase
		 (searchKey, searchKey, pageable);
		}
	}
	
	
	public void deleteProductDetails(Integer productId) {
	    // Retrieve the product
	    Product product = productDao.findById(productId).orElse(null);
	    
	    if (product != null) {
	        // Retrieve order details associated with the product
	        List<OrderDetail> orderDetails = orderDetailDao.findByProduct(product);
	        
	        // Dissociate the product from order details
	        for (OrderDetail orderDetail : orderDetails) {
	            orderDetail.setProduct(null); // Dissociate the product
	            orderDetailDao.save(orderDetail); // Save the updated order detail
	        }
	        
	        // Delete the product
	        productDao.deleteById(productId);
	    }
	}

	
	public Product getProductDetailsById(Integer productId) {
	 return	productDao.findById(productId).get();
	}
	
	public List<Product> getProductDetails(boolean isSingleProductCheckout ,  Integer productId) {
		if(isSingleProductCheckout  && productId != 0) {
		
			List<Product> list = new ArrayList<>();
		Product product = 	productDao.findById(productId).get();
		
		list.add(product);
		
		return list;
		}else {
			// checkout entire cart 
			
		String username =	JwtRequestFilter.CURRENT_USER;
		User user = userDao.findById(username).get();
		
		List<Cart> carts = cartDao.findByUser(user);
		
	return	carts.stream().map( x -> x.getProduct()).collect(Collectors.toList());
		
		
		}
		
		
	}
	
	
	public List<Product> getProductByCategory(String categotyaName) {
	return	productDao.findByCategoryName(categotyaName);
	}
}
