package com.kryptos.hrms.request;

import jakarta.validation.constraints.NotNull;

public class AttendanceRequestDTO {
	
	@NotNull(message = "User ID cannot be Empty!!")
	private Long userId;
	
	@NotNull(message = "Department ID cannot be Empty!!")
	private Long departmentId;
	
	public AttendanceRequestDTO() {
		// TODO Auto-generated constructor stub
	}

	public AttendanceRequestDTO(@NotNull(message = "User ID cannot be Empty!!") Long userId,@NotNull(message = "Department ID cannot be Empty!!") Long departmentId) {
		super();
		this.userId = userId;
		this.departmentId = departmentId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	
	
	

}
