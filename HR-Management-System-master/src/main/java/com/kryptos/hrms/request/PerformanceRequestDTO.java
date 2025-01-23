package com.kryptos.hrms.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PerformanceRequestDTO {
	
	@NotNull(message = "User ID cannot be Empty!!")
    private Long userId;
	
	
	@NotBlank(message = "Goal cannot be Empty!!")
    private String goal;
	
	@FutureOrPresent(message = "Review period cannot be past!!")
    private LocalDate reviewPeriod;
	
	@Future(message = "Review period End cannot be present or past!!")
    private LocalDate reviewPeriodEnd;
	
	@NotNull(message = "Rating cannot be Empty!!")
    private int rating;
	
	@NotBlank(message = "Feedback cannot be Empty!!")
    private String feedback; 
	

    
    public PerformanceRequestDTO() {
		// TODO Auto-generated constructor stub
	}


	public PerformanceRequestDTO(@NotNull(message = "User ID cannot be Empty!!") Long userId,
			@NotBlank(message = "Goal cannot be Empty!!") String goal,
			@FutureOrPresent(message = "Review period cannot be Empty!!") LocalDate reviewPeriod,
			@Future(message = "Review period End cannot be Empty!!") LocalDate reviewPeriodEnd,
			@NotNull(message = "Rating cannot be Empty!!") int rating,
			@NotBlank(message = "Feedback cannot be Empty!!") String feedback) {
		super();
		this.userId = userId;
		this.goal = goal;
		this.reviewPeriod = reviewPeriod;
		this.reviewPeriodEnd = reviewPeriodEnd;
		this.rating = rating;
		this.feedback = feedback;
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

    
    
}
