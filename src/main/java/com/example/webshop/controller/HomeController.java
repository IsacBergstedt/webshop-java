package com.example.webshop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public Map<String, String> home() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Welcome to the Webshop API");
        response.put("endpoints", "Available endpoints: GET /api/products, GET /api/products/{id}, POST /api/orders");
        return response;
    }
}