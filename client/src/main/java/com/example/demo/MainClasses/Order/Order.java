package com.example.demo.MainClasses.Order;

import com.example.demo.MainClasses.Customer.Customer;
import com.example.demo.MainClasses.Director.Director;

import java.util.UUID;

public class Order {
    private UUID orderId;
    private int request;
    private OrderStatus status = OrderStatus.isPreparing;

    public Order()
    {

    }

    public Order(int request) {
        this.request = request;
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

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "Customer request is "+request;
    }


}
