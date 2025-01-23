package com.kryptos.hrms.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class EducationDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

	private String institutionName;
	private String degree;
	private String major;
	private float gradePointAverage;
	private LocalDate startDate;
	private LocalDate endDate;
	private String location;
	private String degreeCertificatePath;
	private String additionalCertifications;
	private boolean currentlyPursuing;

	public EducationDetails() {
		// TODO Auto-generated constructor stub
	}

	public EducationDetails( String institutionName, String degree, String major, float gradePointAverage,
			LocalDate startDate, LocalDate endDate, String location, String degreeCertificatePath,
			String additionalCertifications, boolean currentlyPursuing) {
		super();

		this.institutionName = institutionName;
		this.degree = degree;
		this.major = major;
		this.gradePointAverage = gradePointAverage;
		this.startDate = startDate;
		this.endDate = endDate;
		this.location = location;
		this.degreeCertificatePath = degreeCertificatePath;
		this.additionalCertifications = additionalCertifications;
		this.currentlyPursuing = currentlyPursuing;
	}

	public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public float getGradePointAverage() {
		return gradePointAverage;
	}

	public void setGradePointAverage(float gradePointAverage) {
		this.gradePointAverage = gradePointAverage;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDegreeCertificatePath() {
		return degreeCertificatePath;
	}

	public void setDegreeCertificatePath(String degreeCertificatePath) {
		this.degreeCertificatePath = degreeCertificatePath;
	}

	public String getAdditionalCertifications() {
		return additionalCertifications;
	}

	public void setAdditionalCertifications(String additionalCertifications) {
		this.additionalCertifications = additionalCertifications;
	}

	public boolean isCurrentlyPursuing() {
		return currentlyPursuing;
	}

	public void setCurrentlyPursuing(boolean currentlyPursuing) {
		this.currentlyPursuing = currentlyPursuing;
	}

}
