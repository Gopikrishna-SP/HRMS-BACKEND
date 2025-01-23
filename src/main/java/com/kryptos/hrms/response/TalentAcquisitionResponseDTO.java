package com.kryptos.hrms.response;

import java.time.LocalDate;


public class TalentAcquisitionResponseDTO {
	
	 private Long id;

	    private String jobTitle;

	    private String description;

	    private LocalDate postedDate;

	    private LocalDate closingDate;

	    public TalentAcquisitionResponseDTO() {
			// TODO Auto-generated constructor stub
		}
	    
	    
	    public TalentAcquisitionResponseDTO(Long id, String jobTitle, String description, LocalDate postedDate,
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
