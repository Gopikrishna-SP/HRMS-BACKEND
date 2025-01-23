package com.kryptos.hrms.exception;

import com.kryptos.hrms.model.enums.EError;
import com.kryptos.hrms.response.ErrorResponses;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponses> handleResourceNotFound(ResourceNotFoundException ex,
			HttpServletRequest request) {
		logger.error("Resource not found: {}", ex.getMessage());
		int status = HttpStatus.NOT_FOUND.value();
		EError eError = EError.RESOURCE_NOT_FOUND;
		ErrorResponses errorResponse = new ErrorResponses(status, eError,
				ex.getMessage(), LocalDateTime.now(), request.getRequestURI());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(DuplicateFoundException.class)
	public ResponseEntity<ErrorResponses> handleDuplicateFound(DuplicateFoundException ex, HttpServletRequest request) {
		logger.error("Duplicate found: " + ex.getMessage());
		int status = HttpStatus.CONFLICT.value();
		EError eError = EError.DUPLICATE_FOUND;
		ErrorResponses errorResponse = new ErrorResponses(status , eError ,
				ex.getMessage(), LocalDateTime.now(), request.getRequestURI());
		return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
	}
	
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponses> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        logger.error("Validation failed: " + ex.getMessage());

        int status = HttpStatus.BAD_REQUEST.value();
        EError eError = EError.VALIDATION_FAILED;
        
        List<String> errors = ex.getBindingResult()
                                 .getFieldErrors()
                                 .stream()
                                 .map(error -> error.getField() + ": " + error.getDefaultMessage())
                                 .collect(Collectors.toList());

        ErrorResponses errorResponse = new ErrorResponses(
            status,
            eError,
            String.join(", ", errors),
            LocalDateTime.now(),            
            request.getRequestURI()          
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponses> handleGenericException(Exception ex, HttpServletRequest request) {
        logger.error("An unexpected error occurred: {}", ex.getMessage());
        int status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        EError eError = EError.INTERNAL_SERVER_ERROR;
        ErrorResponses errorResponse = new ErrorResponses(status, eError,
                ex.getMessage(), LocalDateTime.now(), request.getRequestURI());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	

}
