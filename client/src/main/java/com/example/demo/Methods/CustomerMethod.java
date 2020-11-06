package com.example.demo.Methods;

import com.example.demo.MainClasses.Customer.Customer;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

public class CustomerMethod {
    final String address = "http://172.17.0.1:8082/customer/";
    RestTemplate restTemp = new RestTemplate();
    public void createCustomer()
    {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(address).
                queryParam("name", "Vigor").
                queryParam("age", 30).
                queryParam("request", 100);

        System.out.println("Create new customer");
        HttpEntity<Customer> response = restTemp.exchange(builder.toUriString(), HttpMethod.POST, null, Customer.class);
        System.out.println(response.getBody());

        System.out.println("Create new customer");
        builder = UriComponentsBuilder.fromHttpUrl(address).
                queryParam("name", "Savana").
                queryParam("age", 50).
                queryParam("request", 50);
        response = restTemp.exchange(builder.toUriString(),
                HttpMethod.POST, null, Customer.class);
        System.out.println(response.getBody());
    }

    public List<Customer> customerReport(UUID customerId)
    {

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(address).
                queryParam("customerId", customerId);
        HttpEntity<List<Customer>> response = restTemp.exchange(builder.toUriString(),
                HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Customer>>() {
                });
        System.out.println(response.getBody());
        return response.getBody();
    }
}
