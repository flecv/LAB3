package com.example.demo.Services.Classes.DirectorClass;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@EnableAutoConfiguration
public class Director {
    @Id
    private UUID id;
    private String name;
    private int woodAmount;
    private int balance;

    public Director() {
    }

    public Director(String name, int woodAmount, int balance) {
        this.name = name;
        id=UUID.randomUUID();
        this.woodAmount=woodAmount;
        this.balance=balance;
    }

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
        return "Director name is "+name+". Wood amount is "+woodAmount+". Balance is "+balance+".";
    }
}
