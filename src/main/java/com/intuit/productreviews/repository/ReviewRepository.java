package com.intuit.productreviews.repository;

import org.springframework.data.repository.CrudRepository;

import com.intuit.productreviews.model.Review;

public interface ReviewRepository extends CrudRepository<Review, Long>{
	
}
