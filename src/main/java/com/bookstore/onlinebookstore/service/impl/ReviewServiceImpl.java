package com.bookstore.onlinebookstore.service.impl;

import com.bookstore.onlinebookstore.dto.ReviewDTO;
import com.bookstore.onlinebookstore.model.BookEntity;
import com.bookstore.onlinebookstore.model.ReviewEntity;
import com.bookstore.onlinebookstore.model.UserEntity;
import com.bookstore.onlinebookstore.repository.BookRepository;
import com.bookstore.onlinebookstore.repository.ReviewRepository;
import com.bookstore.onlinebookstore.repository.UserRepository;
import com.bookstore.onlinebookstore.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<ReviewEntity> findAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public Optional<ReviewEntity> findReviewById(Long id) {
        return reviewRepository.findById(id);
    }

    @Override
    public List<ReviewEntity> findReviewsByBookId(Long bookId) {
        return reviewRepository.findByBook_Id(bookId);
    }

    @Override
    public ReviewEntity saveReview(ReviewEntity review) {
        return reviewRepository.save(review);
    }

    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public ReviewEntity updateReview(Long id, ReviewDTO reviewDTO) {
        ReviewEntity review = reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Review not found"));
        UserEntity user = userRepository.findById(reviewDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        BookEntity book = bookRepository.findById(reviewDTO.getBookId()).orElseThrow(() -> new RuntimeException("Book not found"));

        review.setUser(user);
        review.setBook(book);
        review.setReview(reviewDTO.getReview());
        review.setRating(reviewDTO.getRating());

        return reviewRepository.save(review);
    }

    private ReviewDTO convertToDTO(ReviewEntity review) {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(review.getId());
        reviewDTO.setUserId(review.getUser().getId());
        reviewDTO.setBookId(review.getBook().getId());
        reviewDTO.setReview(review.getReview());
        reviewDTO.setRating(review.getRating());
        return reviewDTO;
    }

    private ReviewEntity convertToEntity(ReviewDTO reviewDTO) {
        ReviewEntity review = new ReviewEntity();
        review.setId(reviewDTO.getId());
        review.setReview(reviewDTO.getReview());
        review.setRating(reviewDTO.getRating());
        return review;
    }
}
