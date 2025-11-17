package com.example.webshop.repository;

import com.example.webshop.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    private final List<Product> products = new ArrayList<>();
    private long nextId = 1;

    @Autowired
    private RestTemplate restTemplate;

    public ProductRepository() {
        initializeSampleProducts();
    }

    @jakarta.annotation.PostConstruct
    public void init() {
        loadProductsFromDummyJSON();
    }

    private void initializeSampleProducts() {
        products.add(new Product(nextId++, "iPhone 14", "Latest Apple smartphone", 999.99, "https://example.com/iphone14.jpg", 50));
        products.add(new Product(nextId++, "Samsung Galaxy S23", "Android flagship phone", 899.99, "https://example.com/galaxyS23.jpg", 30));
        products.add(new Product(nextId++, "MacBook Pro", "Apple laptop for professionals", 1999.99, "https://example.com/macbookpro.jpg", 20));
        products.add(new Product(nextId++, "Sony Headphones", "Wireless noise-canceling headphones", 299.99, "https://example.com/sonyheadphones.jpg", 100));
        products.add(new Product(nextId++, "iPad Air", "Apple tablet computer", 599.99, "https://example.com/ipadair.jpg", 40));
    }

    private void loadProductsFromDummyJSON() {
        try {
            String url = "https://dummyjson.com/products";
            DummyJsonResponse response = restTemplate.getForObject(url, DummyJsonResponse.class);
            
            if (response != null && response.getProducts() != null) {
                for (DummyProduct dummyProduct : response.getProducts()) {
                    Product product = new Product(
                        nextId++,
                        dummyProduct.getTitle(),
                        dummyProduct.getDescription(),
                        dummyProduct.getPrice(),
                        dummyProduct.getThumbnail(),
                        dummyProduct.getStock()
                    );
                    products.add(product);
                }
                System.out.println("Successfully loaded " + response.getProducts().size() + " products from DummyJSON API");
            }
        } catch (Exception e) {
            System.out.println("Warning: Could not load products from DummyJSON API. Using local products only. Error: " + e.getMessage());
        }
    }

    public List<Product> findAll() {
        return new ArrayList<>(products);
    }

    public Optional<Product> findById(Long id) {
        return products.stream()
                .filter(product -> product.getId() == id)
                .findFirst();
    }

    // Inner classes för DummyJSON mapping
    private static class DummyJsonResponse {
        private List<DummyProduct> products;
        
        public List<DummyProduct> getProducts() { return products; }
        public void setProducts(List<DummyProduct> products) { this.products = products; }
    }

    private static class DummyProduct {
        private String title;
        private String description;
        private double price;
        private String thumbnail;
        private int stock;
        
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        
        public double getPrice() { return price; }
        public void setPrice(double price) { this.price = price; }
        
        public String getThumbnail() { return thumbnail; }
        public void setThumbnail(String thumbnail) { this.thumbnail = thumbnail; }
        
        public int getStock() { return stock; }
        public void setStock(int stock) { this.stock = stock; }
    }
}