
package com.kryptos.hrms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kryptos.hrms.serviceimpl.PayrollServiceImpl;

import jakarta.validation.Valid;

import com.kryptos.hrms.request.PayrollCreateRequestDTO;
import com.kryptos.hrms.request.PayrollUpdateRequestDTO;
import com.kryptos.hrms.response.LeaveResponseDTO;
import com.kryptos.hrms.response.PayrollResponseDTO;
import com.kryptos.hrms.response.ResponseMessage;

@RestController
@RequestMapping("/api/v1/payroll")
public class PayrollController {

	@Autowired
	private PayrollServiceImpl payrollServiceImpl;  

  
    @PostMapping("/add")
	@PreAuthorize("hasRole('ADMIN') or hasRole('HUMANRESOURCE') or hasRole('MANAGER')")
    public ResponseEntity<ResponseMessage<PayrollResponseDTO>> addPayroll(@Valid @RequestBody PayrollCreateRequestDTO dto) {
        PayrollResponseDTO addedPayroll = payrollServiceImpl.addPayroll(dto);
		ResponseMessage<PayrollResponseDTO> response = new ResponseMessage<PayrollResponseDTO>(true, 1,
				"Payroll added successfully", addedPayroll);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);  
    }

    @GetMapping("/get/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('HUMANRESOURCE') or hasRole('MANAGER') or hasRole('EMPLOYEE')")
    public ResponseEntity<ResponseMessage<List<PayrollResponseDTO>>> getPayroll(@PathVariable Long id) {
        List<PayrollResponseDTO> allPayrolls = payrollServiceImpl.getUserPayroll(id);
		ResponseMessage<List<PayrollResponseDTO>> response = new ResponseMessage<List<PayrollResponseDTO>>(true, 1,
				"Attendance marked successfully", allPayrolls);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/get-department/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('HUMANRESOURCE') or hasRole('MANAGER')")
    public ResponseEntity<ResponseMessage<List<PayrollResponseDTO>>> getDepartmentPayroll(@PathVariable Long id) {
        List<PayrollResponseDTO> departmentPayrolls = payrollServiceImpl.getAllUserPayroll(id);
		ResponseMessage<List<PayrollResponseDTO>> response = new ResponseMessage<List<PayrollResponseDTO>>(true, 1,
				"Payroll fetched successfully", departmentPayrolls);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    @GetMapping("/get-all")
	@PreAuthorize("hasRole('ADMIN') or hasRole('HUMANRESOURCE') or hasRole('MANAGER')")
    public ResponseEntity<ResponseMessage<List<PayrollResponseDTO>>> getAllPayroll() {
        List<PayrollResponseDTO> allPayrolls = payrollServiceImpl.getAllPayroll();
		ResponseMessage<List<PayrollResponseDTO>> response = new ResponseMessage<List<PayrollResponseDTO>>(true, 1,
				"All Payroll fetched successfully", allPayrolls);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('HUMANRESOURCE') or hasRole('MANAGER')")
    public ResponseEntity<ResponseMessage<PayrollResponseDTO>> updatePayroll(@PathVariable Long id,@Valid @RequestBody PayrollUpdateRequestDTO dto) {
        PayrollResponseDTO updatedPayroll = payrollServiceImpl.updatePayroll(id, dto);
		ResponseMessage<PayrollResponseDTO> response = new ResponseMessage<PayrollResponseDTO>(true, 1,
				"Attendance marked successfully", updatedPayroll);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
