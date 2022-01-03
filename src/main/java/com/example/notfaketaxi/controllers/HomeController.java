package com.example.notfaketaxi.controllers;

import com.example.notfaketaxi.entities.Client;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping("/test")
    public String test(@RequestAttribute Client client)
    {
        return String.format("Hello, %s!", client.getUsername());
    }



}
