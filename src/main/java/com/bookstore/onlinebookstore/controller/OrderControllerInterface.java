package com.bookstore.onlinebookstore.controller;

import com.bookstore.onlinebookstore.dto.OrderDTO;
import com.bookstore.onlinebookstore.model.OrderEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface OrderControllerInterface {
    List<OrderEntity> getAllOrders();
    ResponseEntity<OrderEntity> getOrderById(@PathVariable Long id);
    List<OrderEntity> getOrdersByUserId(@PathVariable Long userId);
    OrderEntity createOrder(@RequestBody OrderEntity order);
    ResponseEntity<OrderEntity> updateOrder(@PathVariable Long id, @RequestBody OrderDTO orderDTO);
    ResponseEntity<Void> deleteOrder(@PathVariable Long id);
	OrderEntity createOrder(OrderDTO orderDTO);
}
