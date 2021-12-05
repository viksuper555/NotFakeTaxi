package com.example.notfaketaxi.entities;

import lombok.Data;
import org.hibernate.id.GUIDGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "oauth")
public class OAuth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    public UUID authorizationCode;

    private UUID authorizationToken;

    private Date CreateDate;
    private Date ExpireDate;

    public OAuth(){

    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UUID getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(UUID authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public UUID getAuthorizationToken() {
        return authorizationToken;
    }

    public void setAuthorizationToken(UUID authorizationToken) {
        this.authorizationToken = authorizationToken;
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date createDate) {
        CreateDate = createDate;
    }

    public Date getExpireDate() {
        return ExpireDate;
    }

    public void setExpireDate(Date expireDate) {
        ExpireDate = expireDate;
    }

    public OAuth(int id, UUID authorizationCode, UUID authorizationToken, Date createDate, Date expireDate) {
        this.id = id;
        this.authorizationCode = authorizationCode;
        this.authorizationToken = authorizationToken;
        this.CreateDate = createDate;
        this.ExpireDate = expireDate;
    }
}
