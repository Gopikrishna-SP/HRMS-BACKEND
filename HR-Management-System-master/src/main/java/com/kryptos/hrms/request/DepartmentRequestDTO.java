package com.kryptos.hrms.request;

import jakarta.validation.constraints.NotBlank;

public class DepartmentRequestDTO {
	

    @NotBlank(message = "Department Name cannot be Empty!!")
	private String departmentName;
    
    @NotBlank(message = "Department Head cannot be Empty!!")
	private String departmentHead;
    
    public DepartmentRequestDTO() {
		// TODO Auto-generated constructor stub
	}
    
	public DepartmentRequestDTO(@NotBlank(message = "Department Name cannot be Empty!!") String departmentName,
			@NotBlank(message = "Department Head cannot be Empty!!") String departmentHead) {
		super();
		this.departmentName = departmentName;
		this.departmentHead = departmentHead;
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


}
