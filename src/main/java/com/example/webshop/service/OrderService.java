package com.example.webshop.service;

import com.example.webshop.dto.OrderRequest;
import com.example.webshop.dto.OrderResponse;
import com.example.webshop.model.Order;
import com.example.webshop.model.OrderItem;
import com.example.webshop.model.Product;
import com.example.webshop.repository.OrderRepository;
import com.example.webshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    // Hämta alla ordrar
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Hämta specifik order
    public Optional<Order> getOrderById(String id) {
        return orderRepository.findById(id);
    }


    public OrderResponse createOrder(OrderRequest orderRequest) {
        validateOrderRequest(orderRequest);
        
        List<OrderItem> validatedItems = new ArrayList<>();
        double totalAmount = 0.0;

        for (OrderItem item : orderRequest.getItems()) {
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + item.getProductId()));

            if (item.getQuantity() <= 0) {
                throw new IllegalArgumentException("Quantity must be positive for product: " + product.getName());
            }

            OrderItem validatedItem = new OrderItem(
                item.getProductId(),
                item.getQuantity(),
                product.getPrice()
            );
            validatedItems.add(validatedItem);
            
            totalAmount += product.getPrice() * item.getQuantity();
        }

        Order order = new Order();
        order.setCustomerInfo(orderRequest.getCustomerInfo());
        order.setItems(validatedItems);
        order.setTotalAmount(totalAmount);
        order.setOrderDate(LocalDateTime.now());

        Order savedOrder = orderRepository.save(order);

        return new OrderResponse(
            savedOrder.getId(),
            "Order created successfully",
            savedOrder.getTotalAmount()
        );
    }

    private void validateOrderRequest(OrderRequest orderRequest) {
        if (orderRequest.getCustomerInfo() == null) {
            throw new IllegalArgumentException("Customer information is required");
        }
        
        if (orderRequest.getCustomerInfo().getName() == null || orderRequest.getCustomerInfo().getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name is required");
        }
        
        if (orderRequest.getCustomerInfo().getAddress() == null || orderRequest.getCustomerInfo().getAddress().trim().isEmpty()) {
            throw new IllegalArgumentException("Customer address is required");
        }
        
        if (orderRequest.getItems() == null || orderRequest.getItems().isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one item");
        }
    }
}