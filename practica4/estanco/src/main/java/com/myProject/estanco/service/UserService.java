package com.myProject.estanco.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.myProject.estanco.model.*;


//Tendra la logica de negocio de usuarios por eso es un service

@Service
public class UserService {
	
	@Value("${users.url}")
	private String usersUrl;
	
	public UserSearchModel getAllUsers(){
			
		//Llamo a mi fake Api y me cojo todos los usuarios para guardarmelos
	
		
		//Con este objeto spring se hacen las peticiciones HTTP
		final RestTemplate template =new RestTemplate();
		
		//Me creo un objeto que indica el metodo HTTP
		final HttpMethod metodoHttp= HttpMethod.GET;
		
		
		//Hago la llamada a la api
		final ResponseEntity<UserSearchModel> respuesta= template.exchange(usersUrl, metodoHttp, null, UserSearchModel.class);
		
		return respuesta.getBody();
	}
	
	
	
	
}
