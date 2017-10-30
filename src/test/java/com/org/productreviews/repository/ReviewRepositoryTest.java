package com.org.productreviews.repository;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.org.productreviews.model.Product;
import com.org.productreviews.model.Review;
import com.org.productreviews.repository.ProductRepository;
import com.org.productreviews.repository.ReviewRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ReviewRepositoryTest {
	@Autowired
	TestEntityManager entityManager;

	@Autowired
	ReviewRepository reviewRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Test
	public void whenPersistReview_thenReturnReview() {
		Review review = getReviewMock(3.0,"looks good","rakisa");
		entityManager.persist(review);
		entityManager.flush();
		Review found = reviewRepository.findOne(review.getId());
		assertNotNull(found);
	}
	
	private Review getReviewMock(double score, String comment, String userId){
		Product iPhoneX = new Product("iPhone x");
		Review iPhoneXReview = new Review(score, comment, iPhoneX, userId);
		return iPhoneXReview;
	}
}
