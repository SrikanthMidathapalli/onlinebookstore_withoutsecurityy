package com.bookstore.onlinebookstore.service;

import com.bookstore.onlinebookstore.dto.ReviewDTO;
import com.bookstore.onlinebookstore.model.ReviewEntity;
import java.util.List;
import java.util.Optional;

public interface ReviewService {
    List<ReviewEntity> findAllReviews();
    Optional<ReviewEntity> findReviewById(Long id);
    List<ReviewEntity> findReviewsByBookId(Long bookId);
    ReviewEntity saveReview(ReviewEntity review);
    void deleteReview(Long id);
    ReviewEntity updateReview(Long id, ReviewDTO reviewDTO);
}
