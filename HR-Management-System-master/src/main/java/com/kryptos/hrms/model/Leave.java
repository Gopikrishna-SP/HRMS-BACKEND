package com.kryptos.hrms.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.kryptos.hrms.model.enums.ELeaveStatus;
import com.kryptos.hrms.model.enums.ELeaveType;

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
import jakarta.persistence.Table;

@Entity
@Table(name = "Leaves")
public class Leave {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long leaveId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id")
	private Department department;

    @Enumerated(EnumType.STRING)
	private ELeaveType leaveType;

	private LocalDate startDate;

	private LocalDate endDate;

	private int leaveDuration;

	private String reason;

	private String approver;

	private int sickTaken;

	private int casualTaken;

	private int paidLeaveTaken;

	private int unPaidLeaveTaken;

	private int maternityLeaveTaken;

	private int paternityLeaveTaken;

	private ELeaveStatus status;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	@PrePersist
	public void onCreate() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = null;
		this.status = ELeaveStatus.PENDING;
	}

	@PreUpdate
	public void onUpdate() {
		this.updatedAt = LocalDateTime.now();
	}

	public Leave() {
	}

	public Leave(Long leaveId, User user, Department department, ELeaveType leaveType, LocalDate startDate,
			LocalDate endDate, int leaveDuration, String reason, String approver, int sickTaken, int casualTaken,
			int paidLeaveTaken, int unPaidLeaveTaken, int maternityLeaveTaken, int paternityLeaveTaken,
			ELeaveStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.leaveId = leaveId;
		this.user = user;
		this.department = department;
		this.leaveType = leaveType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.leaveDuration = leaveDuration;
		this.reason = reason;
		this.approver = approver;
		this.sickTaken = sickTaken;
		this.casualTaken = casualTaken;
		this.paidLeaveTaken = paidLeaveTaken;
		this.unPaidLeaveTaken = unPaidLeaveTaken;
		this.maternityLeaveTaken = maternityLeaveTaken;
		this.paternityLeaveTaken = paternityLeaveTaken;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(Long leaveId) {
		this.leaveId = leaveId;
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

	public ELeaveType getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(ELeaveType leaveType) {
		this.leaveType = leaveType;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public int getLeaveDuration() {
		return leaveDuration;
	}

	public void setLeaveDuration(int leaveDuration) {
		this.leaveDuration = leaveDuration;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public int getSickTaken() {
		return sickTaken;
	}

	public void setSickTaken(int sickTaken) {
		this.sickTaken = sickTaken;
	}

	public int getCasualTaken() {
		return casualTaken;
	}

	public void setCasualTaken(int casualTaken) {
		this.casualTaken = casualTaken;
	}

	public int getPaidLeaveTaken() {
		return paidLeaveTaken;
	}

	public void setPaidLeaveTaken(int paidLeaveTaken) {
		this.paidLeaveTaken = paidLeaveTaken;
	}

	public int getUnPaidLeaveTaken() {
		return unPaidLeaveTaken;
	}

	public void setUnPaidLeaveTaken(int unPaidLeaveTaken) {
		this.unPaidLeaveTaken = unPaidLeaveTaken;
	}

	public int getMaternityLeaveTaken() {
		return maternityLeaveTaken;
	}

	public void setMaternityLeaveTaken(int maternityLeaveTaken) {
		this.maternityLeaveTaken = maternityLeaveTaken;
	}

	public int getPaternityLeaveTaken() {
		return paternityLeaveTaken;
	}

	public void setPaternityLeaveTaken(int paternityLeaveTaken) {
		this.paternityLeaveTaken = paternityLeaveTaken;
	}

	public ELeaveStatus getStatus() {
		return status;
	}

	public void setStatus(ELeaveStatus status) {
		this.status = status;
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
