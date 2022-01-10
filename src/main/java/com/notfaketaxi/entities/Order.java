package com.notfaketaxi.entities;

import javax.persistence.*;
import java.awt.*;
import java.time.Instant;


@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @ManyToOne
    private Client customer;

    @ManyToOne
    private Client driver;

    private double price;

    private double distance;

    private String description;

    private Instant createDate;

    private Instant closeDate;
    private String origin;
    private String destination;

    public Client getCustomer() {
        return customer;
    }

    public void setCustomer(Client customer) {
        this.customer = customer;
    }

    public Client getDriver() {
        return driver;
    }

    public void setDriver(Client driver) {
        this.driver = driver;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public Long getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Instant getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Instant closeDate) {
        this.closeDate = closeDate;
    }

    public Order()
    {

    }

    public Order(Client customer, double price, String origin, String destination, String description) {
        this.price = price;
        this.description = description;
        this.createDate = Instant.now();
        this.customer = customer;
    }
}
