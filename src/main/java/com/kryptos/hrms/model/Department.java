package com.kryptos.hrms.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Entity
public class Department {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Long departmentId;

    @Column(name = "department_name", nullable = false)
    private String departmentName;

	private String departmentHead;

	private Long numberOfUsers;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	@OneToMany(mappedBy = "department")
	@JsonIgnore
	private List<User> users;

	public Department() {
		// TODO Auto-generated constructor stub
	}

	public Department(Long departmentId, String departmentName, String departmentHead, Long numberOfUsers,
			LocalDateTime createdAt, LocalDateTime updatedAt, List<User> users) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.departmentHead = departmentHead;
		this.numberOfUsers = numberOfUsers;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.users = users;
	}

	@PrePersist
	public void onCreate() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();

	}

	@PreUpdate
	public void onUpdate() {
		this.updatedAt = LocalDateTime.now();
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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}