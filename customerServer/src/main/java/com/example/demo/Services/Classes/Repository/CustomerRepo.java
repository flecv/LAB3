package com.example.demo.Services.Classes.Repository;

import com.example.demo.Services.Classes.CustomerClass.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepo extends CrudRepository<Customer, String>
{
    Customer getCustomerByName(String name);
}