package com.order.it.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.order.it.dto.CartDTO;
import com.order.it.entity.Cart;
import com.order.it.entity.Products;
import com.order.it.entity.Stock;
import com.order.it.repository.CartRepository;
import com.order.it.repository.StockRepo;
import com.order.it.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService ps;
	
	@Autowired
	private ModelMapper mp;
	
	@Autowired
	private StockRepo sr;
	
	Logger logger = LogManager.getLogger(ProductController.class);
	
	@GetMapping("/allProducts")
	public List<Products> getAllProducts() {
		
		return ps.getAllProducts();
	}
	
	@PostMapping("/addToCart")
	public ResponseEntity<String> addToCart(CartDTO cartDto) {
		
		Cart cart = mp.map(cartDto, Cart.class);
		// mobileNo is not getting mapped - To do
		cart.getId().setMobileNo(getMobileNo());
		String cartStatus = ps.save(cart);
		return new ResponseEntity<String>(cartStatus, HttpStatus.CREATED);
	}
	
	@PostMapping("/removeFromCart")
	public ResponseEntity<String> removeFromCart(CartDTO cartDto) {
		
		Cart cart = mp.map(cartDto, Cart.class);
		// mobileNo is not getting mapped - To do
		cart.getId().setMobileNo(cartDto.getMobileNo());
		boolean isDeleted = ps.removeFromCart(cart);
		
		if(isDeleted)
			return new ResponseEntity<String>("Deleted", HttpStatus.OK);
		return new ResponseEntity<String>("Failed", HttpStatus.OK);
	}
	
	@GetMapping("/viewMyCart")
	public List<CartDTO> getAllItemsFromCart() {
		
		List<Cart> cartItems = ps.getAllItemsFromCart(getMobileNo());
		List<CartDTO> cartItemsDto = new ArrayList<>();
		
		for(Cart cartItem : cartItems) {
			CartDTO cd = mp.map(cartItem, CartDTO.class);
			cd.setMobileNo(cartItem.getId().getMobileNo());
			cartItemsDto.add(cd);
		}
		return cartItemsDto;
	}
	
	// place order - copy call cart items to live_orders
	@GetMapping("/placeOrder")
	public ResponseEntity<String> placeOrder() {
		
		boolean copied = ps.copyItemsFromCartToLiveOrders(getMobileNo());
		return new ResponseEntity<String>("Order placed", HttpStatus.OK);
	}
	
	private String getMobileNo() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
	
	@PostMapping("/addImage")
	public String addImage(MultipartFile img) {
		Stock stock = new Stock();
			try {
				stock.setImage(img.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		stock.setStockId(1);
		stock.setUnitId(1);
		sr.save(stock);
		return "Inserted";
	}
	
	@GetMapping("/getMyCartItems")
	public List<Cart> getCart() {
		return ps.getAllCartItems(getMobileNo());
	}
}
