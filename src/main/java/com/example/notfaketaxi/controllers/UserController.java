package com.example.notfaketaxi.controllers;


import com.example.notfaketaxi.entites.User;
import com.example.notfaketaxi.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/authorization")
public class UserController {

    private final UserRepository userRepo;

    public UserController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @PostMapping("/register")
    public User registerUser(String username, String password){
        return userRepo.save(userRepo.findUserByUsername(username)
                .orElse(new User(username, password)));

    }

}
