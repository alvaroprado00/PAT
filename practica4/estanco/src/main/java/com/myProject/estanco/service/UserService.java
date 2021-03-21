package com.myProject.estanco.service;


import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.myProject.estanco.model.*;



//Tendra la logica de negocio de usuarios por eso es un service

@Service
public class UserService {
	
	
	//con la  anotacion de @value inyecto el valor desde properties
	@Value("${users.url}")
	private String usersUrl;
	
	private List<User> listaUsuarios;
	
	public ResponseEntity<UserSearchModel> getAllUsers(){
			
		//Llamo a mi fake Api y me cojo todos los usuarios para guardarmelos
	
		
		//Con este objeto spring se hacen las peticiciones HTTP
		final RestTemplate template =new RestTemplate();
		
		//Me creo un objeto que indica el metodo HTTP
		final HttpMethod metodoHttp= HttpMethod.GET;
		
		
		//Hago la llamada a la api
		//Se le indica la URL, el metodo http y la clase java a la que tiene que convertir el JSON
		//Al ser metodo GET el tercer parametro puede estar a null
		final ResponseEntity<UserSearchModel> respuesta= template.exchange(usersUrl, metodoHttp, null, UserSearchModel.class);
		
		return respuesta;
	}
	
	
	public ResponseEntity<Boolean> checkUser(User user) {
		
		//LLamo al metodo getAllUsers del servicio
		
		boolean isInApi=false;
		
		for(User u: listaUsuarios) {
			
			if (u.equalsTo(user)) {
				isInApi=true;
			}
		}
		
		//Respondo al controller con una ResponseEntity

		ResponseEntity<Boolean> respuesta= new ResponseEntity<>(isInApi, HttpStatus.OK);
		return respuesta;
	}
	
	public ResponseEntity<User> registerUser(User user){
		
		//Va a tratarse de un POST a la API por tanto tiene que haber headers
		
		RestTemplate template= new RestTemplate();
		
		HttpMethod metodo= HttpMethod.POST;
		
		//Me creo la cabecera de la peticion
		HttpHeaders headers= new HttpHeaders();
		
		headers.add("Content-Type", "application/json");
		headers.add("Accept", "application/json");
		
		//Otra manera:
		// headers.setContentType(MediaType.JSON);
		
		//Seteo el body que es el usuario que me pasan
		
		User body = user;
		
		HttpEntity<User> entidad = new HttpEntity<>(body, headers);
		
		ResponseEntity<User> response = template.exchange(usersUrl, metodo, entidad, User.class);
		

		//Ojo, que si registro un nuevo usuario, debo volver a ejecutar InicializeUsers
		inicializeUsers();
		
		return new ResponseEntity<User>(response.getBody(), HttpStatus.OK); 
		
	}

	
	//Despues de inicializar el bean me cojo todos los usuarios de la API para tenerlos ya en memoria
	@PostConstruct
	public void inicializeUsers() {
		
		ResponseEntity<UserSearchModel> responseFromGetAllUsers= this.getAllUsers();
		
		listaUsuarios=responseFromGetAllUsers.getBody().getItems();
		
	}
	
	
}
