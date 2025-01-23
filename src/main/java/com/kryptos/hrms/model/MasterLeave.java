package com.kryptos.hrms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class MasterLeave {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @ManyToOne
    private User user;
    
	@Column(insertable = false, updatable = false)
	private int sick;

	@Column(insertable = false, updatable = false)
	private int casual;


	public MasterLeave() {
		// Default constructor
	}

	

	public MasterLeave(Long id, User user, int sick, int casual) {
		super();
		this.id = id;
		this.user = user;
		this.sick = sick;
		this.casual = casual;
		
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	// Getters and setters
	public int getSick() {
		return sick;
	}

	public void setSick(int sick) {
		this.sick = sick;
	}

	public int getCasual() {
		return casual;
	}

	public void setCasual(int casual) {
		this.casual = casual;
	}

}
