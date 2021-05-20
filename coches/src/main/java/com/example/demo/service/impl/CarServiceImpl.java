package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Car;
import com.example.demo.repository.CarRepository;
import com.example.demo.service.CarService;

@Service
public class CarServiceImpl implements CarService {

	//Inyecto la dependencia del repositorio de coches
	@Autowired
	CarRepository carRepo;
	
	@Override
	public List<Car> getAllCars() {
		
		//Por defecto este metodo devuelve un iterable que pasaremos a list
		Iterable<Car> cars= carRepo.findAll();
		
		Iterator<Car> it= cars.iterator();
		
		ArrayList<Car> carsList= new ArrayList<Car>();
		
		while(it.hasNext()) {
			carsList.add(it.next());
		}
		
		return carsList;
	}

	@Override
	public Car getCarById(long carId) {
		
		//Optional es un objeto que te wrapea el object Car con mas funcionalidades
		
		Optional<Car> car= carRepo.findById(carId);
		
		if(car.isPresent()) {
			return car.get();
		}else {
			return null;
		}
	}

	@Override
	public Car createCar(Car carToCreate) {
		
		return carRepo.save(carToCreate);
		
	}

	@Override
	public boolean updateCarModel(long carId, String carModel) {
		
		int i=carRepo.updateModelbyId(carModel, carId);
		
		if(i==0) {
			//Entonces es que no se ha modificado ninguna tupla
			return false;
		}else {
			return true;
		}
	}

	@Override
	public List<Car> getCarsByBrand(String brand) {
		
		List<Car> carsByBrand= carRepo.findCarsByBrand(brand);
		
		return carsByBrand;
		
	}

	@Override
	public List<Car> getCarsOrderedByPrice() {
		
		return carRepo.findCarsOrderedByPrice();
	}

	@Override
	public boolean deleteCarById(long id) {
		
		carRepo.deleteById(id);
		boolean exists= carRepo.existsById(id);
		return !exists;
	}
	
	
}	
