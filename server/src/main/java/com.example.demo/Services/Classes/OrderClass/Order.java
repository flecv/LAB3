package com.example.demo.Services.Classes.OrderClass;

import java.util.UUID;


public class Order
{
    private UUID orderId;
    private int request;
    private UUID customerId;
    private UUID directorId;
    private OrderStatus status;

    public Order() {
    }

    public Order(int request, UUID customerId) {
        this.orderId = UUID.randomUUID();
        this.customerId = customerId;
        this.request = request;
        this.status = OrderStatus.isPreparing;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public int getRequest() {
        return request;
    }

    public void setRequest(int request) {
        this.request = request;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public UUID getDirectorId() {
        return directorId;
    }

    public void setDirectorId(UUID directorId) {
        this.directorId = directorId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
