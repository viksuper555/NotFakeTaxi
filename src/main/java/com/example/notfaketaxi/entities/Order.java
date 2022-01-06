package com.example.notfaketaxi.entities;

import javax.persistence.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;


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

    private Point pointA;

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

    private Point pointB;

    private double distance;

    private String description;

    private Instant createDate;

    private Instant closeDate;

    public Long getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Point getPointA() {
        return pointA;
    }

    public void setPointA(Point pointA) {
        this.pointA = pointA;
    }

    public Point getPointB() {
        return pointB;
    }

    public void setPointB(Point pointB) {
        this.pointB = pointB;
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

    public Order(double price, String description, Client customer) {
        this.price = price;
        //this.pointA = pointA;
        //this.pointB = pointB;
        this.description = description;
        this.createDate = Instant.now();
        this.customer = customer;
        this.closeDate = closeDate;



    }
}
