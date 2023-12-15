package com.example.service;

import java.util.List;
import java.util.Optional;

import com.example.model.Rating;

public interface RatingService {
	/**
	 * This method adds new rating for the product.;
	 * @param Rating
	 * @return Rating
	 */
	Rating createRating(Rating rating);

	/**
	 * This method gets rating by rating id;
	 * @param id
	 * @return Optional<Rating>
	 */
    Optional<Rating> getRatingById(Integer id);

	/**
	 * This method gets rating for a particaular product id;
	 * @param productId
	 * @return List<Rating>
	 */
    List<Rating> getRatingsByProductId(Integer productId);

	/**
	 * This method gets all the rating done by a particular user by userid;
	 * @param userId
	 * @return List<Rating>
	 */
    List<Rating> getRatingsByUserId(Integer userId);

	/**
	 * This method delets the rating by rating id;
	 * @param id
	 * @return String
	 */
    String deleteRating(Integer id);
}
