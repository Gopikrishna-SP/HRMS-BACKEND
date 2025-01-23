package com.kryptos.hrms.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kryptos.hrms.exception.ResourceNotFoundException;
import com.kryptos.hrms.model.Department;
import com.kryptos.hrms.model.PersonalDetails;
import com.kryptos.hrms.model.Role;
import com.kryptos.hrms.model.User;
import com.kryptos.hrms.model.enums.ERole;
import com.kryptos.hrms.payload.request.LoginRequest;
import com.kryptos.hrms.payload.request.SignupRequest;
import com.kryptos.hrms.payload.response.JwtResponse;
import com.kryptos.hrms.payload.response.MessageResponse;
import com.kryptos.hrms.payload.response.UserResponse;
import com.kryptos.hrms.repository.DepartmentRepository;
import com.kryptos.hrms.repository.RoleRepository;
import com.kryptos.hrms.repository.UserRepository;
import com.kryptos.hrms.request.AnnouncementRequestDTO;
import com.kryptos.hrms.response.AnnouncementResponseDTO;
import com.kryptos.hrms.response.ResponseMessage;
import com.kryptos.hrms.security.jwt.JwtUtils;
import com.kryptos.hrms.security.services.UserDetailsImpl;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	DepartmentRepository departmentRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), roles));
	}

	@PostMapping("/signout")
	public ResponseEntity<?> signOut(HttpServletRequest request, HttpServletResponse response) {
		// Get the JWT token from the request header (Bearer token)
		String token = jwtUtils.getJwtFromRequest(request);

		// If token is found and valid, add it to the blacklist (optional for token
		// invalidation)
		if (token != null && jwtUtils.validateJwtToken(token)) {
			// Initialize the blacklist (use an in-memory Set for simplicity here)
			Set<String> blacklistedTokens = new HashSet<>();

			// Add token to the blacklist (avoid reusing it)
			blacklistedTokens.add(token);
			System.out.println("Blacklisted Token: " + token); // Debug log
		}

		// Remove JWT token from cookies (if you're storing it in cookies)
		Cookie cookie = new Cookie("jwtToken", null);
		cookie.setHttpOnly(true);
		cookie.setSecure(true);
		cookie.setMaxAge(0); // Expire the cookie
		cookie.setPath("/"); // Make sure the path matches your app's domain
		response.addCookie(cookie);

		// Optionally, respond with success
		return ResponseEntity.ok("Successfully logged out.");
	}


	@PostMapping("/add")
	@PreAuthorize("hasRole('ADMIN') or hasRole('HUMANRESOURCE')")
	public ResponseEntity<ResponseMessage<UserResponse>> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest() .body(new ResponseMessage<>(false, 0, "Error: Username is already taken", null));
	    }

		User user = new User(signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_EMPLOYEE)
					.orElseThrow(() -> new RuntimeException("Error : Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin": {
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error : Role is not found."));
					roles.add(adminRole);
					break;
				}
				case "humanresource": {
					Role humanresourceRole = roleRepository.findByName(ERole.ROLE_HUMANRESOURCE)
							.orElseThrow(() -> new RuntimeException("Error : Role is not found."));
					roles.add(humanresourceRole);
					break;
				}
				case "manager": {
					Role managerRole = roleRepository.findByName(ERole.ROLE_MANAGER)
							.orElseThrow(() -> new RuntimeException("Error : Role is not found."));
					roles.add(managerRole);
					break;
				}
				default:
					Role employeeRole = roleRepository.findByName(ERole.ROLE_EMPLOYEE)
							.orElseThrow(() -> new RuntimeException("Error : Role is not found."));
					roles.add(employeeRole);
					break;
				}

			});
		}
		user.setRoles(roles);
		
		Long deptId = signUpRequest.getDepartmentId();
		Department department = departmentRepository.findById(deptId)
				.orElseThrow(() -> new RuntimeException("User not found"));
		user.setDepartment(department);

		if (signUpRequest.getPersonalDetails() != null && !signUpRequest.getPersonalDetails().isEmpty()) {
			user.setPersonalDetails(signUpRequest.getPersonalDetails());
		}

		// Set professional experience
		if (signUpRequest.getProfessionalExperience() != null) {
			user.setProfessionalExperience(signUpRequest.getProfessionalExperience());
		}

		// Set education details
		if (signUpRequest.getEducationDetails() != null) {
			user.setEducationDetails(signUpRequest.getEducationDetails());
		}

		// Set bank account details
		if (signUpRequest.getBankAccountDetails() != null) {
			user.setBankAccountDetails(signUpRequest.getBankAccountDetails());
		}
		
		userRepository.save(user);
		UserResponse userResponse = new UserResponse(
			    user.getId(),
			    user.getUsername(),
			    user.getPassword(),
			    user.getRoles(),
			    user.getDepartment().getDepartmentId(),
			    user.getPersonalDetails(),
			    user.getProfessionalExperience(),
			    user.getEducationDetails(),
			    user.getBankAccountDetails(),
			    user.getCreatedAt(),
			    user.getUpdatedAt()
			);


		    // Create response message
		    ResponseMessage<UserResponse> response = new ResponseMessage<>(
		            true,
		            1,
		            "User created successfully",
		            userResponse
		    );		
		    return ResponseEntity.status(HttpStatus.CREATED).body(response);

	}

	@GetMapping("/all")
	@PreAuthorize("hasRole('ADMIN') or hasRole('HUMANRESOURCE') or hasRole('MANAGER')")
	public ResponseEntity<ResponseMessage<List<User>>> getAllUsers() {
		List<User> users = userRepository.findAll();
		ResponseMessage<List<User>> response = new ResponseMessage<List<User>>(true, 1, "User fetched successfully",
				users);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	// Get user by ID
	@GetMapping("/{userId}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('HUMANRESOURCE') or hasRole('MANAGER') or hasRole('EMPLOYEE')")
	public ResponseEntity<ResponseMessage<UserResponse>> getUserById(@PathVariable Long userId) {
	    User user = userRepository.findById(userId)
	            .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
	    
	    // Map User entity to UserResponse DTO
	    UserResponse userResponse = new UserResponse(
	            user.getId(),
	            user.getUsername(),
	            user.getPassword(),
	            user.getRoles(),
	            user.getDepartment().getDepartmentId(),
	            user.getPersonalDetails(),
	            user.getProfessionalExperience(),
	            user.getEducationDetails(),
	            user.getBankAccountDetails(),
	            user.getCreatedAt(),
	            user.getUpdatedAt()
	    );

	    // Wrap the UserResponse DTO in the ResponseMessage
	    ResponseMessage<UserResponse> response = new ResponseMessage<>(true, 1, "User fetched successfully", userResponse);
	    return ResponseEntity.status(HttpStatus.OK).body(response);
	}


	// Update user by ID
	@PutMapping("/update/{userId}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('HUMANRESOURCE')")
	public ResponseEntity<?> updateUser(@PathVariable Long userId, @Valid @RequestBody SignupRequest signUpRequest) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

		if (signUpRequest.getUsername() != null) {
			user.setUsername(signUpRequest.getUsername());
		}

		// Update password if provided
		if (signUpRequest.getPassword() != null) {
			user.setPassword(encoder.encode(signUpRequest.getPassword()));
		}

		// Update roles
		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles != null) {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin": {
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error : Role is not found."));
					roles.add(adminRole);
					break;
				}
				case "humanresource": {
					Role humanresourceRole = roleRepository.findByName(ERole.ROLE_HUMANRESOURCE)
							.orElseThrow(() -> new RuntimeException("Error : Role is not found."));
					roles.add(humanresourceRole);
					break;
				}
				case "manager": {
					Role managerRole = roleRepository.findByName(ERole.ROLE_MANAGER)
							.orElseThrow(() -> new RuntimeException("Error : Role is not found."));
					roles.add(managerRole);
					break;
				}
				default:
					Role employeeRole = roleRepository.findByName(ERole.ROLE_EMPLOYEE)
							.orElseThrow(() -> new RuntimeException("Error : Role is not found."));
					roles.add(employeeRole);
					break;
				}
			});
		}
		user.setRoles(roles);

		Long deptId = signUpRequest.getDepartmentId();
		Department department = departmentRepository.findById(deptId)
				.orElseThrow(() -> new RuntimeException("User not found"));
		user.setDepartment(department);
		// Update personal details, professional experience, education, and bank account
		// details
		if (signUpRequest.getPersonalDetails() != null && !signUpRequest.getPersonalDetails().isEmpty()) {
			user.setPersonalDetails(signUpRequest.getPersonalDetails());
		}

		if (signUpRequest.getProfessionalExperience() != null) {
			user.setProfessionalExperience(signUpRequest.getProfessionalExperience());
		}

		if (signUpRequest.getEducationDetails() != null) {
			user.setEducationDetails(signUpRequest.getEducationDetails());
		}

		if (signUpRequest.getBankAccountDetails() != null) {
			user.setBankAccountDetails(signUpRequest.getBankAccountDetails());
		}
		
		

		userRepository.save(user);

		ResponseMessage<User> response = new ResponseMessage<User>(true, 1, "User Updated successfully", user);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	// Soft delete user (set 'active' to false)
	@DeleteMapping("/delete/{userId}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('HUMANRESOURCE')")
	public ResponseEntity<?> softDeleteUser(@PathVariable Long userId) {
		// Get user by ID
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

		// Check if personalDetails exists before setting 'active' to false
		if (user.getPersonalDetails() != null && !user.getPersonalDetails().isEmpty()) {
			PersonalDetails personalDetails = user.getPersonalDetails().get(0); // Access the first PersonalDetails
																				// object
			personalDetails.setActive(false); // Set active to false for soft delete
			userRepository.save(user); // Save the updated user object
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new MessageResponse("No personal details found for this user"));
		}

		ResponseMessage<User> response = new ResponseMessage<User>(true, 1, "User Deleted successfully", user);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
