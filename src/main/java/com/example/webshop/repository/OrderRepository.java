package com.example.webshop.repository;

import com.example.webshop.model.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class OrderRepository {
    private final List<Order> orders = new ArrayList<>();
    private final AtomicLong nextId = new AtomicLong(1);

    public Order save(Order order) {
        order.setId("ORD-" + nextId.getAndIncrement());
        orders.add(order);
        return order;
    }

    public List<Order> findAll() {
        return new ArrayList<>(orders);
    }

    public Optional<Order> findById(String id) {
        return orders.stream()
                .filter(order -> order.getId().equals(id))
                .findFirst();
    }
}