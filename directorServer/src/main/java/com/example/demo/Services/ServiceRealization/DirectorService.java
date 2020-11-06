package com.example.demo.Services.ServiceRealization;

import com.example.demo.Services.Classes.DirectorClass.Director;
import com.example.demo.Services.Classes.Repository.DirectorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class DirectorService
{
    private final DirectorRepo repo;

    @Autowired
    public DirectorService(DirectorRepo repo)
    {
        this.repo = repo;
    }
    public Director createDirector(String name, int woodAmount, int balance)
    {
        if (repo.getDirectorByName(name)!=null)
            return repo.getDirectorByName(name);
        else
        {
            Director director = new Director(name, woodAmount, balance);
            System.out.println("woodAmount is " + woodAmount+" balance is " + balance);
            repo.save(director);
            return director;
        }
    }

    public void balancing(int request)
    {
        Director director = repo.getDirectorByName("Konrad");
        int additionalBalance = request * 10;
        int balance = director.getBalance() + additionalBalance;
        director.setBalance(balance);
        repo.save(director);
    }
    public void woodControl(int newSupply)
    {
        Director director = repo.getDirectorByName("Konrad");
        Random random = new Random();
        int sup = random.nextInt(newSupply)+((int)newSupply/2);
        System.out.println("We need new supply. Leader will deliver "+sup+" amount of wood to us");
        director.setWoodAmount(director.getWoodAmount() + sup);
        repo.save(director);
    }


    public Director updateDirectorInformation(int request)
    {
        Director director = repo.getDirectorByName("Konrad");
        balancing(request);
        repo.save(director);
        return director;
    }
    public String directorReport()
    {
        Director director = repo.getDirectorByName("Konrad");
        String report = director.toString();
        return report;
    }
}
