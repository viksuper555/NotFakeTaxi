package com.notfaketaxi.controllers;


import com.notfaketaxi.entities.Client;
import com.notfaketaxi.entities.Role;
import com.notfaketaxi.models.responses.BaseResponse;
import com.notfaketaxi.repositories.ClientRepository;
import com.notfaketaxi.repositories.RoleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientRepository clientRepo;
    private final RoleRepository roleRepo;

    public ClientController(ClientRepository clientRepo, RoleRepository roleRepo) {
        this.clientRepo = clientRepo;
        this.roleRepo = roleRepo;
    }

    @GetMapping("/fetch")
    public List<Client> getAllPeople() {
        return clientRepo.findAll();
    }
    @PostMapping("/register")
    public ResponseEntity<BaseResponse> registerUser(String username, String password){

        if(roleRepo.findAll().isEmpty())
        {
            roleRepo.save(new Role("Customer"));
            roleRepo.save(new Role("Driver"));
        }

        Client client = new Client(username, password);

        client.addRole(roleRepo.findRoleByName("Customer"));
        clientRepo.save(client);
        return new ResponseEntity<BaseResponse>(new BaseResponse("Success"), HttpStatus.OK);
    }
    @PostMapping("/driver/activate")
    public ResponseEntity<BaseResponse> driverActivate(@RequestAttribute Client client)
    {
        client.addRole(roleRepo.findRoleByName("Driver"));
        clientRepo.save(client);
        return new ResponseEntity<BaseResponse>(new BaseResponse("Success"), HttpStatus.OK);
    }


}
