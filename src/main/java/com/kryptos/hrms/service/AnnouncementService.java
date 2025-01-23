package com.kryptos.hrms.service;

import java.util.List;

import com.kryptos.hrms.request.AnnouncementRequestDTO;
import com.kryptos.hrms.response.AnnouncementResponseDTO;

public interface AnnouncementService {
	
	AnnouncementResponseDTO addAnnouncement(AnnouncementRequestDTO dto);
	
	AnnouncementResponseDTO updateAnnouncement(Long id, AnnouncementRequestDTO dto);
	
	List<AnnouncementResponseDTO> getAllAnnouncement();
	
	void deleteAnnouncement(Long id);


}
