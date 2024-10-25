package com.bookstore.onlinebookstore.dto;

import lombok.Data;

@Data
public class BookDTO {
    private Long id;
    private String title;
    private Long authorId;
    private Long genreId;
    private Double price;
    private Integer stock;
    private String description;
}
