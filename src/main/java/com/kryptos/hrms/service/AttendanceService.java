package com.kryptos.hrms.service;

import java.util.List;

import com.kryptos.hrms.response.AttendanceSummaryResponseDTO;
import com.kryptos.hrms.response.AttendanceResponseDTO;

public interface AttendanceService {


	List<AttendanceResponseDTO> getAttendanceUser(Long id);

	List<AttendanceResponseDTO> getAllAttendanceUser(Long id);

	AttendanceResponseDTO checkIn(Long userId);

	AttendanceResponseDTO checkOut(Long userId);
	
	List<AttendanceSummaryResponseDTO> getAttendanceSummary(Long id);
	
}