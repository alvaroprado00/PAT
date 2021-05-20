package com.example.demo.repository;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Car;

//Hay que meter este import static que si no no va
import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest
public class CarRepositoryTest {
	
	//Lo primero que hay que hacer es inyectar el repo
	
	@Autowired
	CarRepository carRepo;
	
	
	//Ahora puedo hacer un Test
	
	//Le estoy diciendo que busque la info a inyectar en el script sql
	//Al acabar el test gracias a @Transactional lo deja todo igual
	
	@Test
	@Sql(scripts="/myTestScript.sql")
	@Transactional
	public void given_repository_when_get_all_cars_then_ok() {
		
		//Given seria el autowired
		
		//When
		Iterable<Car> cars= carRepo.findAll();
		
		Iterator<Car> it= cars.iterator();
		
		ArrayList<Car> carsList= new ArrayList<>();
		
		while(it.hasNext()) {
			carsList.add(it.next());
		}
		
		then(carsList.size()).isEqualTo(5);
		
	}
	

}
