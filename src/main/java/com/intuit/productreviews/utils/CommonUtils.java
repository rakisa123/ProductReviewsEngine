package com.intuit.productreviews.utils;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intuit.productreviews.dto.ProductRequestDto;
import com.intuit.productreviews.dto.ReviewRequestDto;
import com.intuit.productreviews.model.Product;
import com.intuit.productreviews.model.Review;
import com.intuit.productreviews.service.ProductService;

@Component
public class CommonUtils {

	@Autowired
	private ProductService productService;

	public static final String IPHONE_CASE_6s = "iphone case for 6s";
	public static final String IPHONE_CASE_7 = "iphone case for 7";
	public static final String IPHONE_CASE_8 = "iphone case for 8";

	public boolean validateProductRequest(ProductRequestDto input) {
		if (input == null) {// || StringUtils.isNullOrEmpty(input.getName()) ||
							// !isAlphaNumeric(input.getName())) {
			throw new ValidationException(String.format("%s is not allowed for " + "name",
					input.getName() == null || !("").equals(input.getName()) ? "Null or empty " : input.getName()));
		}
		return true;
	}

	public Product getProductModel(ProductRequestDto input) {
		Product product = new Product();
		product.setName(input.getName());
		return product;
	}

	public Review getReviewModel(ReviewRequestDto input) {
		Review review = new Review();
		review.setScore(input.getScore());
		review.setDescription(input.getComment());
		review.setProduct(getProduct(input.getProductId()));
		review.setUserId(input.getUserId());
		return review;
	}

	private Product getProduct(Long productId) {
		return productService.getProductById(productId);
	}

	public boolean validateReviewRequest(ReviewRequestDto input) {
		if (input == null)
			throw new ValidationException("Review cannot be null. Please check the request you have posted");
		return true;
	}

	/*
	 * public boolean validateAlphaNumericIdentifier(String id) { String regex =
	 * "^[a-zA-Z0-9]+$"; Pattern pattern = Pattern.compile(regex); Matcher
	 * matcher = pattern.matcher(id); return matcher.matches(); }
	 */

	/*
	 * private boolean isAlphaNumeric(String s){ String pattern=
	 * "^[a-zA-Z0-9]*$"; return s.matches(pattern); }
	 */
}
