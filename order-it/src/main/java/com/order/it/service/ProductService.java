package com.order.it.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.order.it.entity.Cart;
import com.order.it.entity.LiveOrder;
import com.order.it.entity.Products;
import com.order.it.repository.CartRepository;
import com.order.it.repository.LiveOrderReopsitory;
import com.order.it.repository.ProductRepository;

@Service
@Transactional
public class ProductService {

	@Autowired
	private ProductRepository pr;
	@Autowired
	private CartRepository cr;
	@Autowired
	private LiveOrderReopsitory lor;
	@Autowired
	private ModelMapper modelMapper;
	
	public List<Products> getAllProducts() {
		return (List<Products>) pr.findAll();
	}

	public String save(Cart cart) {
		Products productInfo = pr.findById(cart.getId().getProdID()).get();
		Cart existingCartInfo = cr.findById(cart.getId()).orElse(null);
		if (existingCartInfo == null) {
			cart.setAmount(productInfo.getPricePerUnit() * cart.getQty());
		} else if (existingCartInfo.getQty() >= productInfo.getMaxQtyLimit())
			return "Already max amount in cart. can't add more";

		else if (cart.getQty() + existingCartInfo.getQty() > productInfo.getMaxQtyLimit())
			return "total quantity exceeds max limit";
		else {
			cart.setAmount(productInfo.getPricePerUnit() * cart.getQty() + existingCartInfo.getAmount());
			cart.setQty(existingCartInfo.getQty() + cart.getQty());
		}
		cr.save(cart);
		return "Added to cart";
	}

	public boolean removeFromCart(Cart cart) {

		Cart cartRow = cr.findById(cart.getId()).orElse(null);
		if (cartRow == null || (cartRow.getQty() - 1) <= 0) {
			cr.delete(cartRow);
			return true;
		}
		cartRow.setQty(cartRow.getQty() - 1);
		cr.save(cartRow);
		return true;
	}

	public List<Cart> getAllItemsFromCart(String mobileNo) {

		// findByEmployeeIdentityCompanyId
		return cr.findByIdMobileNo(mobileNo);
	}

	public boolean copyItemsFromCartToLiveOrders(String mobileNo) {

		List<Cart> cartItems = cr.findByIdMobileNo(mobileNo);
		List<LiveOrder> items = new ArrayList<>();
		Type listType = new TypeToken<List<LiveOrder>>(){}.getType();
		items = modelMapper.map(cartItems, listType);
		// save to live_orders and delete them from cart
		lor.saveAll(items);
		cr.deleteAll(cartItems);
		return true;
	}

	public void addProduct(Products p) {
		
		
	}

	public List<Cart> getAllCartItems(String mobileNo) {
		// TODO Auto-generated method stub
		return cr.findByIdMobileNo(mobileNo);
	}

}
