package com.intuit.productreviews.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.intuit.productreviews.dto.ProductRequestDto;
import com.intuit.productreviews.model.Product;
import com.intuit.productreviews.service.ProductService;
import com.intuit.productreviews.utils.CommonUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@RequestMapping("/products")
@Api(value = "ProductsAPI")
@Validated
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private CommonUtils utils;

	@ApiOperation(value = "Save product")
	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<Long> products(@Valid @RequestBody ProductRequestDto input) {		
		if (utils.validateProductRequest(input)) {
			Product product = utils.getProductModel(input);
			return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.CREATED);
		} else
			return ResponseEntity.noContent().build();
	}

	@ApiOperation(value = "Fetch product information by product identifier")
	@RequestMapping(value = "/{id:[\\d]+}", method = RequestMethod.GET)
	public ResponseEntity<Product> products(@PathVariable(value = "id", required = true) Long productId) {
		return new ResponseEntity<>(productService.getProductById(productId), HttpStatus.OK);
	}

	@ApiOperation(value = "Fetch all products")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Product>> products() {
		return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
	}
}
