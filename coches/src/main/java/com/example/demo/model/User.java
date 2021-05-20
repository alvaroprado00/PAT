package com.example.demo.model;


import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Embedded.OnEmpty;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

//Usamos la anotacion @Data para poder tener setters getters y constructor por defecto de Lombok
@Data
@Table("USERS")
public class User {
	
	@Id
	@Column("USER_ID")
	private long userId;
	
	@Column("USER_NAME")
	private String userName;
	
	private String password;
	
	@Column("FIRST_NAME")
	private String firstName;
	
	@Column("LAST_NAME")
	private String lastName;

	
	@MappedCollection(idColumn="USER_ID")
	Set<CarReference> rentedCars;
	
	@Embedded(onEmpty=OnEmpty.USE_EMPTY)
	private ContactInfo contactInfo;
}
