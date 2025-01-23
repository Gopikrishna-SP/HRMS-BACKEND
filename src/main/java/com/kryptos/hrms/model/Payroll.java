package com.kryptos.hrms.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Set;

import org.springframework.data.convert.Jsr310Converters.LocalDateTimeToDateConverter;

import com.kryptos.hrms.model.enums.ERole;
import com.kryptos.hrms.model.enums.EPayrollStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Entity
public class Payroll {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long payrollId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id")
	private Department department;

	@Column(name = "payroll_month")
	private Month payrollMonth;

	@OneToOne
	private MasterPayroll payrollDefault;

	private BigDecimal basicSalary;

	private BigDecimal hra;

	private BigDecimal medicalAllowance;

	private BigDecimal annualBonus;

	private BigDecimal incomeTax;

	private BigDecimal pf;

	private BigDecimal overtime;

	private BigDecimal netSalary;

	private LocalDate date;

	@Enumerated(EnumType.STRING)
	@Column(name = "payment_status")
	private EPayrollStatus paymentStatus;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	public Payroll() {
		// Default constructor
	}

	public Payroll(Long payrollId, User user, Department department, Month payrollMonth, MasterPayroll payrollDefault,
			BigDecimal basicSalary, BigDecimal hra, BigDecimal medicalAllowance, BigDecimal annualBonus,
			BigDecimal incomeTax, BigDecimal pf, BigDecimal overtime, BigDecimal netSalary, LocalDate date,
			EPayrollStatus paymentStatus, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.payrollId = payrollId;
		this.user = user;
		this.department = department;
		this.payrollMonth = payrollMonth;
		this.payrollDefault = payrollDefault;
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

	@PrePersist
	public void onCreate() {
		this.payrollMonth = LocalDate.now().getMonth();
		this.createdAt = LocalDateTime.now();
		this.updatedAt = null;
//        setDefaultPayrollValues();
//        calculateNetSalary();
	}

	@PreUpdate
	public void onUpdate() {
		this.updatedAt = LocalDateTime.now();
//        calculateNetSalary();
	}

//    private void setDefaultPayrollValues() {
//        if (user != null && user.getRoles() != null) {
//            this.basicSalary = BigDecimal.ZERO;
//            this.hra = BigDecimal.ZERO;
//            this.medicalAllowance = BigDecimal.ZERO;
//            this.annualBonus = BigDecimal.ZERO;
//            this.incomeTax = BigDecimal.ZERO;
//            this.pf = BigDecimal.ZERO;
//            this.overtime = BigDecimal.ZERO;
//
//            // Iterate over the user's roles and assign appropriate salary components
//            Set<Role> roles = user.getRoles();
//            for (Role role : roles) {
//                ERole roleEnum = role.getName();
//
//                switch (roleEnum) {
//                    case ROLE_ADMIN:
//                        this.basicSalary = this.basicSalary.add(BigDecimal.valueOf(100000));
//                        this.hra = this.hra.add(BigDecimal.valueOf(25000));
//                        this.incomeTax = this.incomeTax.add(BigDecimal.valueOf(15000));
//                        break;
//                    case ROLE_HUMANRESOURCE:
//                        this.basicSalary = this.basicSalary.add(BigDecimal.valueOf(70000));
//                        this.hra = this.hra.add(BigDecimal.valueOf(15000));
//                        this.incomeTax = this.incomeTax.add(BigDecimal.valueOf(10000));
//                        break;
//                    case ROLE_EMPLOYEE:
//                        this.basicSalary = this.basicSalary.add(BigDecimal.valueOf(80000));
//                        this.hra = this.hra.add(BigDecimal.valueOf(20000));
//                        this.incomeTax = this.incomeTax.add(BigDecimal.valueOf(12000));
//                        break;
//                    default:
//                        this.basicSalary = this.basicSalary.add(BigDecimal.valueOf(50000));
//                        this.hra = this.hra.add(BigDecimal.valueOf(10000));
//                        this.incomeTax = this.incomeTax.add(BigDecimal.valueOf(5000));
//                        break;
//                }
//            }
//        }
//    }
//
//    private void calculateNetSalary() {
//        // Calculate net salary considering all components
//        this.netSalary = this.basicSalary.add(this.hra).add(this.medicalAllowance)
//                .add(this.annualBonus).add(this.overtime).subtract(this.incomeTax).subtract(this.pf);
//    }

	// Getters and setters for all fields
	public Long getPayrollId() {
		return payrollId;
	}

	public void setPayrollId(Long payrollId) {
		this.payrollId = payrollId;
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

	public MasterPayroll getPayrollDefault() {
		return payrollDefault;
	}

	public void setPayrollDefault(MasterPayroll payrollDefault) {
		this.payrollDefault = payrollDefault;
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
