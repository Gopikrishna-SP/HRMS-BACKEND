package com.kryptos.hrms.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kryptos.hrms.response.AttendanceResponseDTO;
import com.kryptos.hrms.response.AttendanceSummaryResponseDTO;
import com.kryptos.hrms.response.ResponseMessage;
import com.kryptos.hrms.serviceimpl.AttendanceServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/v1/attendance")
public class AttendanceController {

	@Autowired
	private AttendanceServiceImpl attendanceServiceImpl;

	@PostMapping("/checkin/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('HUMANRESOURCE') or hasRole('MANAGER') or hasRole('EMPLOYEE')")
	public ResponseEntity<ResponseMessage<AttendanceResponseDTO>> checkIn(@PathVariable Long id) {
		AttendanceResponseDTO checkInResponse = attendanceServiceImpl.checkIn(id);
		ResponseMessage<AttendanceResponseDTO> response = new ResponseMessage<>(true, 1, "Check-In marked successfully",
				checkInResponse);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PostMapping("/checkout/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('HUMANRESOURCE') or hasRole('MANAGER') or hasRole('EMPLOYEE')")
	public ResponseEntity<ResponseMessage<AttendanceResponseDTO>> checkOut(@PathVariable Long id) {
		AttendanceResponseDTO checkOutResponse = attendanceServiceImpl.checkOut(id);
		ResponseMessage<AttendanceResponseDTO> response = new ResponseMessage<>(true, 1,
				"Check-Out marked successfully", checkOutResponse);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping("/get-all/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('HUMANRESOURCE') or hasRole('MANAGER')")
	public ResponseEntity<ResponseMessage<List<AttendanceResponseDTO>>> getAllAttendance(@PathVariable Long id) {
		List<AttendanceResponseDTO> allAttendance = attendanceServiceImpl.getAllAttendanceUser(id);
		ResponseMessage<List<AttendanceResponseDTO>> response = new ResponseMessage<List<AttendanceResponseDTO>>(true,
				1, "Attendance marked successfully", allAttendance);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/get/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('HUMANRESOURCE') or hasRole('MANAGER') or hasRole('EMPLOYEE')")
	public ResponseEntity<ResponseMessage<List<AttendanceResponseDTO>>> getAttendance(@PathVariable Long id) {
		List<AttendanceResponseDTO> getAttendance = attendanceServiceImpl.getAttendanceUser(id);
		ResponseMessage<List<AttendanceResponseDTO>> response = new ResponseMessage<List<AttendanceResponseDTO>>(true,
				1, "Attendance marked successfully", getAttendance);
		return ResponseEntity.status(HttpStatus.OK).body(response);

	}

	@GetMapping("/get-summary/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('HUMANRESOURCE') or hasRole('MANAGER') or hasRole('EMPLOYEE')")
	public ResponseEntity<ResponseMessage<List<AttendanceSummaryResponseDTO>>> getAttendanceSummary(@PathVariable Long id) {
		List<AttendanceSummaryResponseDTO> getAttendance = attendanceServiceImpl.getAttendanceSummary(id);
		ResponseMessage<List<AttendanceSummaryResponseDTO>> response = new ResponseMessage<List<AttendanceSummaryResponseDTO>>(true,
				1, "Attendance fetched successfully", getAttendance);
		
		return ResponseEntity.status(HttpStatus.OK).body(response);

	}
}