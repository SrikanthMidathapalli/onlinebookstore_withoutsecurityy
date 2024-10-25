package com.bookstore.onlinebookstore.dto;

import lombok.Data;

@Data
public class ReviewDTO {
    private Long id;
    private Long userId;
    private Long bookId;
    private String review;
    private Integer rating;
}
