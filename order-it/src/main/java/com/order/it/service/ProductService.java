package com.order.it.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.order.it.controller.ProductController;
import com.order.it.entity.Cart;
import com.order.it.entity.LiveOrder;
import com.order.it.entity.Products;
import com.order.it.entity.Stock;
import com.order.it.exception.OutOfStockException;
import com.order.it.repository.CartRepository;
import com.order.it.repository.LiveOrderReopsitory;
import com.order.it.repository.ProductRepository;
import com.order.it.repository.StockRepo;
import com.order.it.returncode.ReturnCode;
import com.order.it.utils.GeneralUtilities;

@Service
@Transactional
public class ProductService {

	private static final Logger LOGGER = LogManager.getLogger(ProductController.class);
	@Autowired
	private ProductRepository pr;
	@Autowired
	private CartRepository cr;
	@Autowired
	private LiveOrderReopsitory lor;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private GeneralUtilities gu;
	@Autowired
	private StockRepo sr;
	
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
		try {
			Cart cartRow = cr.findById(cart.getId()).orElse(null);
			if (cartRow == null || (cartRow.getQty() - cart.getQty()) <= 0) {
				cr.delete(cartRow);
				return true;
			}
			cartRow.setQty(cartRow.getQty() - cart.getQty());
			cr.save(cartRow);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public List<Cart> getAllItemsFromCart(String mobileNo) {

		// findByEmployeeIdentityCompanyId
		return cr.findByIdMobileNo(mobileNo);
	}

	public ReturnCode copyItemsFromCartToLiveOrders(String mobileNo) {

		List<Cart> cartItems = cr.findByIdMobileNo(mobileNo);
		if(cartItems == null || cartItems.size()==0)
			return new ReturnCode(1008, "ahmm..cart is empty");
		
		// if customer already has live order, no more order allowed
		List<LiveOrder> liveOrder = lor.findByIdMobileNo(mobileNo);
		if(liveOrder!=null && liveOrder.size()>0)
			return new ReturnCode(1008, "Sorry! Your previous orderd is not completed yet.");
			
		// need to update stock quantities
		List<Stock> updatedStocks;
		try {
			updatedStocks = gu.deductOrderedQtyFromStock(cartItems);
		} catch (OutOfStockException e) {
			return new ReturnCode(1008, e.getMessage(), null);
		}
		
		List<LiveOrder> loItems = new ArrayList<>();
		int oId = lor.getMaxOrderId() + 1; // max oid in table + 1. remember that we allow only one request execute this
											// order at a tiem hence wont be data conflict/curruption
		for (Cart cartItem : cartItems) {
			LiveOrder lo = modelMapper.map(cartItem, LiveOrder.class);
			//set date time
			lo.setOrderPlacedOn(gu.getCurrentDateString());
			lo.setPricePerUnit(cartItem.getProduct().getPricePerUnit());
			lo.setAmount(cartItem.getQty()*cartItem.getProduct().getPricePerUnit());
			lo.setOrderId(oId);
			loItems.add(lo);
		}
		// save to live_orders and delete them from cart
		Iterable<LiveOrder> placedOrder = lor.saveAll(loItems);
		// delete from cart if saved to live_order table
		if(placedOrder != null) {
			cr.deleteAll(cartItems);
			for(Stock stock : updatedStocks)
				sr.updateStockQty(stock.getTotalQty(), stock.getStockId());
			
			return new ReturnCode(1007, "Order placed successfully");
		}
		return new ReturnCode(1008, "Error occured. Please contact support");
	}

	public void addProduct(Products p) {
		
		
	}

	public List<Cart> getAllCartItems(String mobileNo) {
		// TODO Auto-generated method stub
		return cr.findByIdMobileNo(mobileNo);
	}

	public List<LiveOrder> getLiveOrders(String mobileNo) {
		// TODO Auto-generated method stub
		return lor.findByIdMobileNo(mobileNo);
	}

	// this method is for seller
	public List<List<LiveOrder>> getPendingOrders() {
		// TODO Auto-generated method stub
		List<LiveOrder> orders = lor.findAllByOrderByOrderIdAsc(); //all placed orders
		// map order id to all items in that particular order
		List<List<LiveOrder>> ordMap = new ArrayList<List<LiveOrder>>();
		
		List<LiveOrder> aOrder = new ArrayList<>(); //temp variables
		int oid = 0;
		
		for(int i=0; i<orders.size(); i++) {
			if(oid==0 || orders.get(i).getOrderId()==oid) {
				aOrder.add(orders.get(i));
				oid = orders.get(i).getOrderId();
			}
			if((i+1)==orders.size() || orders.get(i+1).getOrderId()!=oid) {
				ordMap.add(aOrder);
				aOrder = new ArrayList<>();
				oid=0;
			}
		}
		return ordMap;
	}

}
