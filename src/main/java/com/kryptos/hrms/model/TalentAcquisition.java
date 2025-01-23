package com.kryptos.hrms.model;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TalentAcquisition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "job_title", nullable = false)
    private String jobTitle;

    @Column(name = "description", nullable = false, length = 500)
    private String description;

    @Column(name = "posted_date", nullable = false)
    private LocalDate postedDate;

    @Column(name = "closing_date", nullable = false)
    private LocalDate closingDate;

    public TalentAcquisition() {
		// TODO Auto-generated constructor stub
	}
    
    
    public TalentAcquisition(Long id, String jobTitle, String description, LocalDate postedDate,
			LocalDate closingDate) {
		super();
		this.id = id;
		this.jobTitle = jobTitle;
		this.description = description;
		this.postedDate = postedDate;
		this.closingDate = closingDate;
	}


	// Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

