package com.bookstore.onlinebookstore.service;

import com.bookstore.onlinebookstore.dto.OrderDTO;
import com.bookstore.onlinebookstore.model.OrderEntity;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<OrderEntity> findAllOrders();
    Optional<OrderEntity> findOrderById(Long id);
    List<OrderEntity> findOrdersByUserId(Long userId);
    OrderEntity saveOrder(OrderEntity order);
    void deleteOrder(Long id);
    OrderEntity updateOrder(Long id, OrderDTO orderDTO);
}
