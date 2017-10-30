package com.org.productreviews.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.productreviews.dao.DataAccessFacade;
import com.org.productreviews.model.Product;

/**
 * <p>
 * Developed for demo purpose by Sagar Mummidivarapu
 * <p>
 * Classification level: normal
 * <p>
 * Written on 10/29/2017. This class is the representation of product functionalities
 */

@Service
public class ProductService {
	
	@Autowired
	private DataAccessFacade facade;
	
	public long saveProduct(Product product) {
		return facade.save(product);
	}
	
	public void saveAllProducts(Set<Product> setProducts){
		facade.saveAll(setProducts);
	}

	public Product getProductById(long id) {
		return facade.getProductById(id);
	}
	
	public Product getProductByName(String name) {
		return facade.getProductByName(name);
	}
	
	public List<Product> getAllProducts() {
		return facade.getAllProducts();
	}
}
