package com.org.productreviews.dto;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
* <p>
* Developed for demo purpose by Sagar Mummidivarapu
* <p>
* Classification level: normal
* <p>
* Written on 10/29/2017. This class is the data transfer object that accepts input from the client.
*/

public class ProductRequestDto {
	@NotNull
	@NotEmpty
	@ApiModelProperty(notes = "name of the product", required = true)
	@JsonProperty("name")
	private String name;
	
	//getters
	public String getName(){
		return this.name;
	}
	
	//setters
	public void setName(String name){
		this.name = name;
	}
}
