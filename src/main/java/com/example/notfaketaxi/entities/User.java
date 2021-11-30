package com.example.notfaketaxi.entities;


import javax.persistence.*;

@Entity
@Table(name = "clients")
public class User {

    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String username;

    @Column()
    private String password;


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

    //Constructor

    public User(){
    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
