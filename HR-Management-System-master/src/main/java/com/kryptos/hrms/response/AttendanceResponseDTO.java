package com.kryptos.hrms.response;

import java.time.LocalDateTime;
import java.time.LocalTime;

import com.kryptos.hrms.model.enums.EAttendanceStatus;

public class AttendanceResponseDTO {

	private Long attendanceId;

	private Long departmentId;

	private Long userId;

	private LocalTime checkInTime;

	private LocalTime checkOutTime;

	private float workHours;
						
	private float overTime;

	private EAttendanceStatus status;

	private Boolean lateCheckIn;

	private Boolean earlyCheckout;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	public AttendanceResponseDTO() {
		// TODO Auto-generated constructor stub
	}

	public AttendanceResponseDTO(Long attendanceId, Long departmentId, Long userId, LocalTime checkInTime,
			LocalTime checkOutTime, float workHours, float overTime, EAttendanceStatus status, Boolean lateCheckIn,
			Boolean earlyCheckout, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.attendanceId = attendanceId;
		this.departmentId = departmentId;
		this.userId = userId;
		this.checkInTime = checkInTime;
		this.checkOutTime = checkOutTime;
		this.workHours = workHours;
		this.overTime = overTime;
		this.status = status;
		this.lateCheckIn = lateCheckIn;
		this.earlyCheckout = earlyCheckout;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getAttendanceId() {
		return attendanceId;
	}

	public void setAttendanceId(Long attendanceId) {
		this.attendanceId = attendanceId;
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
