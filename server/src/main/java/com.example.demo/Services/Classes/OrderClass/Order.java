package com.example.demo.Services.Classes.OrderClass;

import java.util.UUID;


public class Order
{
    private String orderId;
    private int request;
    private String customerId;
    private String directorName;
    private OrderStatus status;

    public Order() {
    }

    public Order(int request, String customerId) {
        this.orderId = UUID.randomUUID().toString();
        this.customerId = customerId;
        this.request = request;
        this.status = OrderStatus.isPreparing;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getRequest() {
        return request;
    }

    public void setRequest(int request) {
        this.request = request;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
