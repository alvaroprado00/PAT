package com.myProject.estanco.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.myProject.estanco.model.*;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class ArticleService {

	
	@Value("${articles.url}")
	private String articlesURL;
	
	
	
	public ArticleSearchModel getAllArticles() {
		
		//Para comprobar que llega a este metodo meto un log
		
		log.debug("Estoy llegando al metodo getAllArticles");
		
		RestTemplate template = new RestTemplate();
		
		HttpMethod metodo= HttpMethod.GET;
		
		ResponseEntity<ArticleSearchModel> response =template.exchange(articlesURL,metodo, null, ArticleSearchModel.class);
		
		//Importante, si devuelves la peticion entera movida de cabcera ok y tarda mazo tiempo
		//Pasamos el body y luego pones el status a ok en controller
		return response.getBody();
		
	}
}
