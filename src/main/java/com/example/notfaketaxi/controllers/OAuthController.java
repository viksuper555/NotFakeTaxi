package com.example.notfaketaxi.controllers;

import com.example.notfaketaxi.entities.Client;
import com.example.notfaketaxi.entities.OAuth;
import com.example.notfaketaxi.models.AuthorizationCodeRequest;
import com.example.notfaketaxi.repositories.ClientRepository;
import com.example.notfaketaxi.repositories.OAuthRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/authorization")
public class OAuthController {

    private final OAuthRepository oauthRepo;
    private final ClientRepository clientRepo;

    public OAuthController(OAuthRepository oauthrepo, ClientRepository clientRepo){
        this.oauthRepo = oauthrepo;
        this.clientRepo = clientRepo;
    }

    @PostMapping(path = "/createauthcode")
    public ResponseEntity CreateAuthorizationCode(@RequestBody AuthorizationCodeRequest request){
        Optional<Client> client = clientRepo.findUserByUsernameAndPassword(request.userName, request.password);

        if(client.isEmpty())
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);

        UUID code =  UUID.randomUUID();

        //1 minute expiration period
        OAuth oauth = new OAuth(code, new Date(), new Date(System.currentTimeMillis() + 60 * 1000), client.get());
        oauthRepo.save(oauth);
        return new ResponseEntity(code, HttpStatus.OK);

    }

    @PostMapping(path = "/createauthtoken")
    public UUID CreateAuthorizationToken(@RequestBody AuthorizationCodeRequest request){
//        if(request.code == null);
//
        return null;
    }

}
