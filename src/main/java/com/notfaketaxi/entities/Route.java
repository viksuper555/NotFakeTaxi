package com.notfaketaxi.entities;

import org.springframework.data.mapping.KPropertyPathExtensionsKt;

import java.awt.*;

public class Route {

    private double kilometers;
    private double price;
    //private Client driver;
    //private Client clientName;
    private Point initialLoc;
    private Point finalLoc;

    public double getKilometers() {
        return kilometers;
    }

    public void setKilometers(double kilometers) {
        this.kilometers = kilometers;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Point getInitialLoc() {
        return initialLoc;
    }

    public void setInitialLoc(Point initialLoc) {
        this.initialLoc = initialLoc;
    }

    public Point getFinalLoc() {
        return finalLoc;
    }

    public void setFinalLoc(Point finalLoc) {
        this.finalLoc = finalLoc;
    }

    public Route(double kilometers, double price, Point initialLoc, Point finalLoc) {
        this.kilometers = kilometers;
        this.price = price;
        this.initialLoc = initialLoc;
        this.finalLoc = finalLoc;
    }
}
