package com.example.notfaketaxi.models.responses;

import com.example.notfaketaxi.entities.Order;
import org.apache.el.stream.Optional;

public class OrderResponse extends BaseResponse{

    public Long Id;
    public String Description;
    public double Price;
    public Long CustomerId;
    public Long DriverId;

    public OrderResponse(String message, Long id, String description, double price, Long customerId, Long driverId) {
        super(message);
        Id = id;
        Description = description;
        Price = price;
        CustomerId = customerId;
        DriverId = driverId;
    }
}
