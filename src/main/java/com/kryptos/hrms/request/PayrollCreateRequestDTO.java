package com.kryptos.hrms.request;

import java.time.Month;

import com.kryptos.hrms.model.enums.EPayrollStatus;

import jakarta.validation.constraints.NotNull;

public class PayrollCreateRequestDTO {
	
	@NotNull(message = "User ID cannot be Empty!!")
	private Long userId;

	@NotNull(message = "Department ID cannot be Empty!!")
	private Long DepartmentId;
	
	@NotNull(message = "Payment status cannot be Empty!!")
    private EPayrollStatus paymentStatus;
	
	public PayrollCreateRequestDTO() {
		// TODO Auto-generated constructor stub
	}

	public PayrollCreateRequestDTO(@NotNull(message = "User ID cannot be Empty!!") Long userId,
			@NotNull(message = "Department ID cannot be Empty!!") Long departmentId,
			@NotNull(message = "Payment status cannot be Empty!!") EPayrollStatus paymentStatus) {
		super();
		this.userId = userId;
		DepartmentId = departmentId;
		this.paymentStatus = paymentStatus;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getDepartmentId() {
		return DepartmentId;
	}

	public void setDepartmentId(Long departmentId) {
		DepartmentId = departmentId;
	}

	public EPayrollStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(EPayrollStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
	
	
	
	


}
