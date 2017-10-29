package com.intuit.productreviews.dao.db.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.intuit.productreviews.repository.ProductRepository;
import com.intuit.productreviews.repository.ReviewRepository;

public abstract class AbstractDao {
	@Autowired
	protected ReviewRepository reviewRepository;
	
	@Autowired
	protected ProductRepository productRepository;	
}
