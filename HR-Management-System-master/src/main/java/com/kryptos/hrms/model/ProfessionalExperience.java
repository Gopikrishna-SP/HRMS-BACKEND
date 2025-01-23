package com.kryptos.hrms.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ProfessionalExperience {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    
	private String companyName;
	private String jobTitle;
	private String department;
	private String manager;
	private LocalDate startDate;
	private LocalDate endDate;
	private String jobDescription;
	private float salary;
	private String location;
	private String achievements;
	
	@Column(nullable = true)
	boolean isCurrentJob;
	
	public ProfessionalExperience() {
		// TODO Auto-generated constructor stub
	}

	public ProfessionalExperience( String companyName, String jobTitle, String department, String manager,
			LocalDate startDate, LocalDate endDate, String jobDescription, float salary, String location,
			String achievements, boolean isCurrentJob) {
		super();
		this.companyName = companyName;
		this.jobTitle = jobTitle;
		this.department = department;
		this.manager = manager;
		this.startDate = startDate;
		this.endDate = endDate;
		this.jobDescription = jobDescription;
		this.salary = salary;
		this.location = location;
		this.achievements = achievements;
		this.isCurrentJob = isCurrentJob;
	}



	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAchievements() {
		return achievements;
	}

	public void setAchievements(String achievements) {
		this.achievements = achievements;
	}

	public boolean isCurrentJob() {
		return isCurrentJob;
	}

	public void setCurrentJob(boolean isCurrentJob) {
		this.isCurrentJob = isCurrentJob;
	}
	
	

}
