package com.example.demo.Services.Classes.CustomerClass;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;


@EnableAutoConfiguration
public class Customer {

    private String customerId;
    private String name;
    private int age;
    private int request;
    public Customer() {
    }

    public Customer(String name, int age, int request) {
        this.name = name;
        this.age = age;
        this.request = request;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getRequest() {
        return request;
    }

    public void setRequest(int request) {
        this.request = request;
    }

    public String getCustomerId() {
        return customerId;
    }


    @Override
    public String toString()
    {
        return "Customer`s name is " + name + ". His/her age is "+age+". His/her request is "+request+". Customer Id is "+customerId;
    }
}
