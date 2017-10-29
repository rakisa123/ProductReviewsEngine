package com.intuit.productreviews.dao.db.impl;

import org.springframework.stereotype.Component;

import com.intuit.productreviews.dao.db.ReviewDao;
import com.intuit.productreviews.model.Review;

@Component
public class ReviewDaoImpl extends AbstractDao implements ReviewDao {

	@Override
	public long save(Review review) {
		reviewRepository.save(review);
		return review.getId();
	}
}
