package com.kryptos.hrms.service;

import java.util.List;

import com.kryptos.hrms.request.TalentAcquisitionRequestDTO;
import com.kryptos.hrms.response.TalentAcquisitionResponseDTO;

public interface TalentAcquisitionService {
	
	TalentAcquisitionResponseDTO addJob(TalentAcquisitionRequestDTO dto);
	
	TalentAcquisitionResponseDTO updateJob(Long id, TalentAcquisitionRequestDTO dto);

	List<TalentAcquisitionResponseDTO> getAllJob();
	
	String deleteJob(Long id);

}
