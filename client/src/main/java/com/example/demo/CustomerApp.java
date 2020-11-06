package com.example.demo;

import com.example.demo.MainClasses.Customer.Customer;
import com.example.demo.MainClasses.Order.Order;
import com.example.demo.Methods.CustomerMethod;
import com.example.demo.Methods.DirectorMethod;
import com.example.demo.Methods.OrderMethod;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class CustomerApp
{
    public  static void main(String[] args)
    {
        CustomerMethod cusMeth = new CustomerMethod();
        DirectorMethod dirMeth = new DirectorMethod();
        OrderMethod orderMeth = new OrderMethod();

        cusMeth.createCustomer();
        dirMeth.createDirector();
        List<Customer> list = cusMeth.customerReport(null);
        Order order1 = orderMeth.createOrder(list.get(0).getCustomerId(), 100);
        Order order2 = orderMeth.createOrder(list.get(1).getCustomerId(), 75);

        System.out.println(order1);
        System.out.println(order2);
        orderMeth.completeOrder(order1.getOrderId());
        dirMeth.directorReport();
        orderMeth.completeOrder(order2.getOrderId());
        dirMeth.directorReport();
        //System.out.println("Director wood amount is "+director.getWoodAmount());



    }
}
