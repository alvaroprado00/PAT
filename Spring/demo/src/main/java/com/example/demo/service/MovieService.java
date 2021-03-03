package com.example.demo.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

//con la notacion service indicamos que es un objeto de la capa de negocio 

@Service
public class MovieService {

	private final static String PELICULA_DEFAULT = "Star Wars";
	
	
	//con el @Value inyectas en url el valor guardado en el fichero properties
	//movies.url= https://www.omdbapi.com/?apikey=cc1014ca&s=
	@Value("${movies.url}")
	private String url;

	public List<String> getPeliculas() {
		// HACEMOS LA PETICION A LA API REST DE OMBDB
		// NECESITAREMOS LA URL
		return Collections.singletonList(PELICULA_DEFAULT);
	}
}
