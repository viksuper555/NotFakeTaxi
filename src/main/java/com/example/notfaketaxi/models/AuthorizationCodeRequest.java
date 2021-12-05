package com.example.notfaketaxi.models;

import com.example.notfaketaxi.entities.User;

import java.util.UUID;

public class AuthorizationCodeRequest {

    public String userName;
    public String password;
    public User user;
    public UUID code;

}
