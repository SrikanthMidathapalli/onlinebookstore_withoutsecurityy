package com.bookstore.onlinebookstore.service.impl;

import com.bookstore.onlinebookstore.dto.OrderDTO;
import com.bookstore.onlinebookstore.dto.OrderItemDTO;
import com.bookstore.onlinebookstore.model.OrderEntity;
import com.bookstore.onlinebookstore.model.OrderItemEntity;
import com.bookstore.onlinebookstore.model.UserEntity;
import com.bookstore.onlinebookstore.repository.OrderRepository;
import com.bookstore.onlinebookstore.repository.UserRepository;
import com.bookstore.onlinebookstore.repository.OrderItemRepository;
import com.bookstore.onlinebookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public List<OrderEntity> findAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<OrderEntity> findOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<OrderEntity> findOrdersByUserId(Long userId) {
        return orderRepository.findByUser_Id(userId);
    }

    @Override
    public OrderEntity saveOrder(OrderEntity order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public OrderEntity updateOrder(Long id, OrderDTO orderDTO) {
        OrderEntity order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        UserEntity user = userRepository.findById(orderDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));

        List<OrderItemEntity> orderItems = orderDTO.getOrderItems().stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());

        order.setUser(user);
        order.setOrderDate(orderDTO.getOrderDate());
        order.setTotalAmount(orderDTO.getTotalAmount());
        order.setOrderItems(orderItems);

        return orderRepository.save(order);
    }

    private OrderDTO convertToDTO(OrderEntity order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setUserId(order.getUser().getId());
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setTotalAmount(order.getTotalAmount());
        orderDTO.setOrderItems(order.getOrderItems().stream().map(this::convertToDTO).collect(Collectors.toList()));
        return orderDTO;
    }

    private OrderEntity convertToEntity(OrderDTO orderDTO) {
        OrderEntity order = new OrderEntity();
        order.setId(orderDTO.getId());
        order.setOrderDate(orderDTO.getOrderDate());
        order.setTotalAmount(orderDTO.getTotalAmount());
        order.setOrderItems(orderDTO.getOrderItems().stream().map(this::convertToEntity).collect(Collectors.toList()));
        return order;
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
