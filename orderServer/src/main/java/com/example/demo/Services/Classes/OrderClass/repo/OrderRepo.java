package com.example.demo.Services.Classes.OrderClass.repo;

import com.example.demo.Services.Classes.OrderClass.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepo extends CrudRepository<Order, String> {
    Order getByOrderId(UUID id);
}
