package com.intuit.productreviews.dao.db.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.intuit.productreviews.config.CacheConfig;
import com.intuit.productreviews.dao.db.ProductDao;
import com.intuit.productreviews.model.Product;
import com.intuit.productreviews.utils.ProductUtils;

@Component
public class ProductDaoImpl extends AbstractDao implements ProductDao {

	@Autowired
	ProductUtils productUtils;

	@Override
	public long save(Product product) {
		product = productRepository.save(product);
		return product.getId();
	}

	@Override
	public Product getProductById(long id) {
		Product product = productRepository.findOne(id);
		if(product == null)
			throw new ResourceNotFoundException("Given product for identifier "+ id + " not found", null);
		productUtils.setAggregatedScore(product);
		return product;
	}

	@Override
	@Cacheable(value = CacheConfig.ALL_PRODUCTS_CACHE_KEY, key = "#root.methodName", sync = true)
	public List<Product> getAllProducts() {
		List<Product> lstProducts = (List<Product>) productRepository.findAll();
		lstProducts.forEach(product -> productUtils.setAggregatedScore(product));
		return lstProducts;
	}

	@Override
	public Product getProductByName(String name) {
		return productRepository.findByName(name);
	}

	@Override
	public void saveAll(Set<Product> setProducts) {
		productRepository.save(setProducts);
	}
}
