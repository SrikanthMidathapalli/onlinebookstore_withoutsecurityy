package com.bookstore.onlinebookstore.dto;

import lombok.Data;

@Data
public class OrderItemDTO {
    private Long id;
    private Long bookId;
    private Integer quantity;
    private Double price;
}
