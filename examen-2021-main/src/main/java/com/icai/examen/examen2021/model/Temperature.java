package com.icai.examen.examen2021.model;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

//Garantizo el mapeo a la tabla de temperaturas de la base de datos y utilizo lombok para getters setters y constructor por defecto
@Table("TEMPERATURES")
@Data
public class Temperature {
	
	//Indico con anotaciones de validación que parametros no pueden ser nulos
	
	//Establezco el id utilizado en la tabla 
	@NotNull
	@Id
	private long id;
	
	private float value;
	
	@NotNull
	private String month;

	//Al ser un nombre compuesto me aseguro de que se asocie a la columna
	@NotNull
	@Column("MONTH_OF_YEAR")
	private int monthOfYear;
	
	@NotNull
	private int year;
	
}
