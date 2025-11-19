package com.example.webshop.service;

import com.example.webshop.model.Product;
import com.example.webshop.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void getAllProducts_ShouldReturnAllProducts() {
        
        Product product = new Product(1L, "iPhone 14", "Latest Apple smartphone", 999.99, "https://example.com/iphone14.jpg", 50);
        when(productRepository.findAll()).thenReturn(Arrays.asList(product));

        List<Product> result = productService.getAllProducts();

        assertEquals(1, result.size());
        assertEquals("iPhone 14", result.get(0).getName());
    }

    @Test
    void getProductById_WithValidId_ShouldReturnProduct() {
        
        Product product = new Product(1L, "iPhone 14", "Latest Apple smartphone", 999.99, "https://example.com/iphone14.jpg", 50);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        
        Optional<Product> result = productService.getProductById(1L);
        
        assertTrue(result.isPresent());
        assertEquals("iPhone 14", result.get().getName());
    }
}