package com.bookstore.onlinebookstore.controller;

import com.bookstore.onlinebookstore.dto.CartItemDTO;
import com.bookstore.onlinebookstore.model.CartItemEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CartItemControllerInterface {
    List<CartItemEntity> getCartItemsByUserId(@PathVariable Long userId);
    CartItemEntity createCartItem(@RequestBody CartItemEntity cartItem);
    ResponseEntity<CartItemEntity> updateCartItem(@PathVariable Long id, @RequestBody CartItemDTO cartItemDTO);
    ResponseEntity<Void> deleteCartItem(@PathVariable Long id);
	CartItemEntity createCartItem(CartItemDTO cartItemDTO);
}
