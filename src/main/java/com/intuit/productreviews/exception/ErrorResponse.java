package com.intuit.productreviews.exception;

import java.util.List;

/**
* <p>
* Developed for demo purpose by Sagar Mummidivarapu
* <p>
* Classification level: normal
* <p>
* Written on 10/23/2017. This class is used in the exception response.
*/

public class ErrorResponse {
	private int errorCode;
	private String errorMessage;
	private List<String> errors;
	
	//constructor
	public ErrorResponse(){
		
	}
	
	//setters
	public void setErrorCode(int errorCode){
		this.errorCode = errorCode;
	}
	public void setErrorMessage(String errorMessage){
		this.errorMessage = errorMessage;
	}
	
	//getters
	public int getErrorCode(){
		return this.errorCode;
	}
	public String getErrorMessage(){
		return this.errorMessage;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
}
