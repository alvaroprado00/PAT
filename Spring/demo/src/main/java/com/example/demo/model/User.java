package com.example.demo.model;


import lombok.Data;


//gracias a la libreria lombok podemos usar la anotacion Data

//con @Data nos crea directamente los getters//setters//toString//constructor por defecto//EqualsAndHashCode

@Data
public class User {

	private String username;
	private String password;
	private String phone;
	
}
