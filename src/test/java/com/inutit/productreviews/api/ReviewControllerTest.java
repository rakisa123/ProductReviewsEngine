package com.inutit.productreviews.api;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.intuit.productreviews.api.ReviewController;
import com.intuit.productreviews.dto.ReviewRequestDto;
import com.intuit.productreviews.model.Product;
import com.intuit.productreviews.model.Review;
import com.intuit.productreviews.service.ReviewService;
import com.intuit.productreviews.utils.CommonUtils;

@RunWith(MockitoJUnitRunner.class)
public class ReviewControllerTest {
	@Mock
	ReviewService reviewService;

	@Mock
	CommonUtils utils;
	
	@InjectMocks	
	ReviewController reviewController;
	
	@Test
	public void saveReviewTest(){
		ReviewRequestDto reviewDto = getReviewDtoStub();
		Review review = getReviewStub();
		when(reviewService.saveReview(review)).thenReturn(1L);
		ResponseEntity<Long> productResponse = reviewController.reviews(reviewDto);
		assertNotNull(productResponse);
	}
	
	private Review getReviewStub(){
		Product iPhoneX = new Product("iPhone X");
		iPhoneX.setId(1L);
		Review review = new Review(3.0,"Looks good",iPhoneX,"rakisa");
		return review;
	}
	
	private ReviewRequestDto getReviewDtoStub(){
		ReviewRequestDto reviewDto = new ReviewRequestDto();
		reviewDto.setScore(3.0);
		reviewDto.setComment("Looks good");
		reviewDto.setUserId("rakisa");
		reviewDto.setProductId(1L);
		return reviewDto;
	}
}
