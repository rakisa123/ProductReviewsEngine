package com.org.productreviews.dao.db.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.org.productreviews.repository.ProductRepository;
import com.org.productreviews.repository.ReviewRepository;

public abstract class AbstractDao {
	@Autowired
	protected ReviewRepository reviewRepository;
	
	@Autowired
	protected ProductRepository productRepository;	
}
