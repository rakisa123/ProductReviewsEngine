package com.intuit.productreviews.model;

import java.time.Instant;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.json.JSONException;
import org.json.JSONObject;
import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * <p>
 * Developed for demo purpose by Sagar Mummidivarapu
 * <p>
 * Classification level: normal
 * <p>
 * Written on 10/29/2017. This class is the model object that holds the reviews
 * or ratings details. This class also maintains many to one relationship with
 * products.
 */

@Entity
@Table(name="review")
public class Review{

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private double score;
	private String description;
	private Date dateAdded;
	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "product_id")
	@JsonBackReference
	private Product product;
	@Column(name = "user_id", nullable = false)
	private String userId;

	// Constructors
	public Review() {

	}

	public Review(double score, String comment, Product product, String userId) {
		super();
		this.score = score;
		this.description = comment;
		this.userId = userId;
		this.product=product;
		this.dateAdded = Date.from(Instant.now());// UTC
	}

	// getters
	public Long getId() {
		return id;
	}
	
	public Date getDateAdded() {
		return dateAdded;
	}
	
	public double getScore() {
		return score;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public Product getProduct() {
		return product;
	}

	
	//setters
	public void setId(Long id) {
		this.id = id;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	
	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	
	public void setScore(double rating) {
		this.score = rating;
	}

	
	public void setDescription(String comment) {
		this.description = comment;
	}

	
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String toString(){
    	String info = "";
    	
        JSONObject jsonString = new JSONObject();
        try {
        	jsonString.put("score", this.score);
			jsonString.put("description", this.description);
			jsonString.put("user_id", this.userId);
			jsonString.put("date_added", this.dateAdded);
			JSONObject productObj = new JSONObject();
			productObj.put("name", this.product.getName());
			jsonString.put("product", productObj);
	        
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        info = jsonString.toString();
        return info;
    }
}
