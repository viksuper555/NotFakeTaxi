package com.notfaketaxi.controllers;

import com.notfaketaxi.entities.Client;
import com.notfaketaxi.entities.OAuth;
import com.notfaketaxi.models.requests.AccessTokenRequest;
import com.notfaketaxi.models.requests.AuthorizationCodeRequest;
import com.notfaketaxi.models.responses.AccessTokenResponse;
import com.notfaketaxi.models.responses.AuthorizationCodeResponse;
import com.notfaketaxi.repositories.ClientRepository;
import com.notfaketaxi.repositories.OAuthRepository;
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

    @PostMapping(path = "/code")
    public ResponseEntity<AuthorizationCodeResponse> CreateAuthorizationCode(@RequestBody AuthorizationCodeRequest request){
        Optional<Client> client = clientRepo.findClientByUsernameAndPassword(request.username, request.password);

        if(client.isEmpty())
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        UUID code =  UUID.randomUUID();

        System.out.println(code);

        //1 minute expiration period
        OAuth oauth = new OAuth(code, new Date(), new Date(System.currentTimeMillis() + 600 * 1000), client.get());
        oauthRepo.save(oauth);
        return new ResponseEntity<AuthorizationCodeResponse>(new AuthorizationCodeResponse(code, "Success!"), HttpStatus.OK);

    }

    @PostMapping(path = "/token")
    public ResponseEntity<AccessTokenResponse> CreateAuthorizationToken(@RequestBody AccessTokenRequest request){
        Optional<OAuth> clientCode = oauthRepo.findOAuthByAuthorizationCode(request.authorization_code);

        Date todayDate = new Date();
        if(clientCode.isEmpty() || clientCode.get().getExpireDate().before(todayDate))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        UUID token =  UUID.randomUUID();
        OAuth code = clientCode.get();
        code.setAccessToken(token);
        code.setCreateDate(todayDate);
        code.setExpireDate(new Date(System.currentTimeMillis() + 60 * 1000  * 60 * 24));

        oauthRepo.save(clientCode.get());
        return new ResponseEntity<AccessTokenResponse>(new AccessTokenResponse(token, "Success!"), HttpStatus.OK);

    }

}
