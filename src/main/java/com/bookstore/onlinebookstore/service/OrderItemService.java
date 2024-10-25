package com.bookstore.onlinebookstore.service;

import com.bookstore.onlinebookstore.dto.OrderItemDTO;
import com.bookstore.onlinebookstore.model.OrderItemEntity;
import java.util.List;
import java.util.Optional;

public interface OrderItemService {
    List<OrderItemEntity> findAllOrderItems();
    Optional<OrderItemEntity> findOrderItemById(Long id);
    OrderItemEntity saveOrderItem(OrderItemEntity orderItem);
    void deleteOrderItem(Long id);
    OrderItemEntity updateOrderItem(Long id, OrderItemDTO orderItemDTO);
}
