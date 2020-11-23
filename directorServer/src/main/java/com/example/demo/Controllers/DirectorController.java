package com.example.demo.Controllers;

import com.example.demo.Services.Classes.DirectorClass.Director;
import com.example.demo.Services.ServiceRealization.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/director")
public class DirectorController {
    private final DirectorService service;

    @Autowired
    public DirectorController(DirectorService service)
    {
        this.service=service;
    }
    @PostMapping
    public ResponseEntity<Director> create(@RequestParam String name, @RequestParam int woodAmount, @RequestParam int balance)
    {
        return ResponseEntity.ok(service.createDirector(name, woodAmount, balance));
    }
    /*@GetMapping
    public ResponseEntity<String> report()
    {
        System.out.println("Director get mapping");
        return ResponseEntity.ok(service.directorReport());
    }*/

    @GetMapping(value = "get")
    public ResponseEntity<Director> get(@RequestParam String directorName) {
        return ResponseEntity.ok(service.getDirector(directorName));
    }
    @GetMapping(value = "report")
    public ResponseEntity<String> report()
    {
        System.out.println("In director server value report");
        return ResponseEntity.ok(service.directorReport());
    }
    @PutMapping
    public ResponseEntity<Director> updateInfo(@RequestParam int request, @RequestParam int status)
    {
        return ResponseEntity.ok(service.updateDirectorInformation(request, status));
    }
}

