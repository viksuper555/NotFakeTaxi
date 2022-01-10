package com.notfaketaxi.controllers;

import com.notfaketaxi.entities.Role;
import com.notfaketaxi.repositories.RoleRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/role")
public class RoleController {

    private final RoleRepository rolerepo;

    RoleController(RoleRepository rolerepo){
        this.rolerepo = rolerepo;
    }

    @GetMapping("/fetch")
    public List<Role> getAllPeople() {
        return rolerepo.findAll();
    }
    @GetMapping("/find")
    public Role findRole(String name) {
        Optional <Role> role = rolerepo.findRoleByName(name);
        return role.orElse(new Role());
    }
}
