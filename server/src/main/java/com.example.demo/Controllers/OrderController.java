package com.example.demo.Controllers;


import com.example.demo.Services.Classes.DirectorClass.Director;
import com.example.demo.Services.Classes.OrderClass.Order;
import com.example.demo.Services.Classes.CustomerClass.Customer;
import com.example.demo.Services.Classes.OrderClass.OrderStatus;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Random;
import java.util.UUID;


@RestController
@RequestMapping(value = "/order")
public class OrderController
{
    private final RestTemplate template = new RestTemplate();

    private Director updateDirectorInformation(int request, int balance)
    {
        String address = "http://directorserver:8084/director/";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(address).
                queryParam("request", request).
                queryParam("balance", balance);
        HttpEntity<Director> response = template.exchange(builder.toUriString(), HttpMethod.PUT, null, Director.class);
        return response.getBody();
    }
    private Customer getOrderCustomer(UUID customerId) {

        String address = "http://clientserver:8082/customer/";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(address).
                queryParam("id", customerId);

        HttpEntity<Customer> response = template.exchange(builder.toUriString(), HttpMethod.GET, null,
                Customer.class);
        return response.getBody();
    }
    private Director getOrderDirector(UUID directorId) {

        String address = "http://directorserver:8084/director/";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(address).
                queryParam("id", directorId);

        HttpEntity<Director> response = template.exchange(builder.toUriString(), HttpMethod.GET, null,
                Director.class);
        return response.getBody();
    }
    public Order getOrder(UUID orderId) {

        String address = "http://orderserver:8087/order/";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(address).
                queryParam("orderId", orderId);

        HttpEntity<List<Order>> response = template.exchange(builder.toUriString(), HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {
                });
        List<Order> list = response.getBody();
        if (list.isEmpty())
            return null;
        else
            return list.get(0);

    }

    /*public void decisionMaking(Order order, int customerRequest, int woodAmount)
    {
        String address =
        Order order = getOrder(orderId);
        if (order == null)
            return ResponseEntity.ok(Boolean.FALSE);
        System.out.println(order);
        Director director = get
        if (director.getWoodAmount() < customer.getRequest())
        {
            System.out.println("In decision making, rejected version");
            order.setStatus(OrderStatus.isRejected);
        }
        else
        {
            System.out.println("In decision making, accepted version");
            order.setStatus(OrderStatus.isAccepted);
        }
    }*/




    @PostMapping
    public ResponseEntity<Order> create(@RequestParam int request, @RequestParam UUID customerId)
    {
        String address = "http://orderserver:8087/order/";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(address).
                queryParam("request", request).
                queryParam("customerId", customerId);
        HttpEntity<Order> orderResponse = template.exchange(builder.toUriString(), HttpMethod.POST, null, Order.class);
        return ResponseEntity.ok(orderResponse.getBody());
    }

    @PutMapping
    private ResponseEntity<Boolean> updateInformation(@RequestParam UUID orderId)
    {
        Order order = getOrder(orderId);
        if (order == null)
            return ResponseEntity.ok(Boolean.FALSE);
        System.out.println(order);
        Customer customer = getOrderCustomer(order.getCustomerId());
        Director director = getOrderDirector(order.getDirectorId());
        if (director.getWoodAmount() < customer.getRequest())
        {
            System.out.println("In decision making, rejected version");
            order.setStatus(OrderStatus.isRejected);

        }
        else
        {
            System.out.println("In decision making, accepted version");
            order.setStatus(OrderStatus.isAccepted);
        }
        String address = "http://orderserver:8087/order/";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(address).
                queryParam("orderId", order.getOrderId());
        HttpEntity<Order> response = template.exchange(builder.toUriString(), HttpMethod.PUT, null, Order.class);
        order = response.getBody();
        if(order.getStatus()==OrderStatus.isAccepted)
        {
            System.out.println("We can sell you this amount of wood");
            director.setWoodAmount(director.getWoodAmount() - customer.getRequest());
            director.setBalance(director.getBalance() + 10 * customer.getRequest());

            order.setDirectorId(director.getDirectorId());
            updateDirectorInformation(director.getWoodAmount(), director.getBalance());
            order.setStatus(OrderStatus.isAccepted);
            return ResponseEntity.ok(order.getStatus() == OrderStatus.isAccepted);
        }
        else
        {
            System.out.println("We can not sell you this amount of wood.");
            order.setStatus(OrderStatus.isRejected);
            System.out.println(customer.toString());
            System.out.println(director.toString());
            Random random = new Random();
            int sup = random.nextInt(customer.getRequest())+((int)customer.getRequest()/2);
            System.out.println("We need new supply. Leader will deliver "+sup+" amount of wood to us");
            director.setWoodAmount(director.getWoodAmount() + sup);
            order.setDirectorId(director.getDirectorId());
            updateDirectorInformation(director.getWoodAmount(), director.getBalance());
            System.out.println(director.toString());
            return ResponseEntity.ok(order.getStatus() == OrderStatus.isRejected);
        }
    }
    /*public ResponseEntity<Boolean> processing(@RequestParam String customerName, @RequestParam int customerAge, @RequestParam int customerRequest)
    {
        Customer customer = customerService.createCustomer(customerName,customerAge,customerRequest);
        Director director = directorService.getDirector();
        Order order = orderService.decisionMaking(director, customer);

        orderService.setCustomer(order, customer);
        updateInformation(order,customer.getName());
        return ResponseEntity.ok(orderService.checkIfIsCompleted(order));
    }/*
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
