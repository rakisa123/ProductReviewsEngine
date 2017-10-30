package com.org.productreviews.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * <p>
 * Developed for demo purpose by Sagar Mummidivarapu
 * <p>
 * Classification level: normal
 * <p>
 * Written on 10/29/2017. This class is the model object that holds the product
 * details. This class also maintains one to many relationship with reviews.
 */

@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;	

	private String name;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonManagedReference
	private Set<Review> reviews;
	
	private double aggregatedScore;	

	// Constructors
	public Product() {
	}

	public Product(String name) {
		this.name = name;
	}

	//getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}	

	public void setName(String name) {
		this.name = name;
	}
	
	public Set<Review> getReviews() {
		return reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}

	public double getAggregatedScore() {
		return aggregatedScore;
	}

	public void setAggregatedScore(double aggregatedScore) {
		this.aggregatedScore = aggregatedScore;
	}
	
	public String toString() {
		String info = "";
		JSONObject jsonInfo = new JSONObject();
		try {
			jsonInfo.put("name", this.name);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JSONArray reviewArray = new JSONArray();
		if (this.reviews != null) {
			this.reviews.forEach(review -> {
				JSONObject subJson = new JSONObject();
				try {
					subJson.put("score", review.getScore());
					subJson.put("description", review.getDescription());
					subJson.put("user_id", review.getUserId());
					reviewArray.put(subJson);
					jsonInfo.put("reviews", reviewArray);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			});
		}
		info = jsonInfo.toString();
		return info;
	}
}
