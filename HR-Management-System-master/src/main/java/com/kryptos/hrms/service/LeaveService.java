package com.kryptos.hrms.service;


import java.util.List;

import com.kryptos.hrms.request.LeaveRequestDTO;
import com.kryptos.hrms.response.LeaveResponseDTO;
import com.kryptos.hrms.response.LeaveSummaryResponseDTO;

public interface LeaveService {
	
	LeaveResponseDTO applyLeave(LeaveRequestDTO dto);
	
	LeaveResponseDTO updateLeave(Long id, LeaveRequestDTO dto);
	
	List<LeaveResponseDTO> getLeaveByUserId(Long id);

	List<LeaveResponseDTO> getAllUserLeave(Long id);
	
	List<LeaveResponseDTO> getAllLeave();
	
	boolean deleteLeave(Long id);

	LeaveResponseDTO approveLeave(Long id, LeaveRequestDTO dto);

	LeaveSummaryResponseDTO getLeaveSummary(Long id);


}
