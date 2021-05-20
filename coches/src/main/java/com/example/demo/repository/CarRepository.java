package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Car;

// Hereda de Crud Repository indicamos que la entity es un objeto java Car identificado por un id del tipo String 

public interface CarRepository extends CrudRepository<Car, Long> {

	//OJO que si no pones @Modifying no va por ser un update
	
	@Modifying
	@Transactional
	@Query("UPDATE CARS SET MODEL= :newModel WHERE CAR_ID= :carId")
	public int updateModelbyId(@Param("newModel") String newModel, @Param("carId") long carId);
	
	@Query("SELECT * FROM CARS WHERE BRAND= :brand")
	public List<Car> findCarsByBrand(@Param("brand") String brand);
	
	@Query("SELECT * FROM CARS ORDER BY PRICE DESC")
	public List<Car> findCarsOrderedByPrice();
}
