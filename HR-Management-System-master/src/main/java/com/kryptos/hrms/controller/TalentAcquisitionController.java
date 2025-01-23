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

import com.kryptos.hrms.request.TalentAcquisitionRequestDTO;
import com.kryptos.hrms.response.TalentAcquisitionResponseDTO;
import com.kryptos.hrms.service.TalentAcquisitionServiceImpl;
import com.kryptos.hrms.response.ResponseMessage;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/job")
public class TalentAcquisitionController {
	@Autowired
	private TalentAcquisitionServiceImpl talentAcquisitionServiceImpl;
	


	@PostMapping("/add")
	@PreAuthorize("hasRole('ADMIN') or hasRole('HUMANRESOURCE') ")
    public ResponseEntity<ResponseMessage<TalentAcquisitionResponseDTO>> addTalentAcquisition(@Valid @RequestBody TalentAcquisitionRequestDTO talentAcquisition) {
		TalentAcquisitionResponseDTO addedTalentAcquisition = talentAcquisitionServiceImpl.addJob(talentAcquisition);
		ResponseMessage<TalentAcquisitionResponseDTO> response = new ResponseMessage<TalentAcquisitionResponseDTO>(true, 1,
				"TalentAcquisition added successfully", addedTalentAcquisition);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);  
    }
	
    @PutMapping("/update/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('HUMANRESOURCE') ")
    public ResponseEntity<ResponseMessage<TalentAcquisitionResponseDTO>> updateTalentAcquisition(@PathVariable Long id,@Valid @RequestBody TalentAcquisitionRequestDTO TalentAcquisition) {

            TalentAcquisitionResponseDTO updatedTalentAcquisition = talentAcquisitionServiceImpl.updateJob(id, TalentAcquisition);
    		ResponseMessage<TalentAcquisitionResponseDTO> response = new ResponseMessage<TalentAcquisitionResponseDTO>(true, 1,
    				"TalentAcquisition added successfully", updatedTalentAcquisition);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        
    }
	
	@GetMapping("/get-all")
	@PreAuthorize("hasRole('ADMIN') or hasRole('HUMANRESOURCE')")
	public ResponseEntity<ResponseMessage<List<TalentAcquisitionResponseDTO>>> getAllTalentAcquisition(){
		List<TalentAcquisitionResponseDTO> allTalentAcquisitions = talentAcquisitionServiceImpl.getAllJob();
		ResponseMessage<List<TalentAcquisitionResponseDTO>> response = new ResponseMessage<List<TalentAcquisitionResponseDTO>>(true, 1,
				"TalentAcquisition added successfully", allTalentAcquisitions);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	

    @DeleteMapping("/delete/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('HUMANRESOURCE')")
    public ResponseEntity<ResponseMessage<String>> deleteTalentAcquisition(@PathVariable Long id) {

            String deletedTalentAcquisition = talentAcquisitionServiceImpl.deleteJob(id);
    		ResponseMessage<String> response = new ResponseMessage<String>(true, 1,
    				"TalentAcquisition deleted successfully", deletedTalentAcquisition);
            return ResponseEntity.status(HttpStatus.OK).body(response);
       
    }

}
