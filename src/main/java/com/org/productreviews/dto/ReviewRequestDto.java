package com.org.productreviews.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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

public class ReviewRequestDto {
	@NotNull
	@NotEmpty
	@ApiModelProperty(notes = "review comments for a given product", required = true)
	@JsonProperty("comment")
	private String comment;

	@Min(value = 1)
	@Max(value = 5)
	@ApiModelProperty(notes = "Score or ratings provided by the reviewer", required = true)
	private double score;

	@ApiModelProperty(notes = "Product Identifier", required = true)
	@Min(value = 1)
	private Long productId;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	@NotNull
	@NotEmpty
	@Pattern(regexp = "^[a-zA-Z0-9]+$")
	@ApiModelProperty(notes = "alpha numeric characters identifying the given user by its ID", required = true)
	private String userId;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
