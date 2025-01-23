package com.kryptos.hrms.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.kryptos.hrms.request.DepartmentRequestDTO;
import com.kryptos.hrms.response.DepartmentResponseDTO;
import com.kryptos.hrms.response.ResponseMessage;
import com.kryptos.hrms.service.DepartmentService;

@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController {

	private final DepartmentService departmentService;

	public DepartmentController(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	@PostMapping("/add")
	@PreAuthorize("hasRole('ADMIN') or hasRole('HUMANRESOURCE') ")
	public ResponseEntity<ResponseMessage<DepartmentResponseDTO>> addDepartment(@RequestBody DepartmentRequestDTO dto) {
			DepartmentResponseDTO addDepartment = departmentService.addDepartment(dto);
			ResponseMessage<DepartmentResponseDTO> response = new ResponseMessage<DepartmentResponseDTO>(true, 1,
					"Department created successfully", addDepartment);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);

	}

	@PutMapping("/update/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('HUMANRESOURCE') ")
	public ResponseEntity<ResponseMessage<DepartmentResponseDTO>> updateDepartment(@PathVariable Long id,
			@RequestBody DepartmentRequestDTO dto) {
			DepartmentResponseDTO updatedDepartment = departmentService.updateDepartment(id, dto);
			ResponseMessage<DepartmentResponseDTO> response = new ResponseMessage<DepartmentResponseDTO>(true, 1,
					"Department updated successfully", updatedDepartment);
			return ResponseEntity.status(HttpStatus.OK).body(response);

	}

	@GetMapping("/get-all")
	@PreAuthorize("hasRole('ADMIN') or hasRole('HUMANRESOURCE') ")
	public ResponseEntity<ResponseMessage<List<DepartmentResponseDTO>>> getAllDepartment() {
			List<DepartmentResponseDTO> getAllDepartment = departmentService.getAllDepartment();
			ResponseMessage<List<DepartmentResponseDTO>> response = new ResponseMessage<List<DepartmentResponseDTO>>(true, 1,
					"Department created successfully", getAllDepartment);
			return ResponseEntity.status(HttpStatus.OK).body(response);


	}

	@GetMapping("/get/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('HUMANRESOURCE') ")
	public ResponseEntity<ResponseMessage<DepartmentResponseDTO>> getDepartment(@PathVariable Long id) {
			DepartmentResponseDTO department = departmentService.getDepartment(id);
			ResponseMessage<DepartmentResponseDTO> response = new ResponseMessage<DepartmentResponseDTO>(true, 1,
					"Department created successfully", department);
			return ResponseEntity.status(HttpStatus.OK).body(response);

	}

	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('HUMANRESOURCE') ")
	public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
			departmentService.deleteDepartment(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	}
}