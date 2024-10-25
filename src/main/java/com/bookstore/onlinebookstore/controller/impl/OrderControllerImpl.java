package com.bookstore.onlinebookstore.controller.impl;

import com.bookstore.onlinebookstore.controller.OrderControllerInterface;
import com.bookstore.onlinebookstore.dto.OrderDTO;
import com.bookstore.onlinebookstore.dto.OrderItemDTO;
import com.bookstore.onlinebookstore.model.BookEntity;
import com.bookstore.onlinebookstore.model.OrderEntity;
import com.bookstore.onlinebookstore.model.OrderItemEntity;
import com.bookstore.onlinebookstore.model.UserEntity;
import com.bookstore.onlinebookstore.service.OrderService;
import com.bookstore.onlinebookstore.service.UserService;
import com.bookstore.onlinebookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderControllerImpl implements OrderControllerInterface {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Override
    @GetMapping
    public List<OrderEntity> getAllOrders() {
        return orderService.findAllOrders();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<OrderEntity> getOrderById(@PathVariable Long id) {
        return orderService.findOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @GetMapping("/user/{userId}")
    public List<OrderEntity> getOrdersByUserId(@PathVariable Long userId) {
        return orderService.findOrdersByUserId(userId);
    }

    @Override
    @PostMapping
    public OrderEntity createOrder(@RequestBody OrderDTO orderDTO) {
        UserEntity user = userService.findUserById(orderDTO.getUserId())
                                     .orElseThrow(() -> new RuntimeException("User not found"));

        List<OrderItemEntity> orderItems = orderDTO.getOrderItems().stream()
                .map(this::convertOrderItemDTOToEntity)
                .collect(Collectors.toList());

        OrderEntity order = new OrderEntity();
        order.setUser(user);
        order.setOrderDate(orderDTO.getOrderDate());
        order.setTotalAmount(orderDTO.getTotalAmount());
        order.setOrderItems(orderItems);

        return orderService.saveOrder(order);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<OrderEntity> updateOrder(@PathVariable Long id, @RequestBody OrderDTO orderDTO) {
        return ResponseEntity.ok(orderService.updateOrder(id, orderDTO));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public OrderEntity createOrder(OrderEntity order) {
        UserEntity user = userService.findUserById(order.getUser().getId())
                                     .orElseThrow(() -> new RuntimeException("User not found"));

        List<OrderItemEntity> orderItems = order.getOrderItems().stream()
                .map(this::convertOrderItemEntityToEntity)
                .collect(Collectors.toList());

        order.setUser(user);
        order.setOrderItems(orderItems);

        return orderService.saveOrder(order);
    }

    private OrderEntity convertOrderDTOToEntity(OrderDTO orderDTO) {
        OrderEntity order = new OrderEntity();
        order.setUser(new UserEntity()); // Set a placeholder, this will be replaced in createOrder(OrderEntity order)
        order.getUser().setId(orderDTO.getUserId());
        order.setOrderDate(orderDTO.getOrderDate());
        order.setTotalAmount(orderDTO.getTotalAmount());
        List<OrderItemEntity> orderItems = orderDTO.getOrderItems().stream()
                .map(this::convertOrderItemDTOToEntity)
                .collect(Collectors.toList());
        order.setOrderItems(orderItems);
        return order;
    }

    private OrderItemEntity convertOrderItemDTOToEntity(OrderItemDTO orderItemDTO) {
        OrderItemEntity orderItem = new OrderItemEntity();
        orderItem.setId(orderItemDTO.getId());
        orderItem.setQuantity(orderItemDTO.getQuantity());
        orderItem.setPrice(orderItemDTO.getPrice());

        // Set BookEntity
        BookEntity book = bookService.findBookById(orderItemDTO.getBookId())
                                     .orElseThrow(() -> new RuntimeException("Book not found"));
        orderItem.setBook(book);

        return orderItem;
    }

    private OrderItemEntity convertOrderItemEntityToEntity(OrderItemEntity orderItem) {
        BookEntity book = bookService.findBookById(orderItem.getBook().getId())
                                     .orElseThrow(() -> new RuntimeException("Book not found"));
        orderItem.setBook(book);

        return orderItem;
    }
}
