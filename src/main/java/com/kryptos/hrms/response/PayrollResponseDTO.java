package com.kryptos.hrms.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import com.kryptos.hrms.model.enums.EPayrollStatus;

public class PayrollResponseDTO {

	private Long payrollId;

	private Long employeeId;

	private Long departmentId;

	private Month payrollMonth;

	private BigDecimal basicSalary;

	private BigDecimal hra;

	private BigDecimal medicalAllowance;

	private BigDecimal annualBonus;

	private BigDecimal incomeTax;

	private BigDecimal pf;

	private BigDecimal overtime;

	private BigDecimal netSalary;

	private LocalDate date;

	private EPayrollStatus paymentStatus;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	public PayrollResponseDTO() {
		// TODO Auto-generated constructor stub
	}

	public PayrollResponseDTO(Long payrollId, Long employeeId, Long departmentId, Month payrollMonth,
			BigDecimal basicSalary, BigDecimal hra, BigDecimal medicalAllowance, BigDecimal annualBonus,
			BigDecimal incomeTax, BigDecimal pf, BigDecimal overtime, BigDecimal netSalary, LocalDate date,
			EPayrollStatus paymentStatus, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.payrollId = payrollId;
		this.employeeId = employeeId;
		this.departmentId = departmentId;
		this.payrollMonth = payrollMonth;
		this.basicSalary = basicSalary;
		this.hra = hra;
		this.medicalAllowance = medicalAllowance;
		this.annualBonus = annualBonus;
		this.incomeTax = incomeTax;
		this.pf = pf;
		this.overtime = overtime;
		this.netSalary = netSalary;
		this.date = date;
		this.paymentStatus = paymentStatus;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getPayrollId() {
		return payrollId;
	}

	public void setPayrollId(Long payrollId) {
		this.payrollId = payrollId;
	}

	public Long getUserId() {
		return employeeId;
	}

	public void setUserId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public Month getPayrollMonth() {
		return payrollMonth;
	}

	public void setPayrollMonth(Month payrollMonth) {
		this.payrollMonth = payrollMonth;
	}

	public BigDecimal getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(BigDecimal basicSalary) {
		this.basicSalary = basicSalary;
	}

	public BigDecimal getHra() {
		return hra;
	}

	public void setHra(BigDecimal hra) {
		this.hra = hra;
	}

	public BigDecimal getMedicalAllowance() {
		return medicalAllowance;
	}

	public void setMedicalAllowance(BigDecimal medicalAllowance) {
		this.medicalAllowance = medicalAllowance;
	}

	public BigDecimal getAnnualBonus() {
		return annualBonus;
	}

	public void setAnnualBonus(BigDecimal annualBonus) {
		this.annualBonus = annualBonus;
	}

	public BigDecimal getIncomeTax() {
		return incomeTax;
	}

	public void setIncomeTax(BigDecimal incomeTax) {
		this.incomeTax = incomeTax;
	}

	public BigDecimal getPf() {
		return pf;
	}

	public void setPf(BigDecimal pf) {
		this.pf = pf;
	}

	public BigDecimal getOvertime() {
		return overtime;
	}

	public void setOvertime(BigDecimal overtime) {
		this.overtime = overtime;
	}

	public BigDecimal getNetSalary() {
		return netSalary;
	}

	public void setNetSalary(BigDecimal netSalary) {
		this.netSalary = netSalary;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public EPayrollStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(EPayrollStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
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
