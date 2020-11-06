package com.example.demo.Controllers;


import com.example.demo.Services.Classes.OrderClass.Order;
import com.example.demo.Services.ServiceRealisation.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping(value = "/order")
public class OrderController
{
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService)
    {
        this.orderService=orderService;
    }


    @PostMapping
    public ResponseEntity<Order> create(@RequestParam int customerRequest, @RequestParam UUID customerId)
    {
        return ResponseEntity.ok(orderService.createOrder(customerRequest,customerId));
    }
    @GetMapping
    public ResponseEntity<List<Order>> get(@RequestParam UUID orderId) {
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }
    /*@GetMapping
    public ResponseEntity<Boolean> completing(@RequestParam String customerName, @RequestParam String directorName, @RequestParam int customerAge, @RequestParam int customerRequest)
    {
        Customer customer = customerService.createCustomer(customerName, customerAge, customerRequest);
        Order order = orderService.decisionMaking()
        orderService.setCustomer(order, customer);
        updateInformation(order, director, customer);

        return ResponseEntity.ok(orderService.checkIfIsCompleted(order));
    }
*/
}
