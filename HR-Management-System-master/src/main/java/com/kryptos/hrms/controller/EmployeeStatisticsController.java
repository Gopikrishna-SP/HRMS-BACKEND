package com.kryptos.hrms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kryptos.hrms.response.EmployeeStatisticsResponseDTO;
import com.kryptos.hrms.response.ResponseMessage;
import com.kryptos.hrms.serviceimpl.EmployeeStatisticsServiceImpl;

@RestController
@RequestMapping("/v1/api/statistics")
public class EmployeeStatisticsController {

	@Autowired
	private EmployeeStatisticsServiceImpl employeeStatisticsServiceImpl;

	@GetMapping("/get-all")
	@PreAuthorize("hasRole('ADMIN') or hasRole('HUMANRESOURCE') or hasRole('MANAGER') or hasRole('EMPLOYEE')")
	public ResponseEntity<ResponseMessage<EmployeeStatisticsResponseDTO>> getEmployeeStatistics() {

		EmployeeStatisticsResponseDTO statistics = employeeStatisticsServiceImpl.getEmployeeStatistics();

		ResponseMessage<EmployeeStatisticsResponseDTO> response = new ResponseMessage<>(true, 1,
				"Upcoming birthdays fetched successfully", statistics);
		return ResponseEntity.status(HttpStatus.OK).body(response);

	}

}
