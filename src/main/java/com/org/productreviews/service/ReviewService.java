package com.org.productreviews.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.productreviews.dao.DataAccessFacade;
import com.org.productreviews.model.Review;

/**
 * <p>
 * Developed for demo purpose by Sagar Mummidivarapu
 * <p>
 * Classification level: normal
 * <p>
 * Written on 10/23/2017. This class is the representation of Review
 * functionalities
 */

@Service
public class ReviewService {

	@Autowired
	private DataAccessFacade facade;

	public long saveReview(Review review) {
		return facade.save(review);
	}
}
