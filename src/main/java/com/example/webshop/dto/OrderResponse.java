package com.example.webshop.dto;

public class OrderResponse {
    private String orderId;
    private String message;
    private double totalAmount;

    public OrderResponse() {}

    public OrderResponse(String orderId, String message, double totalAmount) {
        this.orderId = orderId;
        this.message = message;
        this.totalAmount = totalAmount;
    }

    // Getters och Setters
    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
}