package com.example.demo.Services.ServiceRealization;

import com.example.demo.Services.Classes.OrderClass.Order;
import com.example.demo.Services.Classes.OrderClass.OrderStatus;
import com.example.demo.Services.Classes.OrderClass.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class OrderService {

    private final OrderRepo repo;

    @Autowired
    public OrderService(OrderRepo repo)
    {
        this.repo=repo;
    }
    public Order createOrder(int customerRequest, UUID customerId)
    {
        Order order = new Order(customerRequest, customerId);
        repo.save(order);
        return order;
    }

    public List<Order> getOrderById(UUID orderId)
    {
        return repo.getByOrderId(orderId);
    }


    /*public Order decisionMaking(UUID orderId)
    {
        List<Order> list = getOrderById(orderId);
        if(list.isEmpty())
            return null;
        Order order = list.get(0);
        if (director.getWoodAmount() < customer.getRequest())
        {
            System.out.println("In decision making, rejected version");
            order.setStatus(OrderStatus.isRejected);

            return order;
        }
        else
        {
            System.out.println("In decision making, accepted version");
            order.setStatus(OrderStatus.isAccepted);
            return order;
        }
    }*/

}
