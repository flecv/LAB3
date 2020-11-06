package com.example.demo.MainClasses.Director;

import java.util.UUID;

public class Director {
    private UUID id;
    private String name;
    private int woodAmount;
    private int balance;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWoodAmount() {
        return woodAmount;
    }

    public void setWoodAmount(int woodAmount) {
        this.woodAmount = woodAmount;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString()
    {
        return "Director name is "+name+". Amount of wood is "+woodAmount+". Balance is "+balance+".";
    }
}
