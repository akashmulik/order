package com.order.it.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.it.conf.JwtTokenUtil;
import com.order.it.entity.User;
import com.order.it.returncode.ReturnCode;
import com.order.it.service.OtpService;
import com.order.it.service.UserService;

@RestController
public class UserController {

	private static final Logger LOGGER = LogManager.getLogger(UserController.class);
	@Autowired
	private UserService us;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JwtTokenUtil jwtUtil;
	@Autowired
	private OtpService otpService;
	@Autowired
	private Environment env;
	
	@PostMapping("/createUser")
	public ReturnCode signup(User user) {
		
		String generatedOtp = Integer.toString(otpService.getOtp(user.getMobileNo()));
		
		if(generatedOtp == null || !generatedOtp.equals(user.getOtp())) {
			return new ReturnCode(1008, "Entered OTP is incorrect/expired", null);
		}
		try {
			user.setPassword("secret");
			user.setAddress("Please edit and type delivery address here");
			User savedUser = us.save(user);
		//	savedUser.setPassword("secret");
			// on successful signup, generate and return token and redirect user to products page
			String token = getTokenAfterSignupSuccess(savedUser);
		//	logger.info(token);
			String [] data = new String[] {token}; //token
			return new ReturnCode(1007, "user created.", data);
		} catch (Exception e) {
			return new ReturnCode(1008, "System error. Please try again in some time", null);
		}
	}

	@PostMapping("/generateOtp")
	public ReturnCode generateOTP(User user) {
		
		User u = us.findById(user.getMobileNo()).orElse(null);
		if(u != null) {
			return new ReturnCode(1008, "Number already exist.", null);
		}
		otpService.generateOTP(user.getMobileNo());
		return new ReturnCode(1007, "OTP sent. Valid for next "+ env.getProperty("otp.validFor") +" minutes", null);
	}
	@PostMapping("/generateOtpForLogin")
	public ReturnCode generateOtpForLogin(User user) {
		
		User u = us.findById(user.getMobileNo()).orElse(null);
		if(u == null) {
			return new ReturnCode(1008, "Number not exist. Please signup or check if number is correct", null);
		}
		otpService.generateOTP(user.getMobileNo());
		return new ReturnCode(1007, "OTP sent. Valid for next "+ env.getProperty("otp.validFor") +" minutes", null);
	}
	
	@PostMapping(value="/authenticate")
	public ResponseEntity<?> generateJWT(User user) {
		
		// verify OTP
		String generatedOtp = Integer.toString(otpService.getOtp(user.getMobileNo()));

		if (generatedOtp == null || !generatedOtp.equals(user.getOtp())) {
			return new ResponseEntity<String>("Entered OTP is incorrect/expired", HttpStatus.UNAUTHORIZED);
		}
		// we reached here means otp is verified
		try {
	//		String username = user.getMobileNo();
	//		authManager.authenticate(new UsernamePasswordAuthenticationToken(username, user.getPassword()));
			User userFromDB = us.findById(user.getMobileNo())
					.orElseThrow(() -> new UsernameNotFoundException("Please enter correct/registered mobile No"));
			
			if(!userFromDB.isEnabled())
				throw new UsernameNotFoundException("User is disbled for limited time period " + user.getMobileNo());
		
		} catch(Exception e) {
			LOGGER.error(e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getMobileNo());
		final String token = jwtUtil.generateToken(userDetails);
		return new ResponseEntity<String>(token, HttpStatus.OK);
	}
	@GetMapping("/hello")
	public String greeting() {
		return "Hello World";
	}

	private String getTokenAfterSignupSuccess(User user) {
		final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getMobileNo());
		final String token = jwtUtil.generateToken(userDetails);
		return token;
	}
	
	@GetMapping("/getCustAddress")
	public ResponseEntity<String> getCustAddress() {
		
		String addrs = us.getCustAddress(getMobileNo());
		return new ResponseEntity<String>(addrs, HttpStatus.OK);
	}
	@PostMapping("/updateAddrs")
	public ResponseEntity<String> updateAddrs(User u) {
		u.setMobileNo(getMobileNo());
		String returnVal = us.updateAddrs(u);
		return new ResponseEntity<>(returnVal, HttpStatus.OK);
	}
	
	private String getMobileNo() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
	
	@GetMapping("/page/getRoles")
	public String getRoles() {
		if(hasRole("ROLE_SELLER")) //if seller then redirect to pending orders page
			return "/page/pendingOrdersPage";
		return "/page/productsPage"; //else products page
	}
	
	private static boolean hasRole(String roleName) {
		return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
				.anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(roleName));
	}

	@GetMapping("/isTokenValid")
	public boolean isTokenValid() { //if request reaches here means token is valid. no other logic here
		return true;
	}
}