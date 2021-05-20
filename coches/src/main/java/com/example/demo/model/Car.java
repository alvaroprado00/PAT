package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table("CARS")
@AllArgsConstructor
@NoArgsConstructor
public class Car {
	
	@Id
	@Column("CAR_ID")
	private long carId;
	private String brand;
	private String model;
	private int price;

	

}
