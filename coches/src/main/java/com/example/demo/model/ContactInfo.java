package com.example.demo.model;

import org.springframework.data.relational.core.mapping.Column;

import lombok.Data;

@Data
public class ContactInfo {
	
	@Column("PHONE_NUMBER")
	private String phoneNumber;
	private String address;
	
}
