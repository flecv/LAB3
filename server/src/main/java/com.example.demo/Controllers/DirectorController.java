package com.example.demo.Controllers;

import com.example.demo.Services.Classes.DirectorClass.Director;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "/director")
public class DirectorController {
    private final RestTemplate template = new RestTemplate();
    private final String address = "http://localhost:8084/director/";

    @PostMapping()
    public ResponseEntity<Director> create(@RequestParam String name, @RequestParam int woodAmount, @RequestParam int balance) {


        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(address).
                queryParam("name", name).
                queryParam("woodAmount", woodAmount).
                queryParam("balance", balance);
        HttpEntity<Director> response = template.exchange(builder.toUriString(), HttpMethod.POST, null, Director.class);

        return ResponseEntity.ok(response.getBody());
    }

    @GetMapping(value = "/get")
    public ResponseEntity<String> report() {

        String address = "http://localhost:8084/director/get";
        /*UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(address).
                queryParam("directorName", directorName);*/
        HttpEntity<String> response = template.exchange(address, HttpMethod.GET, null, String.class);
        return ResponseEntity.ok(response.getBody());
    }

    @GetMapping(value="/report")
    public ResponseEntity<String> info()
    {
        String address = "http://localhost:8084/director/report";
        System.out.println("In mediator director server before response");
        HttpEntity<String> response = template.exchange(address, HttpMethod.GET, null, String.class);
        System.out.println("In mediator director server after response");
        return ResponseEntity.ok(response.getBody());
    }
}

