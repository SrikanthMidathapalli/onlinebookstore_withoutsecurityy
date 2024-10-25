package com.bookstore.onlinebookstore.service.impl;

import com.bookstore.onlinebookstore.dto.OrderItemDTO;
import com.bookstore.onlinebookstore.model.BookEntity;
import com.bookstore.onlinebookstore.model.OrderItemEntity;
import com.bookstore.onlinebookstore.repository.BookRepository;
import com.bookstore.onlinebookstore.repository.OrderItemRepository;
import com.bookstore.onlinebookstore.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<OrderItemEntity> findAllOrderItems() {
        return orderItemRepository.findAll();
    }

    @Override
    public Optional<OrderItemEntity> findOrderItemById(Long id) {
        return orderItemRepository.findById(id);
    }

    @Override
    public OrderItemEntity saveOrderItem(OrderItemEntity orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Override
    public void deleteOrderItem(Long id) {
        orderItemRepository.deleteById(id);
    }

    @Override
    public OrderItemEntity updateOrderItem(Long id, OrderItemDTO orderItemDTO) {
        OrderItemEntity orderItem = orderItemRepository.findById(id).orElseThrow(() -> new RuntimeException("Order item not found"));
        BookEntity book = bookRepository.findById(orderItemDTO.getBookId()).orElseThrow(() -> new RuntimeException("Book not found"));

        orderItem.setBook(book);
        orderItem.setQuantity(orderItemDTO.getQuantity());
        orderItem.setPrice(orderItemDTO.getPrice());

        return orderItemRepository.save(orderItem);
    }

    private OrderItemDTO convertToDTO(OrderItemEntity orderItem) {
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setId(orderItem.getId());
        orderItemDTO.setBookId(orderItem.getBook().getId());
        orderItemDTO.setQuantity(orderItem.getQuantity());
        orderItemDTO.setPrice(orderItem.getPrice());
        return orderItemDTO;
    }

    private OrderItemEntity convertToEntity(OrderItemDTO orderItemDTO) {
        OrderItemEntity orderItem = new OrderItemEntity();
        orderItem.setId(orderItemDTO.getId());
        orderItem.setQuantity(orderItemDTO.getQuantity());
        orderItem.setPrice(orderItemDTO.getPrice());
        return orderItem;
    }
}
