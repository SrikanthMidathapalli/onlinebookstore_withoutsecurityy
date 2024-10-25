package com.bookstore.onlinebookstore.service;

import com.bookstore.onlinebookstore.dto.CartItemDTO;
import com.bookstore.onlinebookstore.model.CartItemEntity;
import java.util.List;

public interface CartItemService {
    List<CartItemEntity> findCartItemsByUserId(Long userId);
    CartItemEntity saveCartItem(CartItemEntity cartItem);
    void deleteCartItem(Long id);
    CartItemEntity updateCartItem(Long id, CartItemDTO cartItemDTO);
}
