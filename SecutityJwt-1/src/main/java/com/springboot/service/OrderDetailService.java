package com.springboot.service;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.springboot.configuration.JwtRequestFilter;
import com.springboot.dao.CartDao;
import com.springboot.dao.OrderDetailDao;
import com.springboot.dao.ProductDao;
import com.springboot.dao.UserDao;
import com.springboot.entity.Cart;
import com.springboot.entity.OrderDetail;
import com.springboot.entity.OrderInput;
import com.springboot.entity.OrderProductQuantity;
import com.springboot.entity.Product;
import com.springboot.entity.TransactionDetails;
import com.springboot.entity.User;

@Service
public class OrderDetailService {
	
	private static final String ORDER_PLACED ="Placed";
	
	private static final String KEY = "rzp_test_AXBzvN2fkD4ESK";
	
	private static final String KEY_SECRET = "bsZmiVD7p1GMo6hAWiy4SHSH";
	
	private static final String CURRENCY = "INR";
	
	@Autowired
	private OrderDetailDao orderDetailDao;
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private CartDao cartDao;

	public void placeOrder(OrderInput orderInput , boolean isSingleProductCheckout  ) {
	List<OrderProductQuantity> productQuantityList = orderInput.getOrderProductQuantityList();
	
	for(OrderProductQuantity o: productQuantityList ) {
		
	 Product product =	productDao.findById(o.getProductId()).get();
	 
	String currentUser = JwtRequestFilter.CURRENT_USER;
	
	User user = userDao.findById(currentUser).get();
		
		OrderDetail orderDetail = new OrderDetail(
				orderInput.getFullName(),
				orderInput.getFullAddress(),
				orderInput.getCategoryName(),
				orderInput.getContactNumber(),
				orderInput.getAlternateContact(),
				
				ORDER_PLACED,
				product.getProductDiscountedPrice()*o.getQuantity(),
				product,
				user,
				orderInput.getTransactionId());
		
		// empty the cart
		
		if(!isSingleProductCheckout) {
		List<Cart> carts =	cartDao.findByUser(user);
		carts.stream().forEach(x -> cartDao.deleteById(x.getCartId()));
		}
		
		orderDetailDao.save(orderDetail);
	}
	}

	public List<OrderDetail> getorderDetails() {
		
		 
		String currentUser = JwtRequestFilter.CURRENT_USER;
		
		User user = userDao.findById(currentUser).get();
		
	 	return orderDetailDao.findByUser(user);
		
	}
	
	public List<OrderDetail> getAllOrderDetails(String status) {
		if(status.equals("all")) {
			
			return	(List<OrderDetail>) orderDetailDao.findAll();
		}
		
		else {
		return 	orderDetailDao.findByOrderStatus(status);
		}
	}

	
	public void markOrderAsDeliverd(Integer orderId) {
	OrderDetail orderDetail =	orderDetailDao.findById(orderId).get();
	
	if(orderDetail != null) {
		orderDetail.setOrderStatus("Delivered");
		orderDetailDao.save(orderDetail);
	}
	}
	
	public TransactionDetails createTransaction(Double amount) {
	    try {
	        JSONObject jsonObject = new JSONObject();
	        jsonObject.put("amount", amount * 100); // Note the corrected key "amount"
	        jsonObject.put("currency", CURRENCY);
	        
	        RazorpayClient razorpayClient = new RazorpayClient(KEY, KEY_SECRET);
	        Order order = razorpayClient.orders.create(jsonObject);
//	        System.out.println(order);
	        
	      return  prepareTransactionDetails(order);
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    }
	    
	    return null;
	}
	
	private TransactionDetails prepareTransactionDetails(Order order) {
		String orderId = order.get("id");
		String currency = order.get("currency");
		Integer amount = order.get("amount");
		
		
		TransactionDetails transactionDetails =new TransactionDetails(orderId, currency, amount , KEY);
		
		return transactionDetails;
	}
}
