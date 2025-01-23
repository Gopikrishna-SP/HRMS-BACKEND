package com.kryptos.hrms.serviceimpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kryptos.hrms.exception.DuplicateFoundException;
import com.kryptos.hrms.exception.ResourceNotFoundException;
import com.kryptos.hrms.model.Department;
import com.kryptos.hrms.repository.DepartmentRepository;
import com.kryptos.hrms.repository.UserRepository;
import com.kryptos.hrms.request.DepartmentRequestDTO;
import com.kryptos.hrms.response.DepartmentResponseDTO;
import com.kryptos.hrms.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	private final DepartmentRepository departmentRepository;

	private final UserRepository userRepository;

	public DepartmentServiceImpl(DepartmentRepository departmentRepository, UserRepository userRepository) {
		this.departmentRepository = departmentRepository;
		this.userRepository = userRepository;
	}

	@Override
	public DepartmentResponseDTO addDepartment(DepartmentRequestDTO dto) {
	    departmentRepository.findByDepartmentName(dto.getDepartmentName())
        .ifPresent(department -> {
            throw new DuplicateFoundException("Department Name already exists.");
        });
		
		Department department = new Department();
		department.setDepartmentName(dto.getDepartmentName());
		department.setDepartmentHead(dto.getDepartmentHead());
		department.setCreatedAt(LocalDateTime.now());
		updateNumberOfUsers(department);
		department = departmentRepository.save(department);
		return mapToDTO(department);
	}

	@Override
	public DepartmentResponseDTO updateDepartment(Long id, DepartmentRequestDTO dto) {
		Department department = departmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Department not found for Id : " + id));
		department.setDepartmentName(dto.getDepartmentName());
		department.setDepartmentHead(dto.getDepartmentHead());
		updateNumberOfUsers(department);
		department.setUpdatedAt(LocalDateTime.now());
		department = departmentRepository.save(department);
		return mapToDTO(department);
	}

	@Override
	public DepartmentResponseDTO getDepartment(Long id) {
		Department department = departmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Department not found for ID : " + id));
		updateNumberOfUsers(department);

		department = departmentRepository.save(department);
		return mapToDTO(department);
	}

	@Override
	public List<DepartmentResponseDTO> getAllDepartment() {
		List<Department> departments = departmentRepository.findAll();
		List<DepartmentResponseDTO> responseDTOs = new ArrayList<>();
		for (Department department : departments) {
			responseDTOs.add(mapToDTO(department));
		}

		return responseDTOs;
	}

	@Override
	public void deleteDepartment(Long id) {
		if (departmentRepository.existsById(id)) {
			departmentRepository.deleteById(id);
		} else {
			new ResourceNotFoundException("Department not found for ID : " + id);
		}
	}

	private DepartmentResponseDTO mapToDTO(Department department) {
		DepartmentResponseDTO responseDTO = new DepartmentResponseDTO();
		responseDTO.setDepartmentId(department.getDepartmentId());
		responseDTO.setDepartmentName(department.getDepartmentName());
		responseDTO.setDepartmentHead(department.getDepartmentHead());
		responseDTO.setNumberOfUsers(department.getNumberOfUsers());
		responseDTO.setCreatedAt(department.getCreatedAt());
		responseDTO.setUpdatedAt(department.getUpdatedAt());
		return responseDTO;
	}

	public void updateNumberOfUsers(Department department) {
		Long numberOfUsers = userRepository.countByDepartmentId(department.getDepartmentId());

		department.setNumberOfUsers(numberOfUsers);

		departmentRepository.save(department);
	}

}
