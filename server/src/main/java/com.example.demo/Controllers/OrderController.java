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

    /*private Director updateDirectorInformation(String directorName, int request, int balance)
    {
        String address = "http://localhost:8084/director/";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(address).
                queryParam("directorName", directorName).
                queryParam("request", request).
                queryParam("balance", balance);
        HttpEntity<Director> response = template.exchange(builder.toUriString(), HttpMethod.PUT, null, Director.class);
        return response.getBody();
    }*/
    private Customer getOrderCustomer(String customerId) {

        String address = "http://customer-ser:8082/customer/";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(address).
                queryParam("customerId", customerId);

        HttpEntity<Customer> response = template.exchange(builder.toUriString(), HttpMethod.GET, null,
                Customer.class);
        return response.getBody();
    }
    private Director getOrderDirector(String directorName) {

        String address = "http://director-ser:8084/director/get";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(address).
                queryParam("directorName", directorName);

        HttpEntity<Director> response = template.exchange(builder.toUriString(), HttpMethod.GET, null,
                Director.class);
        return response.getBody();
    }
    public Order getOrder(UUID orderId) {

        String address = "http://order-ser:8087/order/";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(address).
                queryParam("orderId", orderId);

        HttpEntity<Order> response = template.exchange(builder.toUriString(), HttpMethod.GET, null, Order.class);
        return response.getBody();

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
    public ResponseEntity<Order> create(@RequestParam int customerRequest, @RequestParam String customerId, @RequestParam String directorName)
    {
        String address = "http://order-ser:8087/order/";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(address).
                queryParam("customerRequest", customerRequest).
                queryParam("customerId", customerId).
                queryParam("directorName", directorName);
        HttpEntity<Order> orderResponse = template.exchange(builder.toUriString(), HttpMethod.POST, null, Order.class);
        System.out.println(customerId+" "+directorName);
        return ResponseEntity.ok(orderResponse.getBody());
    }

    @PutMapping
    private ResponseEntity<Boolean> updateInformation(@RequestParam UUID orderId, @RequestParam String customerId, @RequestParam String directorName)
    {
        System.out.println("In put mapping");
        Order order = getOrder(orderId);
        System.out.println(order.toString());
        if (order == null)
            return ResponseEntity.ok(Boolean.FALSE);
        System.out.println(order);
        System.out.println("Before creation customer and director");
        Customer customer = getOrderCustomer(customerId);
        System.out.println(customer.toString());
        System.out.println("Between creations");
        Director director = getOrderDirector("Konrad");
        System.out.println("After creation customer and director");
        System.out.println(director.toString());
        System.out.println(director.getWoodAmount()+" "+customer.getRequest());
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
        String address = "http://order-ser:8087/order/";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(address).
                queryParam("orderId", order.getOrderId()).
                queryParam("status", order.getStatus());
        HttpEntity<Order> response = template.exchange(builder.toUriString(), HttpMethod.PUT, null, Order.class);
        //order = response.getBody();
        if(order.getStatus()==OrderStatus.isAccepted)
        {
            int k = 1;
            System.out.println("We can sell you this amount of wood");
            director.setWoodAmount(director.getWoodAmount() - customer.getRequest());
            director.setBalance(director.getBalance() + 10 * customer.getRequest());
            System.out.println("In accepted status before update information");
            //order.setDirectorName(director.getName());
            //updateDirectorInformation("Konrad", director.getWoodAmount(), director.getBalance());
            System.out.println("In accepted status after update information");
            System.out.println(director.toString());
            order.setStatus(OrderStatus.isAccepted);
            String address1 = "http://director-ser:8084/director/";
            UriComponentsBuilder builder1 = UriComponentsBuilder.fromHttpUrl(address1).
                    queryParam("request", order.getRequest()).
                    queryParam("status", k);
            HttpEntity<Order> response1 = template.exchange(builder1.toUriString(), HttpMethod.PUT, null, Order.class);
            return ResponseEntity.ok(order.getStatus() == OrderStatus.isAccepted);
        }
        else
        {
            int k = 2;
            System.out.println("We can not sell you this amount of wood.");
            order.setStatus(OrderStatus.isRejected);
            System.out.println(customer.toString());
            System.out.println(director.toString());
            /*Random random = new Random();
            int sup = random.nextInt(customer.getRequest())+((int)customer.getRequest()/2);
            System.out.println("We need new supply. Leader will deliver "+sup+" amount of wood to us");
            director.setWoodAmount(director.getWoodAmount() + sup);
            order.setDirectorName(director.getName());*/
            //updateDirectorInformation("Konrad", director.getWoodAmount(), director.getBalance());
            System.out.println(director.toString());
            String address1 = "http://director-ser:8084/director/";
            UriComponentsBuilder builder1 = UriComponentsBuilder.fromHttpUrl(address1).
                    queryParam("request", order.getRequest()).
                    queryParam("status", k);
            HttpEntity<Order> response1 = template.exchange(builder1.toUriString(), HttpMethod.PUT, null, Order.class);
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
