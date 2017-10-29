package com.intuit.productreviews.dao;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intuit.productreviews.dao.db.ProductDao;
import com.intuit.productreviews.dao.db.ReviewDao;
import com.intuit.productreviews.model.Product;
import com.intuit.productreviews.model.Review;

@Component
public class DataAccessFacadeImpl implements DataAccessFacade{
	
	@Autowired
    ProductDao productDao;
	
	@Autowired
    ReviewDao reviewDao;

	@Override
	public long save(Product product) {
		return productDao.save(product);
	}

	@Override
	public Product getProductByName(String name) {
		return productDao.getProductByName(name);
	}
	
	@Override
	public Product getProductById(long id) {
		return productDao.getProductById(id);
	}

	@Override
	public List<Product> getAllProducts() {
		return productDao.getAllProducts();
	}

	@Override
	public void saveAll(Set<Product> setProducts) {
		productDao.saveAll(setProducts);
	}
	
	@Override
	public long save(Review review) {
		return reviewDao.save(review);
	}
}
