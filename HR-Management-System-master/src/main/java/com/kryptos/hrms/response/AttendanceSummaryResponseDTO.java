package com.kryptos.hrms.response;

public class AttendanceSummaryResponseDTO {

	private int totalDays;

	private int attendedDays;

	private float attendancePercentage;

	public AttendanceSummaryResponseDTO() {
		// TODO Auto-generated constructor stub
	}

	public AttendanceSummaryResponseDTO(int totalDays, int attendedDays, float attendancePercentage) {
		super();
		this.totalDays = totalDays;
		this.attendedDays = attendedDays;
		this.attendancePercentage = attendancePercentage;
	}

	public int getTotalDays() {
		return totalDays;
	}

	public void setTotalDays(int totalDays) {
		this.totalDays = totalDays;
	}

	public int getAttendedDays() {
		return attendedDays;
	}

	public void setAttendedDays(int attendedDays) {
		this.attendedDays = attendedDays;
	}

	public float getAttendancePercentage() {
		return attendancePercentage;
	}

	public void setAttendancePercentage(float attendancePercentage) {
		this.attendancePercentage = attendancePercentage;
	}
	
	

}
