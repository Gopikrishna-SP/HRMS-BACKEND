package com.kryptos.hrms.serviceimpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kryptos.hrms.exception.ResourceNotFoundException;
import com.kryptos.hrms.model.User;
import com.kryptos.hrms.model.enums.ERole;
import com.kryptos.hrms.model.Department;
import com.kryptos.hrms.model.Payroll;
import com.kryptos.hrms.model.MasterPayroll;
import com.kryptos.hrms.model.Role;
import com.kryptos.hrms.repository.UserRepository;
import com.kryptos.hrms.repository.DepartmentRepository;
import com.kryptos.hrms.repository.PayrollDefaultRepository;
import com.kryptos.hrms.repository.PayrollRepository;
import com.kryptos.hrms.request.PayrollCreateRequestDTO;
import com.kryptos.hrms.request.PayrollUpdateRequestDTO;
import com.kryptos.hrms.response.PayrollResponseDTO;
import com.kryptos.hrms.service.PayrollService;

@Service
public class PayrollServiceImpl implements PayrollService {

	private final PayrollRepository payrollRepository;

	private final UserRepository userRepository;

	private final DepartmentRepository departmentRepository;

	private final PayrollDefaultRepository payrollDefaultRepository;

	public PayrollServiceImpl(PayrollRepository payrollRepository, UserRepository userRepository,
			DepartmentRepository departmentRepository, PayrollDefaultRepository payrollDefaultRepository) {
		super();
		this.payrollRepository = payrollRepository;
		this.userRepository = userRepository;
		this.departmentRepository = departmentRepository;
		this.payrollDefaultRepository = payrollDefaultRepository;
	}

	@Override
	public PayrollResponseDTO addPayroll(PayrollCreateRequestDTO dto) {
		User user = userRepository.findById(dto.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("User with ID " + dto.getUserId() + " not found."));

		Department department = departmentRepository.findById(dto.getDepartmentId()).orElseThrow(
				() -> new ResourceNotFoundException("Department ID " + dto.getDepartmentId() + " not found."));

		if (user.getRoles() == null || user.getRoles().isEmpty()) {
			throw new RuntimeException("User has no roles assigned");
		}

		Role primaryRole = user.getRoles().iterator().next();
		ERole userRole = primaryRole.getName();

		MasterPayroll payrollDefault = payrollDefaultRepository.findByRole(userRole)
				.orElseThrow(() -> new RuntimeException("Payroll configuration not found for role: " + userRole));

		Payroll payroll = new Payroll();
		payroll.setUser(user);
		payroll.setDepartment(department);
		LocalDate payrollDate = LocalDate.now();
		payroll.setDate(payrollDate);
		payroll.setPayrollMonth(payrollDate.getMonth());

		payroll.setBasicSalary(payrollDefault.getBasicSalary());
		payroll.setHra(payrollDefault.getHra());
		payroll.setMedicalAllowance(payrollDefault.getMedicalAllowance());
		payroll.setAnnualBonus(payrollDefault.getAnnualBonus());
		payroll.setIncomeTax(payrollDefault.getIncomeTax());
		payroll.setPf(payrollDefault.getPf());
		payroll.setOvertime(BigDecimal.ZERO);
		payroll.setPaymentStatus(dto.getPaymentStatus());
		BigDecimal netSalary = payroll.getBasicSalary().add(payroll.getHra()).add(payroll.getMedicalAllowance())
				.add(payroll.getAnnualBonus()).subtract(payroll.getIncomeTax()).subtract(payroll.getPf());
		payroll.setNetSalary(netSalary);

		payrollRepository.save(payroll);
		return mapToDTO(payroll);
	}

	@Override
	public PayrollResponseDTO updatePayroll(Long id, PayrollUpdateRequestDTO dto) {
		User user = userRepository.findById(dto.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("User with ID " + dto.getUserId() + " not found."));

		Department department = departmentRepository.findById(dto.getDepartmentId()).orElseThrow(
				() -> new ResourceNotFoundException("Department ID " + dto.getDepartmentId() + " not found."));

		if (user.getRoles() == null || user.getRoles().isEmpty()) {
			throw new RuntimeException("User has no roles assigned");
		}

		Role primaryRole = user.getRoles().iterator().next();
		ERole userRole = primaryRole.getName();

		MasterPayroll payrollDefault = payrollDefaultRepository.findByRole(userRole)
				.orElseThrow(() -> new RuntimeException("Payroll configuration not found for role: " + userRole));

		Payroll payroll = new Payroll();
		payroll.setUser(user);
		payroll.setDepartment(department);
		LocalDate payrollDate = LocalDate.now();
		payroll.setDate(payrollDate);
		payroll.setPayrollMonth(payrollDate.getMonth());

		payroll.setBasicSalary(dto.getBasicSalary());
		payroll.setHra(dto.getHra());
		payroll.setMedicalAllowance(dto.getMedicalAllowance());
		payroll.setAnnualBonus(dto.getAnnualBonus());
		payroll.setIncomeTax(dto.getIncomeTax());
		payroll.setPf(dto.getPf());
		payroll.setOvertime(BigDecimal.ZERO);
		payroll.setPaymentStatus(dto.getPaymentStatus());

		BigDecimal netSalary = payroll.getBasicSalary().add(payroll.getHra()).add(payroll.getMedicalAllowance())
				.add(payroll.getAnnualBonus()).subtract(payroll.getIncomeTax()).subtract(payroll.getPf());
		payroll.setNetSalary(netSalary);

		payrollRepository.save(payroll);
		return mapToDTO(payroll);
	}

	@Override
	public List<PayrollResponseDTO> getUserPayroll(Long id) {
		List<Payroll> payrolls = payrollRepository.findByUserUserId(id);

		if (payrolls.isEmpty()) {
			throw new ResourceNotFoundException("No payroll records found for User ID " + id);
		}

		List<PayrollResponseDTO> responseDTOs = new ArrayList<>();
		for (Payroll payroll : payrolls) {
			responseDTOs.add(mapToDTO(payroll));
		}
		return responseDTOs;
	}

	@Override
	public List<PayrollResponseDTO> getAllUserPayroll(Long id) {
		List<Payroll> payrolls = payrollRepository.findByDepartmentDepartmentId(id);
		if (payrolls.isEmpty()) {
			throw new ResourceNotFoundException("No payroll records found for Department ID " + id);
		}
		List<PayrollResponseDTO> responseDTOs = new ArrayList<>();
		for (Payroll payroll : payrolls) {
			responseDTOs.add(mapToDTO(payroll));
		}
		return responseDTOs;
	}

	@Override
	public List<PayrollResponseDTO> getAllPayroll() {
		List<Payroll> payrolls = payrollRepository.findAll();
		if (payrolls.isEmpty()) {
			throw new ResourceNotFoundException("No payroll records found.");
		}
		List<PayrollResponseDTO> responseDTOs = new ArrayList<>();
		for (Payroll payroll : payrolls) {
			responseDTOs.add(mapToDTO(payroll));
		}
		return responseDTOs;
	}

	private PayrollResponseDTO mapToDTO(Payroll payroll) {
		PayrollResponseDTO responseDTO = new PayrollResponseDTO();

		responseDTO.setPayrollId(payroll.getPayrollId());
		responseDTO.setPayrollMonth(payroll.getPayrollMonth());
		responseDTO.setNetSalary(payroll.getNetSalary());
		responseDTO.setHra(payroll.getHra());
		responseDTO.setDate(payroll.getDate());
		responseDTO.setPaymentStatus(payroll.getPaymentStatus());
		responseDTO.setCreatedAt(payroll.getCreatedAt());
		responseDTO.setUpdatedAt(payroll.getUpdatedAt());

		responseDTO.setBasicSalary(payroll.getBasicSalary());

		responseDTO.setMedicalAllowance(payroll.getMedicalAllowance());

		responseDTO.setAnnualBonus(payroll.getAnnualBonus());

		responseDTO.setIncomeTax(payroll.getIncomeTax());

		responseDTO.setPf(payroll.getPf());

		responseDTO.setOvertime(payroll.getOvertime());

		if (payroll.getUser() != null) {
			responseDTO.setUserId(payroll.getUser().getId());
		}

		if (payroll.getDepartment() != null) {
			responseDTO.setDepartmentId(payroll.getDepartment().getDepartmentId());
		}

		return responseDTO;
	}



}
