package com.kryptos.hrms.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.kryptos.hrms.model.enums.EAttendanceStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Entity
public class Attendance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long attendanceId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id", nullable = false, referencedColumnName = "department_id")
	private Department department;

	private LocalDate date;

	private LocalTime checkInTime;

	private LocalTime checkOutTime;

	private float workHours;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private EAttendanceStatus status;

	private float overTime;

	private Boolean lateCheckIn;

	private Boolean earlyCheckout;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	
	public Attendance() {
		// TODO Auto-generated constructor stub
	}

	public Attendance(Long attendanceId, User user, Department department, LocalDate date, LocalTime checkInTime,
			LocalTime checkOutTime, float workHours, EAttendanceStatus status, float overTime, Boolean lateCheckIn,
			Boolean earlyCheckout, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.attendanceId = attendanceId;
		this.user = user;
		this.department = department;
		this.date = date;
		this.checkInTime = checkInTime;
		this.checkOutTime = checkOutTime;
		this.workHours = workHours;
		this.status = status;
		this.overTime = overTime;
		this.lateCheckIn = lateCheckIn;
		this.earlyCheckout = earlyCheckout;
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

	public Long getAttendanceId() {
		return attendanceId;
	}

	public void setAttendanceId(Long attendanceId) {
		this.attendanceId = attendanceId;
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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getCheckInTime() {
		return checkInTime;
	}

	public void setCheckInTime(LocalTime checkInTime) {
		this.checkInTime = checkInTime;
	}

	public LocalTime getCheckOutTime() {
		return checkOutTime;
	}

	public void setCheckOutTime(LocalTime checkOutTime) {
		this.checkOutTime = checkOutTime;
	}

	public float getWorkHours() {
		return workHours;
	}

	public void setWorkHours(float workHours) {
		this.workHours = workHours;
	}

	public float getOverTime() {
		return overTime;
	}

	public void setOverTime(float overTime) {
		this.overTime = overTime;
	}

	public EAttendanceStatus getStatus() {
		return status;
	}

	public void setStatus(EAttendanceStatus status) {
		this.status = status;
	}

	public Boolean getLateCheckIn() {
		return lateCheckIn;
	}

	public void setLateCheckIn(Boolean lateCheckIn) {
		this.lateCheckIn = lateCheckIn;
	}

	public Boolean getEarlyCheckout() {
		return earlyCheckout;
	}

	public void setEarlyCheckout(Boolean earlyCheckout) {
		this.earlyCheckout = earlyCheckout;
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