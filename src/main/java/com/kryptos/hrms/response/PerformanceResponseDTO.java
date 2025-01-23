package com.kryptos.hrms.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PerformanceResponseDTO {
	
	private Long performanceId;
	
	private Long userId;
	
	
	private String goal;
	
	private LocalDate reviewPeriod;
	
	private LocalDate reviewPeriodEnd;
	
	private int rating;
	
	private String feedback; 
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	public PerformanceResponseDTO() {
		// TODO Auto-generated constructor stub
	}


















	public PerformanceResponseDTO(Long performanceId, Long userId, String goal, LocalDate reviewPeriod,
			LocalDate reviewPeriodEnd, int rating, String feedback, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.performanceId = performanceId;
		this.userId = userId;
		this.goal = goal;
		this.reviewPeriod = reviewPeriod;
		this.reviewPeriodEnd = reviewPeriodEnd;
		this.rating = rating;
		this.feedback = feedback;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}


















	public Long getPerformanceId() {
		return performanceId;
	}

	public void setPerformanceId(Long performanceId) {
		this.performanceId = performanceId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

	public LocalDate getReviewPeriod() {
		return reviewPeriod;
	}

	public void setReviewPeriod(LocalDate reviewPeriod) {
		this.reviewPeriod = reviewPeriod;
	}

	public LocalDate getReviewPeriodEnd() {
		return reviewPeriodEnd;
	}

	public void setReviewPeriodEnd(LocalDate reviewPeriodEnd) {
		this.reviewPeriodEnd = reviewPeriodEnd;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
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













	
	

}
