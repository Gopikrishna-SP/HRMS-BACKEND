package com.kryptos.hrms.response;

import com.kryptos.hrms.model.enums.ELeaveStatus;

public class LeaveApproveResponseDTO {

	ELeaveStatus status;
	
	public LeaveApproveResponseDTO() {
		// TODO Auto-generated constructor stub
	}

	public ELeaveStatus getStatus() {
		return status;
	}

	public void setStatus(ELeaveStatus status) {
		this.status = status;
	}

	public LeaveApproveResponseDTO(ELeaveStatus status) {
		super();
		this.status = status;
	}
	
	
}
