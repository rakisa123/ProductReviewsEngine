package com.intuit.productreviews;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.intuit.productreviews.dto.ReviewRequestDto;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = ProductReviewsApplication.class)
public class ReviewControllerIT {
	@Autowired
	TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	HttpHeaders headers = new HttpHeaders();

	@Test
	public void createReview() {
		ResponseEntity<Long> responseEntity = restTemplate.postForEntity("/reviews", createReviewRequest(), Long.class);
		Long reviewId = responseEntity.getBody();
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		assertTrue(reviewId == 9);
	}

	private ReviewRequestDto createReviewRequest(){
		ReviewRequestDto reviewDto = new ReviewRequestDto();
		reviewDto.setScore(3.0);
		reviewDto.setComment("Looks good");
		reviewDto.setUserId("rakisa");
		reviewDto.setProductId(1L);
		return reviewDto;
	}
}
