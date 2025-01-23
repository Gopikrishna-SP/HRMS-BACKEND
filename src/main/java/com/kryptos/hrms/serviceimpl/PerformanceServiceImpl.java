package com.kryptos.hrms.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kryptos.hrms.exception.ResourceNotFoundException;
import com.kryptos.hrms.model.User;
import com.kryptos.hrms.model.Performance;
import com.kryptos.hrms.repository.UserRepository;
import com.kryptos.hrms.repository.PerformanceRepository;
import com.kryptos.hrms.request.PerformanceRequestDTO;
import com.kryptos.hrms.response.PerformanceResponseDTO;
import com.kryptos.hrms.service.PerformanceService;

@Service
public class PerformanceServiceImpl implements PerformanceService {

	private final PerformanceRepository performanceRepository;

	private final UserRepository userRepository;

	public PerformanceServiceImpl(PerformanceRepository performanceRepository, UserRepository userRepository) {
		this.performanceRepository = performanceRepository;
		this.userRepository = userRepository;
	}

	public PerformanceResponseDTO addPerformance(PerformanceRequestDTO dto) {
		User user = userRepository.findById(dto.getUserId()).orElseThrow(
				() -> new ResourceNotFoundException("User with ID " + dto.getUserId() + " not found."));

		Performance performance = new Performance();
		performance.setUser(user);
		performance.setGoal(dto.getGoal());
		performance.setReviewPeriod(dto.getReviewPeriod());
		performance.setReviewPeriodEnd(dto.getReviewPeriodEnd());
		performance.setRating(dto.getRating());
		performance.setFeedback(dto.getFeedback());

		Performance savedPerformance = performanceRepository.save(performance);
		return mapToDTO(savedPerformance);
	}

	public List<PerformanceResponseDTO> getAllPerformance() {
		
			List<Performance> performances = performanceRepository.findAll();
			if(performances.isEmpty()) {
				throw new ResourceNotFoundException("No records Found!!");
			}
			List<PerformanceResponseDTO> responseDTOs = new ArrayList<>();
			for (Performance performance : performances) {
				responseDTOs.add(mapToDTO(performance));
			}
			return responseDTOs;
		

	}

	public List<PerformanceResponseDTO> getPerformanceById(Long id) {
		List<Performance> performances = performanceRepository.findByUserUserId(id);

		if (performances.isEmpty()) {
			throw new ResourceNotFoundException("No performance records found for User ID " + id);
		}

		List<PerformanceResponseDTO> responseDTOs = new ArrayList<>();
		for (Performance performance : performances) {
			responseDTOs.add(mapToDTO(performance));
		}
		return responseDTOs;
	}

	public PerformanceResponseDTO updatePerformance(Long id, PerformanceRequestDTO dto) {
		
		Performance performance = performanceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Performance not found!!"));
		User user = userRepository.findById(dto.getUserId()).orElseThrow(
				() -> new ResourceNotFoundException("User with ID " + dto.getUserId() + " not found."));

		performance.setUser(user);
		performance.setGoal(dto.getGoal());
		performance.setReviewPeriod(dto.getReviewPeriod());
		performance.setReviewPeriodEnd(dto.getReviewPeriodEnd());
		performance.setRating(dto.getRating());
		performance.setFeedback(dto.getFeedback());

		Performance savedPerformance = performanceRepository.save(performance);
		return mapToDTO(savedPerformance);
	}

	public String deletePerformance(Long id) {
		if (performanceRepository.existsById(id)) {
			performanceRepository.deleteById(id);
		} else {
			throw new ResourceNotFoundException("Performance records not found.");
		}
		
		return "Performance record deleted succesfully";

	}

	private PerformanceResponseDTO mapToDTO(Performance performance) {
		PerformanceResponseDTO responseDTO = new PerformanceResponseDTO();
		responseDTO.setPerformanceId(performance.getPerformanceId());
		responseDTO.setGoal(performance.getGoal());
		responseDTO.setReviewPeriod(performance.getReviewPeriod());
		responseDTO.setReviewPeriodEnd(performance.getReviewPeriodEnd());
		responseDTO.setRating(performance.getRating());
		responseDTO.setFeedback(performance.getFeedback());
		responseDTO.setCreatedAt(performance.getCreatedAt());
		responseDTO.setUpdatedAt(performance.getUpdatedAt());
		if (performance.getUser() != null) {
			responseDTO.setUserId(performance.getUser().getId());
		} else {
			responseDTO.setUserId(null);
		}

		return responseDTO;
	}
}
