package com.kryptos.hrms.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UpcomingBday {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private LocalDate birthday;
	
	public UpcomingBday() {
		// TODO Auto-generated constructor stub
	}

	public UpcomingBday(Long id, String name, LocalDate birthday) {
		super();
		this.id = id;
		this.name = name;
		this.birthday = birthday;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getbDay() {
		return birthday;
	}

	public void setbDay(LocalDate birthday) {
		this.birthday = birthday;
	}
	
	
}
