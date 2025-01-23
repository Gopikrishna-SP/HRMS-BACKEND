package com.kryptos.hrms.payload.response;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kryptos.hrms.model.BankAccountDetails;
import com.kryptos.hrms.model.Department;
import com.kryptos.hrms.model.EducationDetails;
import com.kryptos.hrms.model.PersonalDetails;
import com.kryptos.hrms.model.ProfessionalExperience;
import com.kryptos.hrms.model.Role;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserResponse {

	private Long userId;

	private String username;

	private String password;

	private Set<Role> roles = new HashSet<>();

	private Long departmentId;

	private List<PersonalDetails> personalDetails;

	private List<ProfessionalExperience> professionalExperience;

	private List<EducationDetails> educationDetails;

	private BankAccountDetails bankAccountDetails;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	@PrePersist
	protected void onCreate() {
		createdAt = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		updatedAt = LocalDateTime.now();
	}

	public UserResponse(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public Long getId() {
		return userId;
	}

	public void setId(Long id) {
		this.userId = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public List<PersonalDetails> getPersonalDetails() {
		return personalDetails;
	}

	public void setPersonalDetails(List<PersonalDetails> personalDetails) {
		this.personalDetails = personalDetails;
	}

	public List<ProfessionalExperience> getProfessionalExperience() {
		return professionalExperience;
	}

	public void setProfessionalExperience(List<ProfessionalExperience> professionalExperience) {
		this.professionalExperience = professionalExperience;
	}

	public List<EducationDetails> getEducationDetails() {
		return educationDetails;
	}

	public void setEducationDetails(List<EducationDetails> educationDetails) {
		this.educationDetails = educationDetails;
	}

	public BankAccountDetails getBankAccountDetails() {
		return bankAccountDetails;
	}

	public void setBankAccountDetails(BankAccountDetails bankAccountDetails) {
		this.bankAccountDetails = bankAccountDetails;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public UserResponse() {
		// TODO Auto-generated constructor stub
	}

	public UserResponse(Long id, @Size(max = 60) @NotBlank String username, @Size(max = 120) @NotBlank String password,
			Set<Role> roles, Long departmentId, List<PersonalDetails> personalDetails,
			List<ProfessionalExperience> professionalExperience, List<EducationDetails> educationDetails,
			BankAccountDetails bankAccountDetails, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.userId = id;
		this.username = username;
		this.password = password;
		this.roles = roles;
		this.departmentId = departmentId;
		this.personalDetails = personalDetails;
		this.professionalExperience = professionalExperience;
		this.educationDetails = educationDetails;
		this.bankAccountDetails = bankAccountDetails;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	

}


