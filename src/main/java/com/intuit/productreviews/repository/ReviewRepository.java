package com.intuit.productreviews.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.intuit.productreviews.model.Product;
import com.intuit.productreviews.model.Review;
import com.intuit.productreviews.queries.SqlQueries;

public interface ReviewRepository extends CrudRepository<Review, Long>{
	/***
	 * Returns all reviews with the given productId. This method will be translated into a query using the one declared in
	 * the {@link Query} annotation declared one.
	 * 
	 * @param productId
	 * @return
	 **/
	@Query(SqlQueries.FETCH_ALL_REVIEWS_BY_PRODUCTS)
	List<Review> findReviewByProduct(Product product);
}
