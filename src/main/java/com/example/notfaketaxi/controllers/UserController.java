package com.example.notfaketaxi.controllers;


import com.example.notfaketaxi.entities.User;
import com.example.notfaketaxi.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
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