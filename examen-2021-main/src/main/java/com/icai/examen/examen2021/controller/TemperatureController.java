package com.icai.examen.examen2021.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icai.examen.examen2021.service.impl.TemperatureServiceImpl;

@RestController
@RequestMapping("/api/temperatures")
public class TemperatureController {
	
	//Inyecto la dependecia a mi service de temperaturas
	
	@Autowired
	TemperatureServiceImpl temperatureServiceImpl;
	
	
	@GetMapping("/2021")
	public ResponseEntity<Float> getAverageTemperature2021(){
		
		ResponseEntity<Float> response;
		
		float averageTemp2021;
		
		averageTemp2021= temperatureServiceImpl.getAverageTemperatureFrom2021();
		
		//Si el valor por defecto del float no ha variado esque ha habido error
		
		if(averageTemp2021==0.0f) {
			response=new ResponseEntity<Float>(HttpStatus.NO_CONTENT);
		}else {
			response= new ResponseEntity<Float>(averageTemp2021, HttpStatus.OK);
		}
		
		return response;
	}
}
