package com.bookstore.onlinebookstore.controller.impl;

import com.bookstore.onlinebookstore.controller.ReviewControllerInterface;
import com.bookstore.onlinebookstore.dto.ReviewDTO;
import com.bookstore.onlinebookstore.model.BookEntity;
import com.bookstore.onlinebookstore.model.ReviewEntity;
import com.bookstore.onlinebookstore.model.UserEntity;
import com.bookstore.onlinebookstore.service.ReviewService;
import com.bookstore.onlinebookstore.service.BookService;
import com.bookstore.onlinebookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewControllerImpl implements ReviewControllerInterface {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Override
    @GetMapping
    public List<ReviewEntity> getAllReviews() {
        return reviewService.findAllReviews();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ReviewEntity> getReviewById(@PathVariable Long id) {
        return reviewService.findReviewById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @GetMapping("/book/{bookId}")
    public List<ReviewEntity> getReviewsByBookId(@PathVariable Long bookId) {
        return reviewService.findReviewsByBookId(bookId);
    }

    @Override
    @PostMapping
    public ReviewEntity createReview(@RequestBody ReviewDTO reviewDTO) {
        return createReview(convertToEntity(reviewDTO));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ReviewEntity> updateReview(@PathVariable Long id, @RequestBody ReviewDTO reviewDTO) {
        return ResponseEntity.ok(reviewService.updateReview(id, reviewDTO));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ReviewEntity createReview(ReviewEntity review) {
        UserEntity user = userService.findUserById(review.getUser().getId())
                                     .orElseThrow(() -> new RuntimeException("User not found"));
        BookEntity book = bookService.findBookById(review.getBook().getId())
                                     .orElseThrow(() -> new RuntimeException("Book not found"));

        review.setUser(user);
        review.setBook(book);

        return reviewService.saveReview(review);
    }

    private ReviewEntity convertToEntity(ReviewDTO reviewDTO) {
        ReviewEntity review = new ReviewEntity();
        review.setId(reviewDTO.getId());
        review.setReview(reviewDTO.getReview());
        review.setRating(reviewDTO.getRating());

        // Set UserEntity and BookEntity placeholders
        UserEntity user = new UserEntity();
        user.setId(reviewDTO.getUserId());
        review.setUser(user);

        BookEntity book = new BookEntity();
        book.setId(reviewDTO.getBookId());
        review.setBook(book);

        return review;
    }
}
