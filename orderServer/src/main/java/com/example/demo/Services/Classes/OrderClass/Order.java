package com.example.demo.Services.Classes.OrderClass;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@EnableAutoConfiguration
public class Order
{

    @Id
    private int request;
    private UUID orderId;
    private UUID customerId;
    private UUID directorId;
    private OrderStatus status;

    public Order() {
    }

    public Order(int request, UUID customerId) {
        this.orderId=UUID.randomUUID();
        this.customerId=customerId;
        this.request=request;
        this.status = OrderStatus.isPreparing;
    }

    public int getRequest() {
        return request;
    }

    public void setRequest(int request) {
        this.request = request;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
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
    @Override
    public String toString()
    {
        return "Order request " + request +". Order customerId "+customerId+". Order directorId "+directorId+". Order orderId "+orderId+". Order status "+status;
    }
}
