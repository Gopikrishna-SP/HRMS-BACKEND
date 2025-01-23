package com.kryptos.hrms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kryptos.hrms.response.UpcomingBdayResponseDTO;
import com.kryptos.hrms.response.ResponseMessage;
import com.kryptos.hrms.serviceimpl.UpcomingBdayServiceImpl;
@RestController
@RequestMapping("/v1/api/birthday")
public class UpcomingBdayController {

    @Autowired
    private UpcomingBdayServiceImpl upcomingBdayServiceImpl;

    @GetMapping("/get-all")
	@PreAuthorize("hasRole('ADMIN') or hasRole('HUMANRESOURCE') or hasRole('MANAGER') or hasRole('EMPLOYEE')")
    public ResponseEntity<ResponseMessage<List<UpcomingBdayResponseDTO>>> getAllBday() {
        List<UpcomingBdayResponseDTO> statistics = upcomingBdayServiceImpl.getAllBdays();

        // Creating a response with a List of UpcomingBdayResponseDTO
        ResponseMessage<List<UpcomingBdayResponseDTO>> response = new ResponseMessage<>(true, 1,
                "Upcoming birthdays fetched successfully", statistics);
        
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
