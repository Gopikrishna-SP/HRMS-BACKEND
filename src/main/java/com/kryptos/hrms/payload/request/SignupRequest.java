package com.kryptos.hrms.payload.request;

import java.util.List;
import java.util.Set;

import com.kryptos.hrms.model.BankAccountDetails;
import com.kryptos.hrms.model.Department;
import com.kryptos.hrms.model.EducationDetails;
import com.kryptos.hrms.model.PersonalDetails;
import com.kryptos.hrms.model.ProfessionalExperience;

import jakarta.persistence.Embedded;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Size;

public class SignupRequest {

	@NotBlank
	@Size(max = 50)
	@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
	private String username;

	private Set<String> role;

	@NotBlank
	@Size(min = 6, max = 40)
	private String password;

	@NotNull
	private List<PersonalDetails> personalDetails;

	@NotNull
	private List<ProfessionalExperience> professionalExperience;

	@NotNull
	private List<EducationDetails> educationDetails;

	@Embedded
	private BankAccountDetails bankAccountDetails;

	@NotNull
	private Long departmentId;

	public SignupRequest() {
		// TODO Auto-generated constructor stub
	}

	public SignupRequest(
			@NotBlank @Size(max = 50) @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}") String username,
			Set<String> role, @NotBlank @Size(min = 6, max = 40) String password,
			@NotNull List<PersonalDetails> personalDetails,
			@NotNull List<ProfessionalExperience> professionalExperience,
			@NotNull List<EducationDetails> educationDetails, BankAccountDetails bankAccountDetails,
			@NotNull Long departmentId) {
		super();
		this.username = username;
		this.role = role;
		this.password = password;
		this.personalDetails = personalDetails;
		this.professionalExperience = professionalExperience;
		this.educationDetails = educationDetails;
		this.bankAccountDetails = bankAccountDetails;
		this.departmentId = departmentId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<String> getRole() {
		return role;
	}

	public void setRole(Set<String> role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

}
