package com.intuit.productreviews.utils;

import org.springframework.stereotype.Component;

import com.intuit.productreviews.model.Product;

@Component
public class ProductUtils {

	public void setAggregatedScore(Product product) {
		if (product != null && product.getReviews() != null && product.getReviews().size() > 0) {
			product.setAggregatedScore(
					product.getReviews().parallelStream().mapToDouble(i -> i.getScore()).average().getAsDouble());
		} else
			product.setAggregatedScore(0);
	}

}
