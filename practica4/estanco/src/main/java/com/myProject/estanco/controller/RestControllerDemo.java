package com.myProject.estanco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myProject.estanco.model.ArticleSearchModel;
import com.myProject.estanco.model.User;
import com.myProject.estanco.service.ArticleService;
import com.myProject.estanco.service.UserService;

import lombok.extern.slf4j.Slf4j;



//Indico con anotaciones que es un controller tipo REST y que va a atender las peticiones a /api
@Slf4j
@RestController
@RequestMapping("/api")
public class RestControllerDemo {
	
	
	//Inyecto dependencias de los servicios
	@Autowired
	private UserService userService;
	
	@Autowired
	private ArticleService articleService;
	
	
	
	@PostMapping("/users/login")
	public ResponseEntity<Boolean> checkUser(@RequestBody User user){
		log.debug("Llego a checkUsers en el controller");
		ResponseEntity<Boolean> response=userService.checkUser(user);
		return response;
	}
	
	@GetMapping("/articles")
	public ResponseEntity <ArticleSearchModel> getArticles(){
		
		ArticleSearchModel articulos= articleService.getAllArticles();
		
		ResponseEntity<ArticleSearchModel> response= new ResponseEntity<>(articulos, HttpStatus.OK);
		log.debug("vuelvo del service de articulos");
		return response;
	}
	
	
	@PostMapping("/users/register")
	public ResponseEntity<User> register(@RequestBody User user){
		
		log.debug("Metodo register del controller");
		ResponseEntity<User> responseFromService= userService.registerUser(user);
		return responseFromService;		
	}

}
