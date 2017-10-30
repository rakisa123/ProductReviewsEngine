package com.intuit.productreviews.repository;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.intuit.productreviews.model.Product;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {
	@Autowired
	TestEntityManager entityManager;

	@Autowired
	ProductRepository productRepository;

	@Test
	public void whenFindById_thenReturnProduct() {
		// given
		Product iPhoneX = new Product("iphone x");
		entityManager.persist(iPhoneX);
		entityManager.flush();

		Product found = productRepository.findByName(iPhoneX.getName());
		Product iPhoneXFetch = productRepository.findOne(found.getId());
		assertTrue(found.getName().equals(iPhoneX.getName()));
		assertTrue(iPhoneXFetch.getName().equals(iPhoneX.getName()));
	}

}
