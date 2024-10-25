package com.bookstore.onlinebookstore.controller;

import com.bookstore.onlinebookstore.dto.ReviewDTO;
import com.bookstore.onlinebookstore.model.ReviewEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ReviewControllerInterface {
    List<ReviewEntity> getAllReviews();
    ResponseEntity<ReviewEntity> getReviewById(@PathVariable Long id);
    List<ReviewEntity> getReviewsByBookId(@PathVariable Long bookId);
    ReviewEntity createReview(@RequestBody ReviewEntity review);
    ResponseEntity<ReviewEntity> updateReview(@PathVariable Long id, @RequestBody ReviewDTO reviewDTO);
    ResponseEntity<Void> deleteReview(@PathVariable Long id);
	ReviewEntity createReview(ReviewDTO reviewDTO);
}
