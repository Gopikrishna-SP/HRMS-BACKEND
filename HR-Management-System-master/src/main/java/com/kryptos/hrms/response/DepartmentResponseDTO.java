package com.kryptos.hrms.response;

import java.time.LocalDateTime;

public class DepartmentResponseDTO {
	
	private Long departmentId;
	
	private String departmentName;
    
	private String departmentHead;
    
	private Long numberOfUsers;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	public DepartmentResponseDTO() {
		// TODO Auto-generated constructor stub
	}

	public DepartmentResponseDTO(Long departmentId, String departmentName, String departmentHead, Long numberOfUsers,
			LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.departmentHead = departmentHead;
		this.numberOfUsers = numberOfUsers;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentHead() {
		return departmentHead;
	}

	public void setDepartmentHead(String departmentHead) {
		this.departmentHead = departmentHead;
	}

	public Long getNumberOfUsers() {
		return numberOfUsers;
	}

	public void setNumberOfUsers(Long numberOfUsers) {
		this.numberOfUsers = numberOfUsers;
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
