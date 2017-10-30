package com.org.productreviews.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.org.productreviews.dto.ProductRequestDto;
import com.org.productreviews.dto.ReviewRequestDto;
import com.org.productreviews.model.Product;
import com.org.productreviews.model.Review;
import com.org.productreviews.service.ProductService;

@RunWith(SpringRunner.class)
public class CommonUtilsTest {
	
	@TestConfiguration
	static class CommonUtilsTestContextConfiguration {
		@Bean
		public CommonUtils commonUtils() {
			return new CommonUtils();
		}
	}
	
	@Autowired
	CommonUtils commonUtils;
	
	@MockBean
	ProductService productService;
	
	@Test
	public void validateProductRequestTest(){
		boolean result= commonUtils.validateProductRequest(getProductDtoStub());
		assertTrue(result);
		//Negative test case
		try{
			commonUtils.validateProductRequest(null);
		}
		catch(Exception ex){
			result = false;
		}
		assertFalse(result);
	}
	
	@Test
	public void validateReviewRequestTest(){
		boolean result= commonUtils.validateReviewRequest(getReviewDtoStub());
		assertTrue(result);
		//Negative test case
		try{
			commonUtils.validateReviewRequest(null);
		}
		catch(Exception ex){
			result = false;
		}
		assertFalse(result);
	}
	
	@Test
	public void getProductModelTest(){
		Product result= commonUtils.getProductModel(getProductDtoStub());
		assertNotNull(result);
		assertTrue(result.getName().equals("iPhone X"));
	}
	
	@Test
	public void getReviewModelTest(){
		ReviewRequestDto reviewDto = getReviewDtoStub();
		Review result= commonUtils.getReviewModel(reviewDto);
		assertNotNull(result);
		assertTrue(result.getScore()==reviewDto.getScore());
		assertTrue(result.getDescription().equals(reviewDto.getComment()));
		assertTrue(result.getUserId().equals(reviewDto.getUserId()));
	}
	
	private ProductRequestDto getProductDtoStub(){
		ProductRequestDto iPhoneX = new ProductRequestDto();
		iPhoneX.setName("iPhone X");
		return iPhoneX;
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