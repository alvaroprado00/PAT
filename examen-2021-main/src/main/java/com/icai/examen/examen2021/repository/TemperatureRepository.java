package com.icai.examen.examen2021.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import com.icai.examen.examen2021.model.Temperature;

//Como cualquier clase de la capa de repository será una interface que hereda de Crud Repository

public interface TemperatureRepository extends CrudRepository<Temperature, Long> {

	//En este caso haré una query específica para poder seleccionar las temperaturas del año 2021
	
	@Query("SELECT * FROM TEMPERATURES WHERE YEAR=2021")
	public List<Temperature> getTemperaturesFrom2021();
}
