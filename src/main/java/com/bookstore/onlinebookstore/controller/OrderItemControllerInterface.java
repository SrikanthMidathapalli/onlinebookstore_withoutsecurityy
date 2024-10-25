package com.bookstore.onlinebookstore.controller;

import com.bookstore.onlinebookstore.dto.OrderItemDTO;
import com.bookstore.onlinebookstore.model.OrderItemEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface OrderItemControllerInterface {
    List<OrderItemEntity> getAllOrderItems();
    ResponseEntity<OrderItemEntity> getOrderItemById(@PathVariable Long id);
    OrderItemEntity createOrderItem(@RequestBody OrderItemEntity orderItem);
    ResponseEntity<OrderItemEntity> updateOrderItem(@PathVariable Long id, @RequestBody OrderItemDTO orderItemDTO);
    ResponseEntity<Void> deleteOrderItem(@PathVariable Long id);
	OrderItemEntity createOrderItem(OrderItemDTO orderItemDTO);
}
