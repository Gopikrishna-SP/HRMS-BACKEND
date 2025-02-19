package com.kryptos.hrms.response;

import java.time.LocalDate;

public class UpcomingBdayResponseDTO {
	
	private Long id;
	
	private String name;
	
	private LocalDate birthday;
	
	public UpcomingBdayResponseDTO() {
		// TODO Auto-generated constructor stub
	}

	public UpcomingBdayResponseDTO(Long id, String name, LocalDate birthday) {
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

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	
	
	
}
