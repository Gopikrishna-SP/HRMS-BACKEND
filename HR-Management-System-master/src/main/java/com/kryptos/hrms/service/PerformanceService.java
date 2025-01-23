package com.kryptos.hrms.service;

import java.util.List;

import com.kryptos.hrms.request.PerformanceRequestDTO;
import com.kryptos.hrms.response.PerformanceResponseDTO;

public interface PerformanceService {
	
	PerformanceResponseDTO addPerformance(PerformanceRequestDTO dto);
	
	PerformanceResponseDTO updatePerformance(Long id, PerformanceRequestDTO dto);

	List<PerformanceResponseDTO> getPerformanceById(Long id);

	List<PerformanceResponseDTO> getAllPerformance();
	
	String deletePerformance(Long id);


}
