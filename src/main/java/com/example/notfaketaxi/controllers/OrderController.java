package com.example.notfaketaxi.controllers;


import com.example.notfaketaxi.entities.Client;
import com.example.notfaketaxi.entities.Order;
import com.example.notfaketaxi.models.requests.CreateOrderRequest;
import com.example.notfaketaxi.models.responses.OrderResponse;
import com.example.notfaketaxi.repositories.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository orderRepo;


    public OrderController(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }


    @GetMapping("/fetch")
    public List<Order> getAllOrders() {return orderRepo.findAll();
    }
    @PostMapping("/create")
    public OrderResponse createOrder(@RequestAttribute Client client, @RequestBody CreateOrderRequest request){
        Order order = orderRepo.save(new Order(request.Price, request.Description, client));
        return new OrderResponse("Okay", order.getId(), order.getDescription(), order.getPrice(), order.getCustomer().getId(), null);
    }

    @PostMapping("/closeorder")
    public OrderResponse CloseFinishedOrder(@RequestAttribute Client client, Long orderid)
    {
        Order order = orderRepo.getById(orderid);
        order.setCloseDate(Instant.now());
        orderRepo.save(order);
        return new OrderResponse("Okay", order.getId(), order.getDescription(), order.getPrice(), order.getCustomer().getId(), null);
    }

}
