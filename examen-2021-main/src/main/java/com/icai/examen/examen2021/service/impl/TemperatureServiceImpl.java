package com.icai.examen.examen2021.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icai.examen.examen2021.model.Temperature;
import com.icai.examen.examen2021.repository.TemperatureRepository;
import com.icai.examen.examen2021.service.TemperatureService;

//Indico a Spring que es un servicio
@Service
public class TemperatureServiceImpl implements TemperatureService{

	//Inyecto la dependencia de mi repositorio
	@Autowired
	TemperatureRepository temperatureRepo;
	
	@Override
	public float getAverageTemperatureFrom2021() {
		
		//Se trata del servicio por tanto aqui esta la lógica de calcular la temperatura media.
		
		float acumulatedSum;
		
		//En forma de lista me traigo del repositorio todas las temperaturas del 2021
		List<Temperature> temperaturesList= temperatureRepo.getTemperaturesFrom2021();
		
		acumulatedSum=0;
		for(Temperature t: temperaturesList) {
			acumulatedSum=acumulatedSum+t.getValue();
		}
		
		acumulatedSum= (acumulatedSum/(float)(temperaturesList.size()));
		
		return acumulatedSum;
	}

}
