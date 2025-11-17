package com.example.webshop.dto;

import com.example.webshop.model.CustomerInfo;
import com.example.webshop.model.OrderItem;
import java.util.List;

public class OrderRequest {
    private CustomerInfo customerInfo;
    private List<OrderItem> items;

    public OrderRequest() {}

    public OrderRequest(CustomerInfo customerInfo, List<OrderItem> items) {
        this.customerInfo = customerInfo;
        this.items = items;
    }

    // Getters och Setters
    public CustomerInfo getCustomerInfo() { return customerInfo; }
    public void setCustomerInfo(CustomerInfo customerInfo) { this.customerInfo = customerInfo; }
    
    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }
}