package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CarReference;
import com.example.demo.model.LoginRequest;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	UserService userServiceImpl; 

	@PostMapping("/register")
	public ResponseEntity<User> registerNewUser(@RequestBody User u){
	   
		ResponseEntity<User> response;
		
		User userResponse= userServiceImpl.registerUser(u);
		
		if(userResponse!=null) {
			response= new ResponseEntity<>(userResponse, HttpStatus.OK);
		}else {
			response= new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return response;
		
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody LoginRequest lr){
		
		ResponseEntity<User> response;
		
		User userResponse= userServiceImpl.loginUser(lr);
		
		if(userResponse!=null) {
			response= new ResponseEntity<>(userResponse, HttpStatus.OK);
		}else {
			response= new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		return response;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> addCarReference(@PathVariable("id")long id, @RequestBody CarReference cr){
		
		ResponseEntity<User> response;
		
		User userResponse= userServiceImpl.addCarReference(id, cr);
		
		if(userResponse!=null) {
			response= new ResponseEntity<>(userResponse, HttpStatus.OK);
		}else {
			response= new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		return response;
	}
	
}
