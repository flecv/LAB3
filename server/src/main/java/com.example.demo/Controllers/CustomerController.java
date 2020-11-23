package com.example.demo.Controllers;

import com.example.demo.Services.Classes.CustomerClass.Customer;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController
{
    private final RestTemplate template = new RestTemplate();
    private final String address = "http://localhost:8082/customer/";

    @PostMapping
    public ResponseEntity<Customer> create(@RequestParam String name, @RequestParam int age, @RequestParam int request)
    {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(address).
                queryParam("name", name).
                queryParam("age", age).
                queryParam("request", request);
        HttpEntity<Customer> response = template.exchange(builder.toUriString(), HttpMethod.POST, null, Customer.class);
        return ResponseEntity.ok(response.getBody());}
    /*@GetMapping
    public ResponseEntity<List<Customer>> report(@RequestParam UUID customerId)
    {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(address).
                queryParam("id", customerId);
        HttpEntity<List<Customer>> response = template.exchange(builder.toUriString(), HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Customer>>() {
                });
        return ResponseEntity.ok(response.getBody());
    }*/
    @GetMapping
    public ResponseEntity<Customer> getCustomer(@RequestParam String customerId)
    {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(address).
                queryParam("id", customerId);
        HttpEntity<Customer> response = template.exchange(builder.toUriString(), HttpMethod.GET, null, Customer.class);
        return ResponseEntity.ok(response.getBody());
    }
}
