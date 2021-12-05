package com.example.notfaketaxi.controllers;

import com.example.notfaketaxi.entities.OAuth;
import com.example.notfaketaxi.models.AuthorizationCodeRequest;
import com.example.notfaketaxi.repositories.OAuthRepository;
import org.apache.tomcat.jni.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/authorization")
public class OAuthController {

    private List<User> users;
    private final OAuthRepository oauthRepo;

    public OAuthController(List<User> users, OAuthRepository oauthrepo){
        this.users = users;
        this.oauthRepo = oauthrepo;
    }

    @GetMapping("/fetch")
    public List<OAuth> getAllPeople() {
        return oauthRepo.findAll();
    }

    @PostMapping(path = "/createauthcode")
    public ResponseEntity CreateAuthorizationCode(@RequestBody AuthorizationCodeRequest request){
         OAuth oAuthByUser = oauthRepo.findOAuthByUser(request.userName, request.password);

        if(oAuthByUser == null)
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);

        UUID code =  UUID.randomUUID();
        oAuthByUser.authorizationCode = code;
        oauthRepo.save(oAuthByUser);

        return new ResponseEntity(code, HttpStatus.OK);

    }

    @PostMapping(path = "/createauthtoken")
    public UUID CreateAuthorizationToken(@RequestBody AuthorizationCodeRequest request){
        if(request.code == null);

        return null;
    }

}
