package com.inutit.productreviews.api;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.intuit.productreviews.api.ProductController;
import com.intuit.productreviews.dto.ProductRequestDto;
import com.intuit.productreviews.model.Product;
import com.intuit.productreviews.service.ProductService;
import com.intuit.productreviews.utils.CommonUtils;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {
	@Mock
	ProductService productService;

	@Mock
	CommonUtils utils;
	
	@InjectMocks	
	ProductController productController;
	
	@Test
	public void getAllproductsTest(){
		when(productService.getAllProducts()).thenReturn(getAllProductsResponseStub());
		ResponseEntity<List<Product>> productResponse = productController.products();
		assertNotNull(productResponse);
		assertTrue(productResponse.getStatusCode() == HttpStatus.OK);
	}
	
	@Test
	public void getProductByIdTest(){
		
		when(productService.getProductById(1L)).thenReturn(getProductStub());
		ResponseEntity<Product> productResponse = productController.products(1L);
		assertNotNull(productResponse);
		assertTrue(productResponse.getStatusCode() == HttpStatus.OK);
	}
	
	@Test
	public void saveProductTest(){
		ProductRequestDto productDto = getProductDtoStub();
		Product product = getProductStub();
		when(productService.saveProduct(product)).thenReturn(1L);
		ResponseEntity<Long> productResponse = productController.products(productDto);
		assertNotNull(productResponse);
	}
	
	private Product getProductStub(){
		Product iPhoneX = new Product("iPhone X");
		iPhoneX.setId(1L);
		return iPhoneX;
	}
	
	private ProductRequestDto getProductDtoStub(){
		ProductRequestDto iPhoneX = new ProductRequestDto();
		iPhoneX.setName("iPhone X");
		return iPhoneX;
	}
	
	private List<Product> getAllProductsResponseStub() {
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
}
