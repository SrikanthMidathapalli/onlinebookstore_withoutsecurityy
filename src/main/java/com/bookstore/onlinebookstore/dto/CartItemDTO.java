package com.bookstore.onlinebookstore.dto;

import lombok.Data;

@Data
public class CartItemDTO {
    private Long id;
    private Long userId;
    private Long bookId;
    private Integer quantity;
}
