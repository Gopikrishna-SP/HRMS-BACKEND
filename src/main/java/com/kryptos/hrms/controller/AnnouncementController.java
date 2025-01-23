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

import com.kryptos.hrms.request.AnnouncementRequestDTO;
import com.kryptos.hrms.response.AnnouncementResponseDTO;
import com.kryptos.hrms.response.ResponseMessage;
import com.kryptos.hrms.serviceimpl.AnnouncementServiceImpl;

@RestController
@RequestMapping("/api/v1/announcement")
public class AnnouncementController {
	
	@Autowired
	public AnnouncementServiceImpl announcementServiceImpl;
	
	@PostMapping("/add")
	@PreAuthorize("hasRole('ADMIN') or hasRole('HUMANRESOURCE')")
	public ResponseEntity<ResponseMessage<AnnouncementResponseDTO>> addAnnouncement(@RequestBody AnnouncementRequestDTO dto) {
		AnnouncementResponseDTO addedAnnouncement  = announcementServiceImpl.addAnnouncement(dto);
		ResponseMessage<AnnouncementResponseDTO> response = new ResponseMessage<>(true, 1, "Announcement added successfully",
				addedAnnouncement);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@PutMapping("/update/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('HUMANRESOURCE')")
	public ResponseEntity<ResponseMessage<AnnouncementResponseDTO>> updateAnnouncement(@PathVariable Long id, @RequestBody AnnouncementRequestDTO dto) {
		AnnouncementResponseDTO addedAnnouncement  = announcementServiceImpl.updateAnnouncement(id, dto);
		ResponseMessage<AnnouncementResponseDTO> response = new ResponseMessage<>(true, 1, "Announcement updated successfully",
				addedAnnouncement);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping("/get-all")
	@PreAuthorize("hasRole('ADMIN') or hasRole('HUMANRESOURCE')or hasRole('MANAGER')or hasRole('EMPLOYEE') ")
	public ResponseEntity<ResponseMessage<List<AnnouncementResponseDTO>>> getAllDepartment() {
			List<AnnouncementResponseDTO> getAllAnnouncement = announcementServiceImpl.getAllAnnouncement();
			ResponseMessage<List<AnnouncementResponseDTO>> response = new ResponseMessage<List<AnnouncementResponseDTO>>(
					true, 1, "Announcement fetched successfully", getAllAnnouncement);
			return ResponseEntity.status(HttpStatus.OK).body(response);


	}
	
	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('HUMANRESOURCE') ")
	public ResponseEntity<Void> deleteAnnouncement(@PathVariable Long id) {
			announcementServiceImpl.deleteAnnouncement(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	

}
