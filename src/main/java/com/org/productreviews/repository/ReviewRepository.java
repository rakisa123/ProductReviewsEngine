package com.org.productreviews.repository;

import org.springframework.data.repository.CrudRepository;

import com.org.productreviews.model.Review;

public interface ReviewRepository extends CrudRepository<Review, Long>{
	
}
