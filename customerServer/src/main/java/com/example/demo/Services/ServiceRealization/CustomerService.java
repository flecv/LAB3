package com.example.demo.Services.ServiceRealization;

import com.example.demo.Services.Classes.CustomerClass.Customer;
import com.example.demo.Services.Classes.Repository.CustomerRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepo repo;
    public CustomerService(CustomerRepo repo)
    {
        this.repo = repo;
    }
    public Customer createCustomer(String name, int age, int request)
    {
        Customer customer = new Customer(name, age, request);
        repo.save(customer);
        return customer;
    }

    public Customer getCustomerByName(String name)
    {
        return repo.getCustomerByName(name);
    }
    public List<Customer> customerReport()
    {
        return (List<Customer>) repo.findAll();
    }

}