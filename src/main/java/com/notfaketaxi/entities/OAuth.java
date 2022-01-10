package com.notfaketaxi.entities;

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

    private UUID authorizationCode;

    private UUID accessToken;

    private Date createDate;
    private Date expireDate;

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
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public OAuth(UUID authorizationToken,Date createDate, Date expireDate) {
        this.accessToken = authorizationToken;
        this.createDate = createDate;
        this.expireDate = expireDate;

    }

    public OAuth(UUID authorizationCode, Date createDate, Date expireDate, Client client) {
        this.authorizationCode = authorizationCode;
        this.createDate = createDate;
        this.expireDate = expireDate;
        this.client = client;
    }
}
