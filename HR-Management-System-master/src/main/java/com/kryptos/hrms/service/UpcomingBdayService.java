package com.kryptos.hrms.service;

import java.util.List;

import com.kryptos.hrms.response.PerformanceResponseDTO;
import com.kryptos.hrms.response.UpcomingBdayResponseDTO;

public interface UpcomingBdayService {
	
	List<UpcomingBdayResponseDTO> getAllBdays();


}
