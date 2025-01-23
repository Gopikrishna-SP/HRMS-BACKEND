package com.kryptos.hrms.serviceimpl;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kryptos.hrms.repository.DepartmentRepository;
import com.kryptos.hrms.repository.UserRepository;
import com.kryptos.hrms.response.EmployeeStatisticsResponseDTO;
import com.kryptos.hrms.service.EmployeeStatisticsService;

@Service
public class EmployeeStatisticsServiceImpl implements EmployeeStatisticsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public EmployeeStatisticsResponseDTO getEmployeeStatistics() {
		LocalDate currentDate = LocalDate.now();
		Month currentMonth = currentDate.getMonth();
		int currentYear = currentDate.getYear();

		// Get total employees count
		int totalEmployees = userRepository.countAll();

		// Get departments count
		int departments = departmentRepository.countAll();

		// Get new hires count for the current month
		int newHires = userRepository.countByDateOfJoiningMonth(currentMonth.getValue(), currentYear);

		// Count employees who have exited
		int exitedEmployees = userRepository.countByDateOfExitNotNull();
		// Calculate staff turnover percentage
		double staffTurnover = totalEmployees > 0 ? (double) exitedEmployees / totalEmployees * 100 : 0;

		// Map to DTO
		return mapToDTO(totalEmployees, departments, newHires, staffTurnover);
	}

	private EmployeeStatisticsResponseDTO mapToDTO(int totalEmployees, int departments, int newHires,
			double staffTurnover) {
		EmployeeStatisticsResponseDTO responseDTO = new EmployeeStatisticsResponseDTO();
		responseDTO.setTotalEmployees(totalEmployees);
		responseDTO.setDepartments(departments);
		responseDTO.setNewHires(newHires);
		responseDTO.setStaffTurnover(staffTurnover);
		return responseDTO;
	}

}
