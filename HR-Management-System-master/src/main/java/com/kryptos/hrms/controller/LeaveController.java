package com.kryptos.hrms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kryptos.hrms.request.LeaveRequestDTO;
import com.kryptos.hrms.response.AttendanceResponseDTO;
import com.kryptos.hrms.response.LeaveResponseDTO;
import com.kryptos.hrms.response.LeaveSummaryResponseDTO;
import com.kryptos.hrms.response.ResponseMessage;
import com.kryptos.hrms.serviceimpl.LeaveServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/leave")
public class LeaveController {

	private final LeaveServiceImpl leaveServiceImpl;

	public LeaveController(LeaveServiceImpl leaveServiceImpl) {
		super();
		this.leaveServiceImpl = leaveServiceImpl;
	}

	@PostMapping("/add")
	@PreAuthorize("hasRole('ADMIN') or hasRole('HUMANRESOURCE') or hasRole('EMPLOYEE') or hasRole('MANAGER')")
	public ResponseEntity<ResponseMessage<LeaveResponseDTO>> applyLeave(@Valid @RequestBody LeaveRequestDTO leave) {
		LeaveResponseDTO addedLeave = leaveServiceImpl.applyLeave(leave);
		ResponseMessage<LeaveResponseDTO> response = new ResponseMessage<LeaveResponseDTO>(true, 1,
				"Attendance marked successfully", addedLeave);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping("/get-all")
	@PreAuthorize("hasRole('ADMIN') or hasRole('HUMANRESOURCE') or hasRole('MANAGER')")
	public ResponseEntity<ResponseMessage<List<LeaveResponseDTO>>> getAllLeave() {
		List<LeaveResponseDTO> allLeave = leaveServiceImpl.getAllLeave();
		ResponseMessage<List<LeaveResponseDTO>> response = new ResponseMessage<List<LeaveResponseDTO>>(true, 1,
				"Attendance marked successfully", allLeave);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/get/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('HUMANRESOURCE') or hasRole('MANAGER') or hasRole('EMPLOYEE')")
	public ResponseEntity<ResponseMessage<List<LeaveResponseDTO>>> getLeaveById(@PathVariable Long id) {
		List<LeaveResponseDTO> leave = leaveServiceImpl.getLeaveByUserId(id);
		ResponseMessage<List<LeaveResponseDTO>> response = new ResponseMessage<List<LeaveResponseDTO>>(true, 1,
				"Attendance marked successfully", leave);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/get-department/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('HUMANRESOURCE') or hasRole('MANAGER')")
	public ResponseEntity<ResponseMessage<List<LeaveResponseDTO>>> getAllUserLeave(@PathVariable Long id) {
		List<LeaveResponseDTO> leave = leaveServiceImpl.getAllUserLeave(id);
		ResponseMessage<List<LeaveResponseDTO>> response = new ResponseMessage<List<LeaveResponseDTO>>(true, 1,
				"Attendance marked successfully", leave);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PutMapping("/update/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('HUMANRESOURCE') or hasRole('MANAGER') or hasRole('EMPLOYEE')")
	public ResponseEntity<ResponseMessage<LeaveResponseDTO>> updateLeave(@PathVariable Long id,
			@Valid @RequestBody LeaveRequestDTO leave) {
		LeaveResponseDTO updatedLeave = leaveServiceImpl.updateLeave(id, leave);
		ResponseMessage<LeaveResponseDTO> response = new ResponseMessage<LeaveResponseDTO>(true, 1,
				"Attendance marked successfully", updatedLeave);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PatchMapping("/status/{id}")
	@PreAuthorize("hasRole('HUMANRESOURCE') or hasRole('MANAGER')")
	public ResponseEntity<ResponseMessage<LeaveResponseDTO>> approveLeave(@PathVariable Long id,
			@Valid @RequestBody LeaveRequestDTO leave) {
		  LeaveResponseDTO updatedLeave = leaveServiceImpl.approveLeave(id, leave);
	        
	        if (updatedLeave != null) {
	            ResponseMessage<LeaveResponseDTO> response = new ResponseMessage<>(true, 1,
	                    "Status updated successfully", updatedLeave);
	            return ResponseEntity.status(HttpStatus.OK).body(response);
	        } else {
	            ResponseMessage<LeaveResponseDTO> response = new ResponseMessage<>(false, 0,
	                    "Leave request not found", null);
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	        }
	}
	
    @CrossOrigin(origins = "http://localhost:3000") // Allow specific origin
	@GetMapping("/get-leave-summary/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('HUMANRESOURCE') or hasRole('MANAGER')")
	public ResponseEntity<ResponseMessage<LeaveSummaryResponseDTO>> getLeaveSummary(@PathVariable Long id) {
		LeaveSummaryResponseDTO leave = leaveServiceImpl.getLeaveSummary(id);
		ResponseMessage<LeaveSummaryResponseDTO> response = new ResponseMessage<LeaveSummaryResponseDTO>(true, 1,
				"Attendance marked successfully", leave);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('HUMANRESOURCE') or hasRole('MANAGER') or hasRole('EMPLOYEE')")
	public ResponseEntity<Void> deleteLeave(@PathVariable Long id) {
		leaveServiceImpl.deleteLeave(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
