package com.kryptos.hrms.serviceimpl;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kryptos.hrms.model.UpcomingBday;
import com.kryptos.hrms.model.User;
import com.kryptos.hrms.repository.UserRepository;
import com.kryptos.hrms.response.UpcomingBdayResponseDTO;
import com.kryptos.hrms.service.UpcomingBdayService;

@Service
public class UpcomingBdayServiceImpl implements UpcomingBdayService {
	
	@Autowired
	private UserRepository userRepository;
	@Override
	public List<UpcomingBdayResponseDTO> getAllBdays() {
	    LocalDate today = LocalDate.now();
	    Month currentMonth = today.getMonth();

	    List<User> users = userRepository.findAll();
	    List<UpcomingBdayResponseDTO> responseDTOs = new ArrayList<>();

	    for (User user : users) {
	        if (user.getPersonalDetails() != null && !user.getPersonalDetails().isEmpty()) {
	            LocalDate dob = user.getPersonalDetails().get(0).getDateOfBirth();
	            if (dob != null && dob.getMonth() == currentMonth && dob.getDayOfMonth() >= today.getDayOfMonth()) {
	                responseDTOs.add(mapToDTO(user));
	            }
	        }
	    }

	    return responseDTOs;
	}

	private UpcomingBdayResponseDTO mapToDTO(User user) {
	    UpcomingBdayResponseDTO responseDTO = new UpcomingBdayResponseDTO();
	    responseDTO.setId(user.getId());
	    String name = user.getPersonalDetails().get(0).getFirstName() + " " +
	                  user.getPersonalDetails().get(0).getLastName();
	    responseDTO.setName(name);
	    responseDTO.setBirthday(user.getPersonalDetails().get(0).getDateOfBirth());
	    return responseDTO;
	}

}
