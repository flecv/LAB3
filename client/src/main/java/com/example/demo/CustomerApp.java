package com.example.demo;

import com.example.demo.MainClasses.Customer.Customer;
import com.example.demo.MainClasses.Director.Director;
import com.example.demo.MainClasses.Order.Order;
import com.example.demo.Methods.CustomerMethod;
import com.example.demo.Methods.DirectorMethod;
import com.example.demo.Methods.OrderMethod;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class CustomerApp
{
    public  static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        CustomerMethod cusMeth = new CustomerMethod();
        DirectorMethod dirMeth = new DirectorMethod();
        OrderMethod orderMeth = new OrderMethod();

        dirMeth.createDirector();
        Customer customer = cusMeth.createCustomer();
        //List<Customer> list = cusMeth.customerReport(null);


        System.out.println(customer);
        System.out.println("Before order creating");
        System.out.println("Please, enter ID of director you`re working at:");
        String directorId = in.nextLine();
        System.out.println("Please, enter ID of customer you`re working at:");
        String customerId1 = in.nextLine();
        /*dirMeth.directorReport();
        System.out.println(list.get(0).getCustomerId()+" "+ list.get(0).getRequest() +" "+ directorId);*/
        Order order1 = orderMeth.createOrder(customerId1, customer.getRequest(), directorId);
        /*Order order2 = orderMeth.createOrder(list.get(1).getCustomerId(), list.get(1).getRequest(), directorId);*/

        System.out.println(order1);
       /* System.out.println(order2);*/
        System.out.println("Please, enter ID of order you`re working at:");
        String orderId1 = in.nextLine();
        orderMeth.completeOrder(orderId1, customerId1, directorId);
        dirMeth.directorReport();
        /*orderMeth.completeOrder(order2.getOrderId());*/
        /*dirMeth.directorReport();*/
        //System.out.println("Director wood amount is "+director.getWoodAmount());



    }
}
