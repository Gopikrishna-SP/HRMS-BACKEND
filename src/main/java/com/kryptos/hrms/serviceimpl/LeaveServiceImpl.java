package com.kryptos.hrms.serviceimpl;

import com.kryptos.hrms.exception.ResourceNotFoundException;
import com.kryptos.hrms.model.User;
import com.kryptos.hrms.model.UserLeave;
import com.kryptos.hrms.model.enums.ELeaveType;
import com.kryptos.hrms.model.Department;
import com.kryptos.hrms.model.Leave;
import com.kryptos.hrms.model.MasterLeave;
import com.kryptos.hrms.repository.UserRepository;
import com.kryptos.hrms.repository.DepartmentRepository;
import com.kryptos.hrms.repository.LeaveRepository;
import com.kryptos.hrms.repository.MasterLeaveRepository;
import com.kryptos.hrms.repository.UserLeaveRepository;
import com.kryptos.hrms.request.LeaveApproveRequestDTO;
import com.kryptos.hrms.request.LeaveRequestDTO;
import com.kryptos.hrms.response.AttendanceSummaryResponseDTO;
import com.kryptos.hrms.response.LeaveApproveResponseDTO;
import com.kryptos.hrms.response.LeaveResponseDTO;
import com.kryptos.hrms.response.LeaveSummaryResponseDTO;
import com.kryptos.hrms.service.LeaveService;

import jakarta.validation.Valid;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LeaveServiceImpl implements LeaveService {

	private final LeaveRepository leaveRepository;

	private final UserRepository userRepository;

	private final DepartmentRepository departmentRepository;

	private final UserLeaveRepository userLeaveRepository;

	private MasterLeaveRepository masterLeaveRepository;

	public LeaveServiceImpl(LeaveRepository leaveRepository, UserRepository userRepository,
			DepartmentRepository departmentRepository, UserLeaveRepository userLeaveRepository) {
		super();
		this.leaveRepository = leaveRepository;
		this.userRepository = userRepository;
		this.departmentRepository = departmentRepository;
		this.userLeaveRepository = userLeaveRepository;
	}

	@Override
	public LeaveResponseDTO applyLeave(LeaveRequestDTO dto) {
		User user = userRepository.findById(dto.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("User with ID " + dto.getUserId() + " not found."));
		Department department = departmentRepository.findById(dto.getDepartmentId()).orElseThrow(
				() -> new ResourceNotFoundException("Department with ID " + dto.getDepartmentId() + " not found."));
		Leave leave = new Leave();
		leave.setUser(user);
		leave.setDepartment(department);

		if (dto.getStartDate() == null || dto.getEndDate() == null) {
			throw new IllegalArgumentException("Start date and end date must not be null");
		}

		LocalDate startDate = dto.getStartDate();
		LocalDate endDate = dto.getEndDate();
		leave.setLeaveType(dto.getLeaveType());
		leave.setStartDate(dto.getStartDate());
		leave.setEndDate(dto.getEndDate());
		long duration = ChronoUnit.DAYS.between(leave.getStartDate(), leave.getEndDate()) + 1;
		leave.setLeaveDuration((int) duration);
		leave.setApprover(dto.getApprover());

		leave.setReason(dto.getReason());
		Leave savedLeave = leaveRepository.save(leave);
		return mapToDTO(savedLeave);
	}

	@Override
	public LeaveResponseDTO updateLeave(Long id, LeaveRequestDTO dto) {
		Optional<Leave> leaveUpdate = leaveRepository.findById(id);

		if (leaveUpdate.isEmpty()) {
			throw new ResourceNotFoundException("Leave records not found for ID : " + id);
		}

		Leave leave = leaveUpdate.get();

		leave.setLeaveType(dto.getLeaveType());
		leave.setStartDate(dto.getStartDate());
		leave.setEndDate(dto.getEndDate());
		long duration = ChronoUnit.DAYS.between(leave.getStartDate(), leave.getEndDate()) + 1;
		leave.setLeaveDuration((int) duration);
		leave.setApprover(dto.getApprover());
		leave.setReason(dto.getReason());

		Leave updatedLeave = leaveRepository.save(leave);
		return mapToDTO(updatedLeave);
	}

	
	@Override
	public List<LeaveResponseDTO> getLeaveByUserId(Long id) {
		List<Leave> leaves = leaveRepository.findByUserId(id);

		List<LeaveResponseDTO> responseDTOs = new ArrayList<>();
		for (Leave leave : leaves) {
			responseDTOs.add(mapToDTO(leave));

		}
		return responseDTOs;
	}

	@Override
	public List<LeaveResponseDTO> getAllUserLeave(Long id) {
		List<Leave> leaves = leaveRepository.findByDepartmentDepartmentId(id);
		if (leaves.isEmpty()) {
			throw new ResourceNotFoundException("No leave records found for user ID " + id);
		}

		List<LeaveResponseDTO> responseDTOs = new ArrayList<>();
		for (Leave leave : leaves) {
			responseDTOs.add(mapToDTO(leave));

		}
		return responseDTOs;
	}

	@Override
	public List<LeaveResponseDTO> getAllLeave() {
		List<Leave> leaves = leaveRepository.findAll();
		if (leaves.isEmpty()) {
			throw new ResourceNotFoundException("No leave records found.");
		}
		List<LeaveResponseDTO> responseDTOs = new ArrayList<>();

		leaves.forEach(leave -> {
			responseDTOs.add(mapToDTO(leave));
		});

		return responseDTOs;
	}

	@Override
	public boolean deleteLeave(Long id) {
		if (!leaveRepository.existsById(id)) {
			throw new ResourceNotFoundException("Leave records not found.");
		}

		leaveRepository.deleteById(id);
		return true;
	}

	private LeaveResponseDTO mapToDTO(Leave leave) {
		LeaveResponseDTO responseDTO = new LeaveResponseDTO();
		responseDTO.setLeaveId(leave.getLeaveId());
		responseDTO.setUserId(leave.getUser().getId());
		responseDTO.setDepartmentId(leave.getDepartment().getDepartmentId());
		responseDTO.setLeaveType(leave.getLeaveType());
		responseDTO.setStartDate(leave.getStartDate());
		responseDTO.setEndDate(leave.getEndDate());
		responseDTO.setStatus(leave.getStatus());
		responseDTO.setLeaveDuration((int) leave.getLeaveDuration());
		responseDTO.setApprover(leave.getApprover());
		responseDTO.setReason(leave.getReason());

		responseDTO.setCreatedAt(leave.getCreatedAt());
		responseDTO.setUpdatedAt(leave.getUpdatedAt());
		if (leave.getUser() != null) {
			responseDTO.setUserId(leave.getUser().getId());
		} else {
			responseDTO.setUserId(null);
		}

		if (leave.getDepartment() != null) {
			responseDTO.setDepartmentId(leave.getDepartment().getDepartmentId());
		} else {
			responseDTO.setDepartmentId(null);
		}

		return responseDTO;
	}
	
	@Override
	public LeaveApproveResponseDTO approveLeave(Long id, @Valid LeaveApproveRequestDTO dto) {
	    Leave leave = leaveRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Leave record not found for ID: " + id));
	    leave.setStatus(dto.getStatus());


	   
	       
	    leaveRepository.save(leave);

	    LeaveApproveResponseDTO responseDTO = new LeaveApproveResponseDTO();
	    responseDTO.setStatus(leave.getStatus());
	    return responseDTO;
	}

	@Override
	public LeaveSummaryResponseDTO getLeaveSummary(Long id) {
	    UserLeave userLeave = userLeaveRepository.findByUserId(id).orElseThrow(() -> new ResourceNotFoundException("User with ID " + id + " not found"));
	    MasterLeave masterLeave = masterLeaveRepository.findById(1L).orElseThrow(() -> new ResourceNotFoundException("Master leave record not found"));

	    int totalLeave = masterLeave.getSick() + masterLeave.getCasual() ;
	    int leaveTaken = userLeave.getSickTaken() + userLeave.getCasualTaken();
	    int remainingLeave = totalLeave - leaveTaken;

	    return new LeaveSummaryResponseDTO(totalLeave, leaveTaken, remainingLeave);
	}
}