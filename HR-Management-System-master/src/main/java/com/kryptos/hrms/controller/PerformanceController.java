package com.kryptos.hrms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kryptos.hrms.request.PerformanceRequestDTO;
import com.kryptos.hrms.response.LeaveResponseDTO;
import com.kryptos.hrms.response.PerformanceResponseDTO;
import com.kryptos.hrms.response.ResponseMessage;
import com.kryptos.hrms.serviceimpl.PerformanceServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/performance")
public class PerformanceController {
	
	@Autowired
	private PerformanceServiceImpl performanceServiceImpl;
	


	@PostMapping("/add")
	@PreAuthorize("hasRole('ADMIN') or hasRole('HUMANRESOURCE') or hasRole('MANAGER')")
    public ResponseEntity<ResponseMessage<PerformanceResponseDTO>> addPerformance(@Valid @RequestBody PerformanceRequestDTO performance) {
		PerformanceResponseDTO addedPerformance = performanceServiceImpl.addPerformance(performance);
		ResponseMessage<PerformanceResponseDTO> response = new ResponseMessage<PerformanceResponseDTO>(true, 1,
				"Performance added successfully", addedPerformance);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);  
    }
	
	@GetMapping("/get-all")
	@PreAuthorize("hasRole('ADMIN') or hasRole('HUMANRESOURCE') or hasRole('MANAGER')")
	public ResponseEntity<ResponseMessage<List<PerformanceResponseDTO>>> getAllPerformance(){
		List<PerformanceResponseDTO> allPerformances = performanceServiceImpl.getAllPerformance();
		ResponseMessage<List<PerformanceResponseDTO>> response = new ResponseMessage<List<PerformanceResponseDTO>>(true, 1,
				"Performance added successfully", allPerformances);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping("/get/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('HUMANRESOURCE') or hasRole('MANAGER')")
    public ResponseEntity<ResponseMessage<List<PerformanceResponseDTO>>> getPerformanceById(@PathVariable Long id) {
        List<PerformanceResponseDTO> performance = performanceServiceImpl.getPerformanceById(id);
		ResponseMessage<List<PerformanceResponseDTO>> response = new ResponseMessage<List<PerformanceResponseDTO>>(true, 1,
				"Performance added successfully", performance);
        return ResponseEntity.status(HttpStatus.OK).body(response);
        
    }

    @PutMapping("/update/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('HUMANRESOURCE') or hasRole('MANAGER')")
    public ResponseEntity<ResponseMessage<PerformanceResponseDTO>> updatePerformance(@PathVariable Long id,@Valid @RequestBody PerformanceRequestDTO Performance) {

            PerformanceResponseDTO updatedPerformance = performanceServiceImpl.updatePerformance(id, Performance);
    		ResponseMessage<PerformanceResponseDTO> response = new ResponseMessage<PerformanceResponseDTO>(true, 1,
    				"Performance added successfully", updatedPerformance);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        
    }

    @DeleteMapping("/delete/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('HUMANRESOURCE') or hasRole('MANAGER')")
    public ResponseEntity<ResponseMessage<String>> deletePerformance(@PathVariable Long id) {

            String deletedPerformance = performanceServiceImpl.deletePerformance(id);
    		ResponseMessage<String> response = new ResponseMessage<String>(true, 1,
    				"Performance deleted successfully", deletedPerformance);
            return ResponseEntity.status(HttpStatus.OK).body(response);
       
    }

}
