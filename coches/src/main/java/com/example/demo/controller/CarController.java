package com.example.demo.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Car;
import com.example.demo.service.CarService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/cars")
public class CarController {
	
	@Autowired
	CarService carServiceImpl;
	
	@GetMapping
	public ResponseEntity<List<Car>> getAllCars(){
		
		//Endpoint para getear todos los coches
		
		List<Car> cars= carServiceImpl.getAllCars();
		
		ResponseEntity<List<Car>> response;
		
		if(cars.size()==0) {
			response= new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			response= new ResponseEntity<>(cars, HttpStatus.OK);
		}
		
		return response;
	}
	
	@GetMapping("/brand")
	public ResponseEntity<List<Car>> getCarByBrand(@RequestParam("brand")String brand){
		
		List<Car> carsByBrand= carServiceImpl.getCarsByBrand(brand);
		
		ResponseEntity<List<Car>> response;
		
		if(carsByBrand.size()==0) {
			response= new ResponseEntity<List<Car>>(HttpStatus.NO_CONTENT);
			
		}else {
			response= new ResponseEntity<List<Car>>(carsByBrand,HttpStatus.OK);
		}
		
		return response;
	}
	
	@GetMapping("/orderedbyprice")
	public ResponseEntity<List<Car>> getCarsOrderedByPrice(){
		
		List<Car> carsOrdered= carServiceImpl.getCarsOrderedByPrice();
		
		ResponseEntity<List<Car>> response;
		
		if(carsOrdered.size()==0) {
			response= new ResponseEntity<List<Car>>(HttpStatus.NO_CONTENT);
			
		}else {
			response= new ResponseEntity<List<Car>>(carsOrdered,HttpStatus.OK);
		}
		
		return response;
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Car> getCarById(@PathVariable("id") long carId){
		
		//Endpoint para getear un coche por su id
		
		Car car= carServiceImpl.getCarById(carId);
		
		ResponseEntity<Car> response;
		
		if(car==null) {
			response= new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			response= new ResponseEntity<>(car, HttpStatus.OK);
		}
		
		return response;
	}
	
	@PostMapping
	public ResponseEntity<Car> createCar(@RequestBody @Valid Car car){
		//Creacion de nuevos objetos
		
		Car carResponse= carServiceImpl.createCar(car);
		
		ResponseEntity<Car> response;
		
		if(carResponse==null) {
			response= new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			response= new ResponseEntity<>(carResponse, HttpStatus.CREATED);
		}
		
		return response;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Car> updateCarModel(@PathVariable("id") long carId, @RequestParam("newModel") String newModel){
		
		ResponseEntity<Car> response;
		
		//En este caso modificamos el modelo de uno de los coches de la tienda
		if(carServiceImpl.updateCarModel(carId, newModel)) {
			response= new ResponseEntity<>(HttpStatus.OK);
		}else {
			response= new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}
		
		return response;
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Car> deleteCarById(@PathVariable("id") long carId){
		
		ResponseEntity<Car> response;
		
		boolean deleted= carServiceImpl.deleteCarById(carId);
		
		if(deleted) {
			
			response=new ResponseEntity<Car> (HttpStatus.OK);
		}else {
			response=new ResponseEntity<Car> (HttpStatus.NOT_FOUND);
		}
		
		return response;
	
	}
	
	@PostConstruct
	public void init() {
		log.debug("Se ha levantado el restController de coches");
	}
	

}
