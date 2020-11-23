package com.example.demo.MainClasses.Order;

import com.example.demo.MainClasses.Customer.Customer;
import com.example.demo.MainClasses.Director.Director;

import java.util.UUID;

public class Order {
    private UUID orderId;
    private int request;
    private String customerId;
    private String directorName;
    private OrderStatus status = OrderStatus.isPreparing;

    public Order()
    {

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

    public void setDirectorId(String directorName) {
        this.directorName = directorName;
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
        return "Customer request is "+request+" . Order id is "+orderId;
    }


}
