package com.neb.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.neb.dto.request.LoginRequest;
import com.neb.dto.request.RegisterRequest;
import com.neb.dto.response.LoginResponse;
import com.neb.dto.response.MyResponse;
import com.neb.security.SecurityUtils;
import com.neb.security.jwt.JwtUtils;
import com.neb.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class UserJWTController {

	private UserService userService;
	private AuthenticationManager authManager;
	private JwtUtils jwtUtils;
	
	@GetMapping("/welcome")
	public String welcome() {
		
//		UserDetails userDetails=(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		String userName=SecurityUtils.getCurrentUserLogin().orElseThrow(()->new UsernameNotFoundException("Username not found!"));
		
		
		return "Welcome to secure area "+userName;
		
	}
	
	@PostMapping("/register")
	public ResponseEntity<MyResponse> registerUser(@Valid @RequestBody RegisterRequest registerRequest ){
		userService.saveUser(registerRequest);
		MyResponse response=new MyResponse("User Registered Successfully", true);
		
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginRequest loginRequest){
		
		UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword());
		Authentication authenticated=authManager.authenticate(authentication);
		
		UserDetails userDetails=(UserDetails)authenticated.getPrincipal();
		
		String token=jwtUtils.generateToken(userDetails);
		
		LoginResponse loginResponse= new LoginResponse(token);
		return new ResponseEntity<>(loginResponse, HttpStatus.CREATED);
		

				
	}
	
	
}
