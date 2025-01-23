package com.kryptos.hrms.request;

import java.time.LocalDate;

import jakarta.persistence.Column;

public class TalentAcquisitionRequestDTO {
	
    @Column(name = "job_title", nullable = false)
    private String jobTitle;

    @Column(name = "description", nullable = false, length = 500)
    private String description;

    @Column(name = "posted_date", nullable = false)
    private LocalDate postedDate;

    @Column(name = "closing_date", nullable = false)
    private LocalDate closingDate;
    
    public TalentAcquisitionRequestDTO() {
		// TODO Auto-generated constructor stub
	}

	public TalentAcquisitionRequestDTO(String jobTitle, String description, LocalDate postedDate,
			LocalDate closingDate) {
		super();
		this.jobTitle = jobTitle;
		this.description = description;
		this.postedDate = postedDate;
		this.closingDate = closingDate;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(LocalDate postedDate) {
		this.postedDate = postedDate;
	}

	public LocalDate getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(LocalDate closingDate) {
		this.closingDate = closingDate;
	}
    
    

}
