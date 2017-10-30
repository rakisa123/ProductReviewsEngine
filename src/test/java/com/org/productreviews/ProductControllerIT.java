package com.org.productreviews;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.org.productreviews.ProductReviewsApplication;
import com.org.productreviews.model.Product;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = ProductReviewsApplication.class)
public class ProductControllerIT {

	@Autowired
	TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	HttpHeaders headers = new HttpHeaders();

	@Test
	public void createProduct() {
		ResponseEntity<Long> responseEntity = restTemplate.postForEntity("/products", createProductRequest("iPhone X"),
				Long.class);
		Long productId = responseEntity.getBody();
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		assertTrue(productId == 5);
	}

	@Test
	public void testRetrieveProductById() {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<Product> response = restTemplate.exchange(createURLWithPort("/products/2"), HttpMethod.GET,
				entity, Product.class);
		assertNotNull(response.getBody());
		assertTrue(response.getBody().getName().equals("iphone case for 7"));
		assertTrue(response.getBody().getId() == 2);
	}

	@Test
	public void testRetrieveProductByIdNegativeCase() {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<Product> response = restTemplate.exchange(createURLWithPort("/products/25"), HttpMethod.GET,
				entity, Product.class);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	public void testRetrieveAllProducts() {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<Product[]> response = restTemplate.exchange(createURLWithPort("/products"), HttpMethod.GET,
				entity, Product[].class);
		assertNotNull(response.getBody());
		for (Product product : response.getBody()) {
			assertNotNull(product.getName());
			assertTrue(product.getId() > 0);
		}
	}

	private Product createProductRequest(String name) {
		return new Product(name);
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}
