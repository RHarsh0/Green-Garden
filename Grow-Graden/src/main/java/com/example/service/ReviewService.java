package com.example.service;

import java.util.List;
import java.util.Optional;

import com.example.model.Review;

public interface ReviewService {

	/**
	 * This method adds the new review for the product.
	 * @param Review
	 * @return Review
	 */
    Review createReview(Review review);

	/**
	 * This method gets review based on review id;
	 * @param id
	 * @return Optional<Review>
	 */
    Optional<Review> getReviewById(Integer id);

	/**
	 * This method gets all the review based on product id;
	 * @param productId
	 * @return List<Review>
	 */
    List<Review> getReviewsByProductId(Integer productId);

	/**
	 * This method gets all the review based on the user id;
	 * @param userId
	 * @return List<Review>
	 */
    List<Review> getReviewsByUserId(Integer userId);

	/**
	 * This method will delete the review based on reviewId and return's the review status.
	 * @param id
	 * @return String
	 */
    String deleteReview(Integer id);
}

