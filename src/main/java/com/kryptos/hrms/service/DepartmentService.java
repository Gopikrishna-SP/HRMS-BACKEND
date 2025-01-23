package com.kryptos.hrms.service;

import java.util.List;

import com.kryptos.hrms.request.DepartmentRequestDTO;
import com.kryptos.hrms.response.DepartmentResponseDTO;

public interface DepartmentService {

	DepartmentResponseDTO addDepartment(DepartmentRequestDTO dto);

	DepartmentResponseDTO updateDepartment(Long id, DepartmentRequestDTO department);

	DepartmentResponseDTO getDepartment(Long id);

	List<DepartmentResponseDTO> getAllDepartment();

	void deleteDepartment(Long id);

}
