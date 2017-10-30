package com.intuit.productreviews.utils;

import java.text.DecimalFormat;

import org.springframework.stereotype.Component;

import com.intuit.productreviews.model.Product;

@Component
public class ProductUtils {

	public void setAggregatedScore(Product product) {
		if (product != null && product.getReviews() != null && product.getReviews().size() > 0) {
			DecimalFormat numberFormat = new DecimalFormat("#.0");
			Double average = product.getReviews().parallelStream().mapToDouble(i -> i.getScore()).average()
					.getAsDouble();
			product.setAggregatedScore(Double.parseDouble(numberFormat.format(average)));
		} else
			product.setAggregatedScore(0);
	}

}
