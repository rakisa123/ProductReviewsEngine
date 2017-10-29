package com.intuit.productreviews.dao.db;

import com.intuit.productreviews.model.Review;

public interface ReviewDao {
	
	long save(Review review);
}
