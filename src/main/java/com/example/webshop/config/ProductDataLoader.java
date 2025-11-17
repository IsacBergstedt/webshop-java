package com.example.webshop.config;

import com.example.webshop.model.Product;
import com.example.webshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class ProductDataLoader {

    @Autowired
    private ProductRepository productRepository;

    @PostConstruct
    public void loadData() {
        // Data laddas redan i ProductRepository konstruktorn
        System.out.println("Sample products loaded successfully");
    }
}