package com.kryptos.hrms.model;

import java.math.BigDecimal;

import com.kryptos.hrms.model.enums.ERole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MasterPayroll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long payrollDefaultId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private ERole role;

    @Column(nullable = false)
    private BigDecimal basicSalary;

    private BigDecimal hra = BigDecimal.ZERO;

    private BigDecimal medicalAllowance = BigDecimal.ZERO;

    private BigDecimal annualBonus = BigDecimal.ZERO;

    private BigDecimal incomeTax = BigDecimal.ZERO;

    private BigDecimal pf = BigDecimal.ZERO;

    private BigDecimal overtimeRate = BigDecimal.ZERO;
    
    public MasterPayroll() {
		// TODO Auto-generated constructor stub
	}

	public MasterPayroll(Long payrollDefaultId, ERole role, BigDecimal basicSalary, BigDecimal hra,
			BigDecimal medicalAllowance, BigDecimal annualBonus, BigDecimal incomeTax, BigDecimal pf,
			BigDecimal overtimeRate) {
		super();
		this.payrollDefaultId = payrollDefaultId;
		this.role = role;
		this.basicSalary = basicSalary;
		this.hra = hra;
		this.medicalAllowance = medicalAllowance;
		this.annualBonus = annualBonus;
		this.incomeTax = incomeTax;
		this.pf = pf;
		this.overtimeRate = overtimeRate;
	}

	public Long getPayrollDefaultId() {
		return payrollDefaultId;
	}

	public void setPayrollDefaultId(Long payrollDefaultId) {
		this.payrollDefaultId = payrollDefaultId;
	}

	public ERole getRole() {
		return role;
	}

	public void setRole(ERole role) {
		this.role = role;
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

	public BigDecimal getOvertimeRate() {
		return overtimeRate;
	}

	public void setOvertimeRate(BigDecimal overtimeRate) {
		this.overtimeRate = overtimeRate;
	}


	
	

    
}
