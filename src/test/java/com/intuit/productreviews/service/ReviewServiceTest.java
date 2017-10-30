package com.intuit.productreviews.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.intuit.productreviews.dao.DataAccessFacade;
import com.intuit.productreviews.model.Product;
import com.intuit.productreviews.model.Review;

@RunWith(SpringRunner.class)
public class ReviewServiceTest {
	
	@TestConfiguration
	static class ReviewServiceTestContextConfiguration {
		@Bean
		public ReviewService reviewService() {
			return new ReviewService();
		}
	}
	
	@Autowired
	ReviewService reviewService;

	@MockBean
	DataAccessFacade facade;
	
	@Test
	public void whenSave_thenReviewShouldReturnId() {
		Review review = getReviewMock();
		Mockito.when(facade.save(review)).thenReturn(1L);
		long productId = reviewService.saveReview(review);
		assertTrue(productId == 1);
	}
	
	private Review getReviewMock(){
		Product iPhoneX = new Product("iPhone x");
		iPhoneX.setId(1L);
		Review iPhoneXReview = new Review(4.0, "Good but edges are sharp", iPhoneX, "rakisa");
		return iPhoneXReview;
	}
}
