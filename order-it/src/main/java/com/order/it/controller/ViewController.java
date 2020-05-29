package com.order.it.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

	@GetMapping("/page/productsPage")
	public String loadProductsPage() {
		return "products";
	}
	
	@GetMapping("/page/signupPage")
	public String loadSignupPage() {
		return "signup";
	}
	
	@GetMapping("/page/imagePage")
	public String insertImagePage() {
		return "insertImage";
	}
	
	@GetMapping("/page/loginPage")
	public String loginPage() {
		return "login";
	}
	
	@GetMapping("/page/cartPage")
	public String cartPage() {
		return "cart";
	}
	@GetMapping("/page/orderSummaryPage")
	public String orderSummaryPage() {
		return "orderSummary";
	}
	@GetMapping("/page/liveOrdersPage")
	public String liveOrdersPage() {
		return "myorders";
	}
//	@Secured("ROLE_SELLER")
	@GetMapping("/page/pendingOrdersPage")
	public String pendingOrdersPage() {
		return "pendingOrders";
	}
	@GetMapping("/page/loginSuccess")
	public String loginSuccess() {
		return "loginSuccess";
	}
	
}
