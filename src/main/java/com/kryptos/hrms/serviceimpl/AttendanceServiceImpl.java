package com.kryptos.hrms.serviceimpl;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.kryptos.hrms.exception.ResourceNotFoundException;
import com.kryptos.hrms.model.Attendance;
import com.kryptos.hrms.model.Department;
import com.kryptos.hrms.model.User;
import com.kryptos.hrms.model.enums.EAttendanceStatus;
import com.kryptos.hrms.repository.AttendanceRepository;
import com.kryptos.hrms.repository.DepartmentRepository;
import com.kryptos.hrms.repository.UserRepository;
import com.kryptos.hrms.response.AttendanceSummaryResponseDTO;
import com.kryptos.hrms.response.AttendanceResponseDTO;
import com.kryptos.hrms.service.AttendanceService;

@Service
public class AttendanceServiceImpl implements AttendanceService {

	private final AttendanceRepository attendanceRepository;

	private final UserRepository userRepository;

	private final DepartmentRepository departmentRepository;

	public AttendanceServiceImpl(AttendanceRepository attendanceRepository, UserRepository userRepository,
			DepartmentRepository departmentRepository) {
		super();
		this.attendanceRepository = attendanceRepository;
		this.userRepository = userRepository;
		this.departmentRepository = departmentRepository;
	}

	@Scheduled(cron = "0 42 12 * * *")
	public void markAbsentForMissingAttendance() {
		List<User> users = userRepository.findAll();
		LocalDate today = LocalDate.now();

		for (User user : users) {
			Optional<Attendance> attendance = attendanceRepository.findByUserAndDate(user, today);

			if (attendance.isEmpty() || attendance.get().getCheckInTime() == null
					|| attendance.get().getCheckOutTime() == null) {

				markAsAbsent(user, today);
			}
		}
	}

	private void markAsAbsent(User user, LocalDate date) {
		Attendance absentAttendance = new Attendance();
		absentAttendance.setUser(user);
		absentAttendance.setDate(date);
		absentAttendance.setStatus(EAttendanceStatus.ABSENT);
		absentAttendance.setCheckInTime(null);
		absentAttendance.setCheckOutTime(null);
		absentAttendance.setWorkHours(0);
		absentAttendance.setCreatedAt(LocalDateTime.now());
		absentAttendance.setUpdatedAt(LocalDateTime.now());

		attendanceRepository.save(absentAttendance);
	}

	@Override
	public AttendanceResponseDTO checkIn(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User with ID " + id + " not found."));

	
		Optional<Attendance> existingAttendance = attendanceRepository.findByUserAndStatusAndDate(user,
				EAttendanceStatus.PRESENT, LocalDate.now());

		if (existingAttendance.isPresent() && existingAttendance.get().getCheckInTime() != null) {
			Attendance existing = existingAttendance.get();
			return mapToDTO(existing);
		}

		Attendance attendance = existingAttendance.orElseGet(() -> {
			Attendance newAttendance = new Attendance();
			newAttendance.setUser(user);
			newAttendance.setDepartment(user.getDepartment());
			newAttendance.setDate(LocalDate.now());
			newAttendance.setCreatedAt(LocalDateTime.now());
			return newAttendance;
		});

		if (attendance.getCheckInTime() == null) {
			LocalTime now = LocalTime.now();
			attendance.setCheckInTime(now);
			attendance.setStatus(EAttendanceStatus.PRESENT);

			LocalTime shiftStartTime = LocalTime.of(9, 0);
			attendance.setLateCheckIn(now.isAfter(shiftStartTime));
		}

		attendance.setUpdatedAt(LocalDateTime.now());
		Attendance savedAttendance = attendanceRepository.save(attendance);

		return mapToDTO(savedAttendance);
	}

	@Override
	public AttendanceResponseDTO checkOut(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User with ID " + id + " not found."));


		Optional<Attendance> existingAttendance = attendanceRepository.findByUserAndStatusAndDate(user,
				EAttendanceStatus.PRESENT, LocalDate.now());

		if (existingAttendance.isEmpty() || existingAttendance.get().getCheckInTime() == null) {
			throw new ResourceNotFoundException("Check-in has not been recorded for today.");
		}

		Attendance attendance = existingAttendance.get();

		LocalTime now = LocalTime.now();
		attendance.setCheckOutTime(now);
		Duration duration = Duration.between(attendance.getCheckInTime(), attendance.getCheckOutTime());
		int workHours = (int) duration.toHours();
		attendance.setWorkHours(workHours);

		if (workHours >= 8) {
			attendance.setStatus(EAttendanceStatus.PRESENT);
			float overTime = workHours - 8;
			attendance.setOverTime(overTime);
			
		} else if (workHours < 8) {
			attendance.setStatus(EAttendanceStatus.HALF_DAY);
		} else {
			attendance.setStatus(EAttendanceStatus.ABSENT);
		}

		LocalTime shiftEndTime = LocalTime.of(17, 0);
		attendance.setEarlyCheckout(now.isBefore(shiftEndTime));

		attendance.setUpdatedAt(LocalDateTime.now());
		Attendance savedAttendance = attendanceRepository.save(attendance);

		return mapToDTO(savedAttendance);
	}

	@Override
	public List<AttendanceResponseDTO> getAttendanceUser(Long id) {

		List<Attendance> attendances = attendanceRepository.findUserByUserId(id);

		if (attendances.isEmpty()) {
			throw new ResourceNotFoundException("Attendance Record not found for User ID : " + id);
		}

		List<AttendanceResponseDTO> responseDTOs = new ArrayList<>();
		for (Attendance attendance : attendances) {
			responseDTOs.add(mapToDTO(attendance));
		}
		return responseDTOs;
	}

	@Override
	public List<AttendanceResponseDTO> getAllAttendanceUser(Long id) {

		List<Attendance> attendances = attendanceRepository.findUserByDepartmentId(id);

		if (attendances.isEmpty()) { 
			throw new ResourceNotFoundException("Attendance Record not found for Department ID : " + id);
		}

		List<AttendanceResponseDTO> responseDTOs = new ArrayList<>();
		for (Attendance attendance : attendances) {
			responseDTOs.add(mapToDTO(attendance));
		}
		return responseDTOs;
	}

	private AttendanceResponseDTO mapToDTO(Attendance attendance) {
		AttendanceResponseDTO responseDTO = new AttendanceResponseDTO();
		responseDTO.setAttendanceId(attendance.getAttendanceId());
		responseDTO.setCheckInTime(attendance.getCheckInTime());
		responseDTO.setCheckOutTime(attendance.getCheckOutTime());
		responseDTO.setWorkHours(attendance.getWorkHours());
		responseDTO.setStatus(attendance.getStatus());
		responseDTO.setLateCheckIn(attendance.getLateCheckIn());
		responseDTO.setEarlyCheckout(attendance.getEarlyCheckout());
		responseDTO.setCreatedAt(attendance.getCreatedAt());
		responseDTO.setUpdatedAt(attendance.getUpdatedAt());

		if (attendance.getUser() != null) {
			responseDTO.setUserId(attendance.getUser().getId());
		}
		else {
			responseDTO.setUserId(null);
		}
		
		if (attendance.getUser() != null) {
			responseDTO.setDepartmentId(attendance.getDepartment().getDepartmentId());
		} 
		else {
			responseDTO.setUserId(null);
		}
		return responseDTO;
	}

	@Override
	public List<AttendanceSummaryResponseDTO> getAttendanceSummary(Long id) {
	    // Get the current date and month
	    LocalDate currentDate = LocalDate.now();
	    YearMonth yearMonth = YearMonth.from(currentDate);

	    // Get the total number of days in the current month
	    int totalDays = yearMonth.lengthOfMonth();

	    // Fetch the user by ID
	    User user = userRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("User with ID " + id + " not found."));

	    // Get the number of attended days for the user in the current month
	    int attended = attendanceRepository.countCheckedInDays(user.getId(), currentDate.getMonthValue());

	    // Calculate the attendance percentage
	    float percentage = (attended * 100) / (float) totalDays;

	    // Create a response DTO
	    AttendanceSummaryResponseDTO summary = new AttendanceSummaryResponseDTO();
	    summary.setTotalDays(totalDays);
	    summary.setAttendedDays(attended);
	    summary.setAttendancePercentage(percentage);

	    List<AttendanceSummaryResponseDTO> responseDTOs = new ArrayList<>();
	    responseDTOs.add(summary);

	    return responseDTOs;
	}





	
	

}
