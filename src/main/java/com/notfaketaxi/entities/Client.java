package com.notfaketaxi.entities;


import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "client")
public class Client {

    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String username;

    @Column()
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "client_role",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )

    private Set<Role> roles = new LinkedHashSet<Role>();

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        if(this.roles == null) this.roles = new HashSet<Role>();
        this.roles.add(role);
    }
    public boolean hasRole(String roleName)
    {
        if(this.roles == null) return false;
        return this.roles.stream().anyMatch(item -> Objects.equals(item.getName(), roleName));
    }

    //Constructor

    public Client()
    {

    }
    public Client(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
