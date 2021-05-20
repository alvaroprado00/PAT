package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Car;

public interface CarService {
	
	public List<Car> getAllCars();
	
	public Car getCarById(long carId);
	
	public Car createCar(Car carToCreate);
	
	public boolean updateCarModel(long carId, String carModel);
	
	public List<Car> getCarsByBrand(String brand);
	
	public List<Car> getCarsOrderedByPrice();
	
	public boolean deleteCarById(long id);
}
