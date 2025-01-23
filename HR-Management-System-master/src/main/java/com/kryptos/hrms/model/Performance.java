package com.kryptos.hrms.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Entity
public class Performance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long performanceId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id")
	private Department department;

	private String goal;

	private LocalDate reviewPeriod;

	private LocalDate reviewPeriodEnd;

	private int rating;

	private String feedback;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	public Performance() {
		// TODO Auto-generated constructor stub
	}


	
	public Performance(Long performanceId, User user, Department department, String goal,
			LocalDate reviewPeriod, LocalDate reviewPeriodEnd, int rating, String feedback, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		super();
		this.performanceId = performanceId;
		this.user = user;
		this.department = department;
		this.goal = goal;
		this.reviewPeriod = reviewPeriod;
		this.reviewPeriodEnd = reviewPeriodEnd;
		this.rating = rating;
		this.feedback = feedback;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}



	@PrePersist
	public void onCreate() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = null;
	}
	
	@PreUpdate
	public void onUpdate() {
		this.updatedAt = LocalDateTime.now();
	}

	public Long getPerformanceId() {
		return performanceId;
	}

	public void setPerformanceId(Long performanceId) {
		this.performanceId = performanceId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
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
