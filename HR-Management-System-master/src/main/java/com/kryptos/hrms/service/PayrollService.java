package com.kryptos.hrms.service;

import java.util.List;

import com.kryptos.hrms.request.PayrollCreateRequestDTO;
import com.kryptos.hrms.request.PayrollUpdateRequestDTO;
import com.kryptos.hrms.response.PayrollResponseDTO;

public interface PayrollService {
	
	PayrollResponseDTO addPayroll(PayrollCreateRequestDTO dto);
	
	PayrollResponseDTO updatePayroll(Long id, PayrollUpdateRequestDTO dto);
	
	List<PayrollResponseDTO> getUserPayroll(Long id);
	
	List<PayrollResponseDTO> getAllUserPayroll(Long id);
	
	List<PayrollResponseDTO> getAllPayroll();


}
