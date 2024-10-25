package com.bookstore.onlinebookstore.service.impl;

import com.bookstore.onlinebookstore.dto.CartItemDTO;
import com.bookstore.onlinebookstore.model.BookEntity;
import com.bookstore.onlinebookstore.model.CartItemEntity;
import com.bookstore.onlinebookstore.model.UserEntity;
import com.bookstore.onlinebookstore.repository.BookRepository;
import com.bookstore.onlinebookstore.repository.CartItemRepository;
import com.bookstore.onlinebookstore.repository.UserRepository;
import com.bookstore.onlinebookstore.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<CartItemEntity> findCartItemsByUserId(Long userId) {
        return cartItemRepository.findByUser_Id(userId);
    }

    @Override
    public CartItemEntity saveCartItem(CartItemEntity cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @Override
    public void deleteCartItem(Long id) {
        cartItemRepository.deleteById(id);
    }

    @Override
    public CartItemEntity updateCartItem(Long id, CartItemDTO cartItemDTO) {
        CartItemEntity cartItem = cartItemRepository.findById(id).orElseThrow(() -> new RuntimeException("Cart item not found"));
        UserEntity user = userRepository.findById(cartItemDTO.getUserId())
                              .orElseThrow(() -> new RuntimeException("User not found"));
        BookEntity book = bookRepository.findById(cartItemDTO.getBookId())
                              .orElseThrow(() -> new RuntimeException("Book not found"));

        cartItem.setUser(user);
        cartItem.setBook(book);
        cartItem.setQuantity(cartItemDTO.getQuantity());

        return cartItemRepository.save(cartItem);
    }

    private CartItemDTO convertToDTO(CartItemEntity cartItem) {
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setId(cartItem.getId());
        cartItemDTO.setUserId(cartItem.getUser().getId());
        cartItemDTO.setBookId(cartItem.getBook().getId());
        cartItemDTO.setQuantity(cartItem.getQuantity());
        return cartItemDTO;
    }

    private CartItemEntity convertToEntity(CartItemDTO cartItemDTO) {
        CartItemEntity cartItem = new CartItemEntity();
        cartItem.setId(cartItemDTO.getId());
        cartItem.setQuantity(cartItemDTO.getQuantity());
        return cartItem;
    }
}
