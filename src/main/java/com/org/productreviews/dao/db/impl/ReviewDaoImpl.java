package com.org.productreviews.dao.db.impl;

import org.springframework.stereotype.Component;

import com.org.productreviews.dao.db.ReviewDao;
import com.org.productreviews.model.Review;

@Component
public class ReviewDaoImpl extends AbstractDao implements ReviewDao {

	@Override
	public long save(Review review) {
		reviewRepository.save(review);
		return review.getId();
	}
}
