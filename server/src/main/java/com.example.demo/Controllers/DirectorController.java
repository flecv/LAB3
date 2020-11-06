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
    private final String address = "http://directorserver:8084/director/";

    @PostMapping()
    public ResponseEntity<Director> create() {

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(address).
                queryParam("name", "Konrad");
        HttpEntity<Director> response = template.exchange(builder.toUriString(), HttpMethod.POST, null, Director.class);
        return ResponseEntity.ok(response.getBody());
    }

    @GetMapping
    public ResponseEntity<String> report() {

        HttpEntity<String> response = template.exchange(address, HttpMethod.GET, null, String.class);
        return ResponseEntity.ok(response.getBody());
    }
}

