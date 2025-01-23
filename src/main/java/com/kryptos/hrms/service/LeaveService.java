package com.kryptos.hrms.service;


import java.util.List;

import com.kryptos.hrms.request.LeaveApproveRequestDTO;
import com.kryptos.hrms.request.LeaveRequestDTO;
import com.kryptos.hrms.response.LeaveApproveResponseDTO;
import com.kryptos.hrms.response.LeaveResponseDTO;
import com.kryptos.hrms.response.LeaveSummaryResponseDTO;

import jakarta.validation.Valid;

public interface LeaveService {
	
	LeaveResponseDTO applyLeave(LeaveRequestDTO dto);
	
	LeaveResponseDTO updateLeave(Long id, LeaveRequestDTO dto);
	
	List<LeaveResponseDTO> getLeaveByUserId(Long id);

	List<LeaveResponseDTO> getAllUserLeave(Long id);
	
	List<LeaveResponseDTO> getAllLeave();
	
	boolean deleteLeave(Long id);

	LeaveSummaryResponseDTO getLeaveSummary(Long id);

	LeaveApproveResponseDTO approveLeave(Long id, @Valid LeaveApproveRequestDTO leave2);


}