package com.bookstore.onlinebookstore.controller.impl;

import com.bookstore.onlinebookstore.controller.CartItemControllerInterface;
import com.bookstore.onlinebookstore.dto.CartItemDTO;
import com.bookstore.onlinebookstore.model.BookEntity;
import com.bookstore.onlinebookstore.model.CartItemEntity;
import com.bookstore.onlinebookstore.model.UserEntity;
import com.bookstore.onlinebookstore.service.BookService;
import com.bookstore.onlinebookstore.service.CartItemService;
import com.bookstore.onlinebookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart-items")
public class CartItemControllerImpl implements CartItemControllerInterface {

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Override
    @GetMapping("/user/{userId}")
    public List<CartItemEntity> getCartItemsByUserId(@PathVariable Long userId) {
        return cartItemService.findCartItemsByUserId(userId);
    }

    @Override
    @PostMapping
    public CartItemEntity createCartItem(@RequestBody CartItemDTO cartItemDTO) {
        UserEntity user = userService.findUserById(cartItemDTO.getUserId())
                                     .orElseThrow(() -> new RuntimeException("User not found"));
        BookEntity book = bookService.findBookById(cartItemDTO.getBookId())
                                     .orElseThrow(() -> new RuntimeException("Book not found"));

        CartItemEntity cartItem = new CartItemEntity();
        cartItem.setUser(user);
        cartItem.setBook(book);
        cartItem.setQuantity(cartItemDTO.getQuantity());

        return cartItemService.saveCartItem(cartItem);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<CartItemEntity> updateCartItem(@PathVariable Long id, @RequestBody CartItemDTO cartItemDTO) {
        return ResponseEntity.ok(cartItemService.updateCartItem(id, cartItemDTO));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable Long id) {
        cartItemService.deleteCartItem(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public CartItemEntity createCartItem(CartItemEntity cartItem) {
        UserEntity user = userService.findUserById(cartItem.getUser().getId())
                                     .orElseThrow(() -> new RuntimeException("User not found"));
        BookEntity book = bookService.findBookById(cartItem.getBook().getId())
                                     .orElseThrow(() -> new RuntimeException("Book not found"));

        cartItem.setUser(user);
        cartItem.setBook(book);

        return cartItemService.saveCartItem(cartItem);
    }
}
