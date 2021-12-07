package com.example.notfaketaxi.controllers;


import com.example.notfaketaxi.entities.Client;
import com.example.notfaketaxi.entities.Role;
import com.example.notfaketaxi.repositories.ClientRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientRepository clientRepo;

    public ClientController(ClientRepository clientRepo) {
        this.clientRepo = clientRepo;
    }

    @GetMapping("/fetch")
    public List<Client> getAllPeople() {
        return clientRepo.findAll();
    }
    @PostMapping("/register")
    public Client registerUser(String username, String password){
        return clientRepo.save(clientRepo.findUserByUsername(username)
                .orElse(new Client(username, password)));

    }

}
