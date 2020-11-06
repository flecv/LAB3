package com.example.demo.Methods;

import com.example.demo.MainClasses.Customer.Customer;
import com.example.demo.MainClasses.Director.Director;
import com.example.demo.MainClasses.MapModule;
import com.example.demo.MainClasses.Order.Order;
import com.example.demo.MainClasses.Order.OrderStatus;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

public class OrderMethod {
    final String address = "http://172.17.0.1:8082/order/";
    RestTemplate restTemp = new RestTemplate();
    public Order createOrder(UUID customerId,int request)
    {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(address).
                queryParam("request", request).
                queryParam("customerId", customerId);

        HttpEntity<Order> response = restTemp.exchange(builder.toUriString(),
                HttpMethod.POST, null, Order.class);
        return response.getBody();
    }
    public void completeOrder(UUID orderId) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(address).
                queryParam("orderId", orderId);
        HttpEntity<Boolean> response = restTemp.exchange(builder.toUriString(), HttpMethod.PUT, null, Boolean.class);
        if (response.getBody() == Boolean.TRUE)
            System.out.println("We can complete this order");
        else
            System.out.println("We can not complete this order");
    }

}
