package com.kryptos.hrms.response;

public class LeaveSummaryResponseDTO {
	
	private int totalLeave;
	
	private int leaveTaken;
	
	private int remainingLeave;
	
	public LeaveSummaryResponseDTO() {
		// TODO Auto-generated constructor stub
	}

	public LeaveSummaryResponseDTO(int totalLeave, int leaveTaken, int remainingLeave) {
		super();
		this.totalLeave = totalLeave;
		this.leaveTaken = leaveTaken;
		this.remainingLeave = remainingLeave;
	}

	public int getTotalLeave() {
		return totalLeave;
	}

	public void setTotalLeave(int totalLeave) {
		this.totalLeave = totalLeave;
	}

	public int getLeaveTaken() {
		return leaveTaken;
	}

	public void setLeaveTaken(int leaveTaken) {
		this.leaveTaken = leaveTaken;
	}

	public int getRemainingLeave() {
		return remainingLeave;
	}

	public void setRemainingLeave(int remainingLeave) {
		this.remainingLeave = remainingLeave;
	}

	



}
