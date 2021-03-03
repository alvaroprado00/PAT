package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.MovieService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




//Indicamos que se trata de un controller tipo Rest que atiende todas las peticiones al path /api

@RestController
@RequestMapping("/api")
public class RestControllerDemo {
	
	@Autowired
	private MovieService movieService;



	//Para las peticiones GET a /hellow-world viene aqui
	@GetMapping("/hello-world")
	public ResponseEntity <String> helloWorld() {
		
		//Response Entity es un objeto de spring que emula cualquier peticion HTTP con un body y un status
		
		final String message= "{\"message\":\"Hola Mundo\"}";
		
		final ResponseEntity<String> response= new ResponseEntity<>(message, HttpStatus.OK);
		
		return response; 
		
	}
	
	//Para las peticiones tipo POST a /user/ vendra a este metodo
	//En el parametro indicamos que en el body de la peticion viene un objeto tipo User
	@PostMapping("/user/") 
	public ResponseEntity<String> createUser(@RequestBody User user){
		//Supuestamente manejariamos esta peticio ir lo meteriamos en persistence
		//Si todo hubiera ido bien ya le respondemos
		final String message="El nombre de usuario es:"+user.getUsername();
		final ResponseEntity<String> response= new ResponseEntity<> (message, HttpStatus.OK);
		
		return response;
	}
	
	
	
	//En este caso se indica que una parte del path es varible con {}
	@GetMapping("/user/{userId}")
	public ResponseEntity<User> getUser(@PathVariable String userId) {
		final User user = new User();
		user.setUsername(userId);
		return new ResponseEntity<>(user, HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	@GetMapping("/peliculas")
	public ResponseEntity<List<String>> peliculas() {
		return new ResponseEntity<>(movieService.getPeliculas(), HttpStatus.OK);
	}

	
}
