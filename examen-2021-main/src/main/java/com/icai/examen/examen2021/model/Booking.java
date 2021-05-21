package com.icai.examen.examen2021.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Table("BOOKINGS")
@Data
public class Booking {

	@Id
	@Column("ID")
	private Long id;

	@Column("EMAIL")
	private String email;

	@Column("START_DATE")
	private Date startDate;

	@Column("END_DATE")
	private Date endDate;

	@Column("APARTMENT")
	private String apartment;

	@Column("NUMBER_PEOPLE")
	private Integer numberOfPeople;

	@Column("PETS")
	private Boolean pets;

}
