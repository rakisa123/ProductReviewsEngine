package com.org.productreviews.api;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.org.productreviews.dto.ReviewRequestDto;
import com.org.productreviews.model.Review;
import com.org.productreviews.service.ReviewService;
import com.org.productreviews.utils.CommonUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@RequestMapping("/reviews")
@Api(value = "ReviewsAPI")
@Validated
public class ReviewController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ReviewService reviewService;

	@Autowired
	private CommonUtils utils;

	@ApiOperation(value = "Save reviews for the given product")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Long> reviews(@Valid @RequestBody ReviewRequestDto input) {
		if (utils.validateReviewRequest(input)) {
			logger.info("Review request validation succeeded");
			Review review = utils.getReviewModel(input);
			logger.info("Review response sent successfully");
			return new ResponseEntity<>(reviewService.saveReview(review), HttpStatus.CREATED);
		} else
			return ResponseEntity.noContent().build();
	}
}
