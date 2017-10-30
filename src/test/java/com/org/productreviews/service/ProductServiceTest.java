package com.org.productreviews.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.org.productreviews.dao.DataAccessFacade;
import com.org.productreviews.model.Product;
import com.org.productreviews.model.Review;
import com.org.productreviews.service.ProductService;

@RunWith(SpringRunner.class)
public class ProductServiceTest {

	@TestConfiguration
	static class ProductServiceTestContextConfiguration {
		@Bean
		public ProductService productService() {
			return new ProductService();
		}
	}

	@Autowired
	ProductService productService;

	@MockBean
	DataAccessFacade facade;

	@Before
	public void setUp() {
		Product iPhoneX = new Product("iPhone x");
		iPhoneX.setId(1L);
		Mockito.when(facade.getProductByName(iPhoneX.getName())).thenReturn(iPhoneX);
		Mockito.when(facade.getAllProducts()).thenReturn(getProducts());
	}

	@Test
	public void whenValidName_thenProductShouldBeFound() {
		String name = "iPhone x";
		Product response = productService.getProductByName(name);
		assertNotNull(response);
		assertNotNull(response.getName());
		assertTrue(response.getId() > 0);
	}

	@Test
	public void whenValidId_thenProductShouldBeFound() {
		Mockito.when(facade.getProductById(1)).thenReturn(getProductWithReviewsMock());
		Product response = productService.getProductById(1);
		assertNotNull(response);
		assertNotNull(response.getName());
		assertTrue(response.getName().equals("iPhone x"));
		assertTrue(response.getReviews().size() == 3);
		assertTrue(response.getAggregatedScore() == 3.0);
	}

	@Test
	public void whenSave_thenProductShouldReturnId() {
		Product iPhoneX = new Product("iPhone x");
		Mockito.when(facade.save(iPhoneX)).thenReturn(1L);
		long productId = productService.saveProduct(iPhoneX);
		assertTrue(productId == 1);
	}

	@Test
	public void whenGetAllProducts() {
		List<Product> lstProducts = productService.getAllProducts();
		assertTrue(lstProducts.size() == 4);
	}

	private List<Product> getProducts() {
		Product iPhoneX = new Product("iPhone x");
		iPhoneX.setId(1L);
		Product iPhone8 = new Product("iPhone 8");
		iPhoneX.setId(2L);
		Product iPhone7 = new Product("iPhone 7");
		iPhoneX.setId(3L);
		Product iPhone6 = new Product("iPhone 6S");
		iPhoneX.setId(4L);
		List<Product> lstProducts = new ArrayList<Product>();
		lstProducts.add(iPhoneX);
		lstProducts.add(iPhone8);
		lstProducts.add(iPhone7);
		lstProducts.add(iPhone6);
		return lstProducts;
	}
	
	private Product getProductWithReviewsMock(){
		Product iPhoneX = new Product("iPhone x");
		iPhoneX.setId(1L);
		Set<Review> setReviews=new HashSet<>();
		setReviews.add(getReviewMock(4.0,"Good but edges are sharp","rakisa"));
		setReviews.add(getReviewMock(3.0,"Just","rakisa12"));
		setReviews.add(getReviewMock(2.0,"Not comfortable","rakisa123"));
		iPhoneX.setReviews(setReviews);
		iPhoneX.setAggregatedScore(3.0);
		return iPhoneX;
	}
	
	private Review getReviewMock(double score, String comment, String userId){
		Product iPhoneX = new Product("iPhone x");
		iPhoneX.setId(1L);
		Review iPhoneXReview = new Review(score, comment, iPhoneX, userId);
		return iPhoneXReview;
	}
}
