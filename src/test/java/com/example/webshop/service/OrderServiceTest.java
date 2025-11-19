package com.example.webshop.service;

import com.example.webshop.dto.OrderRequest;
import com.example.webshop.dto.OrderResponse;
import com.example.webshop.model.CustomerInfo;
import com.example.webshop.model.Order;
import com.example.webshop.model.OrderItem;
import com.example.webshop.model.Product;
import com.example.webshop.repository.OrderRepository;
import com.example.webshop.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void createOrder_WithValidRequest_ShouldReturnOrderResponse() {
        Product product = new Product(1L, "iPhone 14", "Latest Apple smartphone", 999.99, "https://example.com/iphone14.jpg", 50);
        CustomerInfo customerInfo = new CustomerInfo("John Doe", "123 Main St", "john@example.com");
        OrderItem orderItem = new OrderItem(1L, 1, 0);
        OrderRequest orderRequest = new OrderRequest(customerInfo, Arrays.asList(orderItem));

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> {
            Order order = invocation.getArgument(0);
            order.setId("ORD-1");
            return order;
        });

        OrderResponse response = orderService.createOrder(orderRequest);

        assertNotNull(response);
        assertEquals("ORD-1", response.getOrderId());
        assertEquals(999.99, response.getTotalAmount(), 0.01);
    }
}