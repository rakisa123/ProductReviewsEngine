package com.intuit.productreviews.repository;

import org.springframework.data.repository.CrudRepository;

import com.intuit.productreviews.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{
	/**
	 * Find the product with the given product name. This method will be translated into a query using the
	 * {@link javax.persistence.NamedQuery} annotation at the {@link User} class.
	 * 
	 * @param name
	 * @return
	 */
	Product findByName(String name);
}
