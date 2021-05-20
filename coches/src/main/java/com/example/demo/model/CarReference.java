package com.example.demo.model;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("CAR_REFERENCES")
public class CarReference {
	
	@Column("CAR_TOKEN")
	private long carToken; 

}
