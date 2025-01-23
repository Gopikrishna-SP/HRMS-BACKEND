package com.kryptos.hrms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kryptos.hrms.exception.ResourceNotFoundException;
import com.kryptos.hrms.model.TalentAcquisition;
import com.kryptos.hrms.repository.TalentAcquisitionRepository;
import com.kryptos.hrms.request.TalentAcquisitionRequestDTO;
import com.kryptos.hrms.response.TalentAcquisitionResponseDTO;

@Service
public class TalentAcquisitionServiceImpl implements TalentAcquisitionService {

	@Autowired
	private TalentAcquisitionRepository talentAcquisitionRepository;
	

	@Override
	public TalentAcquisitionResponseDTO addJob(TalentAcquisitionRequestDTO dto) {

		TalentAcquisition job = new TalentAcquisition();
		job.setJobTitle(dto.getJobTitle());;
		job.setDescription(dto.getDescription());
		job.setPostedDate(dto.getPostedDate());
		job.setClosingDate(dto.getClosingDate());


		TalentAcquisition savedTalentAcquisition = talentAcquisitionRepository.save(job);
		return mapToDTO(savedTalentAcquisition);
	}

	@Override
	public TalentAcquisitionResponseDTO updateJob(Long id, TalentAcquisitionRequestDTO dto) {
	
		TalentAcquisition job = talentAcquisitionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Job ID not found!!"));
		job.setJobTitle(dto.getJobTitle());;
		job.setDescription(dto.getDescription());
		job.setPostedDate(dto.getPostedDate());
		job.setClosingDate(dto.getClosingDate());

		TalentAcquisition savedTalentAcquisition = talentAcquisitionRepository.save(job);
		return mapToDTO(savedTalentAcquisition);
	}

	@Override
	public List<TalentAcquisitionResponseDTO> getAllJob() {
		List<TalentAcquisition> jobs = talentAcquisitionRepository.findAll();
		if(jobs.isEmpty()) {
			throw new ResourceNotFoundException("No records Found!!");
		}
		List<TalentAcquisitionResponseDTO> responseDTOs = new ArrayList<>();
		for (TalentAcquisition job : jobs) {
			responseDTOs.add(mapToDTO(job));
		}
		return responseDTOs;
	}

	@Override
	public String deleteJob(Long id) {
		if (talentAcquisitionRepository.existsById(id)) {
			talentAcquisitionRepository.deleteById(id);
		} else {
			throw new ResourceNotFoundException("TalentAcquisition records not found.");
		}
		
		return "Job record deleted succesfully";

	}

	private TalentAcquisitionResponseDTO mapToDTO(TalentAcquisition job) {
		TalentAcquisitionResponseDTO responseDTO = new TalentAcquisitionResponseDTO();
		responseDTO.setId(job.getId());
		responseDTO.setJobTitle(job.getJobTitle());;
		responseDTO.setDescription(job.getDescription());
		responseDTO.setPostedDate(job.getPostedDate());
		responseDTO.setClosingDate(job.getClosingDate());


		return responseDTO;
	}
	

}
