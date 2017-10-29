package com.intuit.productreviews.preload;

import java.util.HashSet;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.intuit.productreviews.model.Product;
import com.intuit.productreviews.model.Review;
import com.intuit.productreviews.repository.ProductRepository;
import com.intuit.productreviews.repository.ReviewRepository;
import com.intuit.productreviews.utils.CommonUtils;

/**
 * <p>
 * Developed for demo purpose by Sagar Mummidivarapu
 * <p>
 * Classification level: normal
 * <p>
 * Written on 10/29/2017. This class is called during the application start up and loads the db with some initial products catalog
 * and test some basic operations
 */

@Component
public class ProductReviewsCommandLineRunner implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(Product.class);
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ReviewRepository reviewRepository;
	
	@Override
	@Transactional
	public void run(String... arg0) throws Exception {
		saveWithApproach1();
		saveWithApproach2();
		showAllData();
		showData();
	}

	private void showData() {
		Product iPhone = productRepository.findOne(1L);// findOne(1L);
		System.out.println(iPhone.getName());
		System.out.println(iPhone.getId());
		System.out.println("===================Review List:==================");
		iPhone.getReviews().forEach(System.out::println);
	}

	private void showAllData() {
		// get All products
		Iterable<Product> productLst = productRepository.findAll();
		System.out.println("===================Product List:==================");
		for (Product product : productLst) {
			System.out.println(product.getId());
			System.out.println(product.getName());
			if (product.getReviews() != null)
				product.getReviews().forEach(System.out::println);
		}
	}

	private void saveWithApproach2() {
		Product iPhone = new Product("iphone");
		Product iPad = new Product("ipad");
		// save products
		productRepository.save(iPhone);
		productRepository.save(iPad);
		/*
		 * Then store reviews with persisted products.
		 */
		Review iPhoneReview1 = new Review(4.0, "Good but edges are sharp", iPhone, "1");
		Review iPhoneReview2 = new Review(3.0, "Decent !!!", iPhone, "1");

		Review iPadReview1 = new Review(3.0, "Decent !!!", iPad, "1");
		Review iPadReview2 = new Review(2.0, "Poor quality. I vote down", iPad, "2");

		// save reviews
		reviewRepository.save(iPhoneReview1);
		reviewRepository.save(iPhoneReview2);

		reviewRepository.save(iPadReview1);
		reviewRepository.save(iPadReview2);

	}

	private void saveWithApproach1() {
		logger.info("-------------------------------");
		logger.info("Adding product 1");
		logger.info("-------------------------------");

		Product iPhoneCase6S = new Product(CommonUtils.IPHONE_CASE_6s);
		Product iPhoneCase7 = new Product(CommonUtils.IPHONE_CASE_7);

		Review iPhoneCase6SReview1 = new Review(4.0, "Good but edges are sharp", iPhoneCase6S, "1");
		Review iPhoneCase6SReview2 = new Review(3.0, "Decent !!!", iPhoneCase6S, "1");

		Review iPhoneCase7Review1 = new Review(3.0, "Decent !!!", iPhoneCase7, "1");
		Review iPhoneCase7Review2 = new Review(2.0, "Poor quality. I vote down", iPhoneCase7, "2");

		iPhoneCase6S.setReviews(new HashSet<Review>() {
			{
				add(iPhoneCase6SReview1);
				add(iPhoneCase6SReview2);
			}
		});

		iPhoneCase7.setReviews(new HashSet<Review>() {
			{
				add(iPhoneCase7Review1);
				add(iPhoneCase7Review2);
			}
		});

		productRepository.save(iPhoneCase6S);
		productRepository.save(iPhoneCase7);
	}
}
