package com.intuit.productreviews.utils;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.intuit.productreviews.exception.ErrorResponse;

/**
* <p>
* Developed for demo purpose by Sagar Mummidivarapu
* <p>
* Classification level: normal
* <p>
* Written on 10/29/2017. This class can have all utility functions related to exceptions.
*/

public class ExceptionUtils {

	public static ResponseEntity<ErrorResponse> getErrorResponse(int errorCode, String errorMessage, HttpStatus httpStatus, List<String> lstErrors) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(errorCode);
		error.setErrorMessage(errorMessage);
		error.setErrors(lstErrors);
		return new ResponseEntity<>(error, httpStatus);
	}
}
