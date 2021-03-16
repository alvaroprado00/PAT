package com.myProject.estanco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myProject.estanco.model.User;
import com.myProject.estanco.model.UserSearchModel;
import com.myProject.estanco.service.UserService;



//Indico con anotaciones que es un controller tipo REST y que va a atender las peticiones a /api
@RestController
@RequestMapping("/api")
public class RestControllerDemo {
	
	
	@Autowired
	private UserService userService;
	
	
	
	@PostMapping("/users")
	public ResponseEntity<Boolean> checkUser(@RequestBody User user){
		
		UserSearchModel userSearch =userService.getAllUsers();
		List<User> listaUsuarios= userSearch.getItems();
		boolean isInApi=false;
		
		for(User u: listaUsuarios) {
			
			if (u.equalsTo(user)) {
				isInApi=true;
			}
		}
		
		ResponseEntity<Boolean> respuesta= new ResponseEntity<>(isInApi, HttpStatus.OK);
		
		return respuesta;
	}
	
}
