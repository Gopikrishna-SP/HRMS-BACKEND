package com.kryptos.hrms.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = "username") })
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@Size(max = 60)
	@NotBlank
	private String username;

	@Size(max = 120)
	@NotBlank
	private String password;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	@ManyToOne
	@JoinColumn(name = "department_id")
	@JsonIgnore 
	private Department department;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = false)
	@JoinColumn(name = "user_id")
	private List<PersonalDetails> personalDetails;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = false)
	@JoinColumn(name = "user_id")
	private List<ProfessionalExperience> professionalExperience;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = false)
	@JoinColumn(name = "user_id")
	private List<EducationDetails> educationDetails;

	@Embedded
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

	public User(String username, String password) {
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

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
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

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(Long id, @Size(max = 60) @NotBlank String username, @Size(max = 120) @NotBlank String password,
			Set<Role> roles, Department department, List<PersonalDetails> personalDetails,
			List<ProfessionalExperience> professionalExperience, List<EducationDetails> educationDetails,
			BankAccountDetails bankAccountDetails, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.userId = id;
		this.username = username;
		this.password = password;
		this.roles = roles;
		this.department = department;
		this.personalDetails = personalDetails;
		this.professionalExperience = professionalExperience;
		this.educationDetails = educationDetails;
		this.bankAccountDetails = bankAccountDetails;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

}
