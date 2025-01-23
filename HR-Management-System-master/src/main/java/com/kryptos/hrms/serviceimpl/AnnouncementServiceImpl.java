package com.kryptos.hrms.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kryptos.hrms.exception.ResourceNotFoundException;
import com.kryptos.hrms.model.Announcement;
import com.kryptos.hrms.repository.AnnouncementRepository;
import com.kryptos.hrms.request.AnnouncementRequestDTO;
import com.kryptos.hrms.response.AnnouncementResponseDTO;
import com.kryptos.hrms.service.AnnouncementService;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {
	
	public final AnnouncementRepository announcementRepository;
		
	

	public AnnouncementServiceImpl(AnnouncementRepository announcementRepository) {
		super();
		this.announcementRepository = announcementRepository;
	}

	@Override
	public AnnouncementResponseDTO addAnnouncement(AnnouncementRequestDTO dto) {

		Announcement announcement = new Announcement();
		announcement.setAuthor(dto.getAuthor());
		announcement.setDatePublished(dto.getDatePublished());
		announcement.setDescription(dto.getDescription());
		announcement.setTitle(dto.getTitle());
		
		Announcement save = announcementRepository.save(announcement);
		return mapToDTO(save);
	}

	@Override
	public AnnouncementResponseDTO updateAnnouncement(Long id, AnnouncementRequestDTO dto) {
		Announcement announcement = announcementRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Announcement record not found"));
		announcement.setAuthor(dto.getAuthor());
		announcement.setDatePublished(dto.getDatePublished());
		announcement.setDescription(dto.getDescription());
		announcement.setTitle(dto.getTitle());
		
		Announcement save = announcementRepository.save(announcement);
		return mapToDTO(save);
	}

	@Override
	public List<AnnouncementResponseDTO> getAllAnnouncement() {
		List<Announcement> announcement = announcementRepository.findAll();
		if(announcement.isEmpty()) {
			throw new ResourceNotFoundException("No Announcement!!");
		}
		List<AnnouncementResponseDTO> responseDTOs = new ArrayList<>();
		for (Announcement annoucements : announcement) {
			responseDTOs.add(mapToDTO(annoucements));
		}

		return responseDTOs;
	}

	@Override
	public void deleteAnnouncement(Long id) {
		if (announcementRepository.existsById(id)) {
			announcementRepository.deleteById(id);
		} else {
			new ResourceNotFoundException("Department not found for ID : " + id);
		}
	}
	
	private AnnouncementResponseDTO mapToDTO(Announcement announcement) {
		AnnouncementResponseDTO responseDTO = new AnnouncementResponseDTO();
		responseDTO.setAnnouncementId(announcement.getAnnouncementId());
		responseDTO.setAuthor(announcement.getAuthor());
		responseDTO.setDescription(announcement.getDescription());
		responseDTO.setTitle(announcement.getTitle());
		responseDTO.setDatePublished(announcement.getDatePublished());
		responseDTO.setCreatedAt(announcement.getCreatedAt());
		responseDTO.setUpdatedAt(announcement.getUpdatedAt());
		

		return responseDTO;
	}


	

}
