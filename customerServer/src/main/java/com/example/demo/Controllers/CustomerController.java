package com.example.demo.Controllers;

import com.example.demo.Services.Classes.CustomerClass.Customer;
import com.example.demo.Services.ServiceRealization.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController
{
    private final CustomerService service;

    @Autowired
    public CustomerController(CustomerService service)
    {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Customer> create(@RequestParam String name, @RequestParam int age, @RequestParam int request)
    {
        if (service.getCustomerByName(name) != null)
            return ResponseEntity.ok(service.getCustomerByName(name));
        else
            return ResponseEntity.ok(service.createCustomer(name,age, request));
    }
    @GetMapping
    public ResponseEntity<List<Customer>> report()
    {
        return ResponseEntity.ok(service.customerReport());
    }
}
