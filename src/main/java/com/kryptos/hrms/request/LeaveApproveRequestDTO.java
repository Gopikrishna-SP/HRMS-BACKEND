package com.kryptos.hrms.request;

import com.kryptos.hrms.model.enums.ELeaveStatus;

public class LeaveApproveRequestDTO {
	
	ELeaveStatus status;
	
	public LeaveApproveRequestDTO() {
		// TODO Auto-generated constructor stub
	}

	public ELeaveStatus getStatus() {
		return status;
	}

	public void setStatus(ELeaveStatus status) {
		this.status = status;
	}

	public LeaveApproveRequestDTO(ELeaveStatus status) {
		super();
		this.status = status;
	}
	
	

}
