package com.example.notfaketaxi.entities;

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
    @JoinColumn(name = "client_id")
    private Client client;

    public UUID authorizationCode;

    private UUID accessToken;

    private Date CreateDate;
    private Date ExpireDate;

    public OAuth(){

    }

    public int getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public UUID getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(UUID authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public UUID getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(UUID authorizationToken) {
        this.accessToken = authorizationToken;
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

    public OAuth(UUID authorizationToken,Date createDate, Date expireDate) {
        this.accessToken = authorizationToken;
        this.CreateDate = createDate;
        this.ExpireDate = expireDate;

    }

    public OAuth(UUID authorizationCode, Date createDate, Date expireDate, Client client) {
        this.authorizationCode = authorizationCode;
        this.CreateDate = createDate;
        this.ExpireDate = expireDate;
        this.client = client;
    }
}
