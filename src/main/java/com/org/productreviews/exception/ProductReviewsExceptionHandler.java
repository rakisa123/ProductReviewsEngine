package com.org.productreviews.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.org.productreviews.utils.ExceptionUtils;

/**
 * <p>
 * Developed for demo purpose by Sagar Mummidivarapu
 * <p>
 * Classification level: normal
 * <p>
 * Written on 10/29/2017. This class handles all exceptions throughout the
 * application rather than having try and catch blocks everywhere.
 */

@ControllerAdvice
public class ProductReviewsExceptionHandler {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private static final String APIERROR_MSG = "Please contact your administrator with ";
	private static final String SERVICE_FAILURE_MSG = "Product_Reviews service failed with {} and the UUID is {}";
	private static final String ERROR_TOKEN = "ErrorToken:";
	private static final String LINE_SEPARATOR = "line.separator";
	private static final String INPUT_VALIDATION_ERROR = "Validation failed for input ";
	private static final String RESOURCE_NOT_FOUND = "Requested resource not found ";

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
		UUID uuid = java.util.UUID.randomUUID();
		logger.error(SERVICE_FAILURE_MSG, ex.getMessage(), uuid, ex);
		final List<String> errors = new ArrayList<String>();
		errors.add(ex.getLocalizedMessage());
		return ExceptionUtils.getErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), APIERROR_MSG + uuid,
				HttpStatus.INTERNAL_SERVER_ERROR, errors);
	}

	@ExceptionHandler(value = ValidationException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(ValidationException ex) {
		UUID uuid = java.util.UUID.randomUUID();
		logger.error(SERVICE_FAILURE_MSG, ex.getMessage(), uuid, ex);
		final List<String> errors = new ArrayList<String>();
		errors.add(ex.getLocalizedMessage());
		return ExceptionUtils.getErrorResponse(HttpStatus.BAD_REQUEST.value(),
				ERROR_TOKEN + uuid + System.getProperty(LINE_SEPARATOR) +INPUT_VALIDATION_ERROR, HttpStatus.BAD_REQUEST,
				errors);
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(MethodArgumentNotValidException ex) {
		UUID uuid = java.util.UUID.randomUUID();
		logger.error(SERVICE_FAILURE_MSG, ex.getMessage(), uuid, ex);
		final List<String> errors = new ArrayList<String>();
		for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}
		for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}
		return ExceptionUtils.getErrorResponse(HttpStatus.BAD_REQUEST.value(),
				ERROR_TOKEN + uuid + System.getProperty(LINE_SEPARATOR) + INPUT_VALIDATION_ERROR,
				HttpStatus.BAD_REQUEST, errors);
	}

	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(ResourceNotFoundException ex) {
		UUID uuid = java.util.UUID.randomUUID();
		logger.error(SERVICE_FAILURE_MSG, RESOURCE_NOT_FOUND, uuid, ex);
		final List<String> errors = new ArrayList<String>();
		errors.add(ex.getPropertyName());
		return ExceptionUtils.getErrorResponse(HttpStatus.NOT_FOUND.value(),
				ERROR_TOKEN + uuid + System.getProperty(LINE_SEPARATOR) + RESOURCE_NOT_FOUND, HttpStatus.NOT_FOUND, errors);
	}
}
