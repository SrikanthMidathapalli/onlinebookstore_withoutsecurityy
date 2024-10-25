package com.bookstore.onlinebookstore.controller.impl;

import com.bookstore.onlinebookstore.controller.OrderItemControllerInterface;
import com.bookstore.onlinebookstore.dto.OrderItemDTO;
import com.bookstore.onlinebookstore.model.BookEntity;
import com.bookstore.onlinebookstore.model.OrderItemEntity;
import com.bookstore.onlinebookstore.service.OrderItemService;
import com.bookstore.onlinebookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemControllerImpl implements OrderItemControllerInterface {

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private BookService bookService;

    @Override
    @GetMapping
    public List<OrderItemEntity> getAllOrderItems() {
        return orderItemService.findAllOrderItems();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<OrderItemEntity> getOrderItemById(@PathVariable Long id) {
        return orderItemService.findOrderItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public OrderItemEntity createOrderItem(@RequestBody OrderItemDTO orderItemDTO) {
        return createOrderItem(convertToEntity(orderItemDTO));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<OrderItemEntity> updateOrderItem(@PathVariable Long id, @RequestBody OrderItemDTO orderItemDTO) {
        return ResponseEntity.ok(orderItemService.updateOrderItem(id, orderItemDTO));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
        orderItemService.deleteOrderItem(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public OrderItemEntity createOrderItem(OrderItemEntity orderItem) {
        BookEntity book = bookService.findBookById(orderItem.getBook().getId())
                                     .orElseThrow(() -> new RuntimeException("Book not found"));

        orderItem.setBook(book);

        return orderItemService.saveOrderItem(orderItem);
    }

    private OrderItemEntity convertToEntity(OrderItemDTO orderItemDTO) {
        OrderItemEntity orderItem = new OrderItemEntity();
        orderItem.setId(orderItemDTO.getId());
        orderItem.setQuantity(orderItemDTO.getQuantity());
        orderItem.setPrice(orderItemDTO.getPrice());

        // Set BookEntity
        BookEntity book = new BookEntity();
        book.setId(orderItemDTO.getBookId());
        orderItem.setBook(book);

        return orderItem;
    }
}
