package com.org.productreviews.dao.db;

import com.org.productreviews.model.Review;

public interface ReviewDao {
	
	long save(Review review);
}
