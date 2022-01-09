package com.example.notfaketaxi.controllers;


import com.example.notfaketaxi.entities.Client;
import com.example.notfaketaxi.entities.Order;
import com.example.notfaketaxi.models.requests.CreateOrderRequest;
import com.example.notfaketaxi.models.responses.OrderResponse;
import com.example.notfaketaxi.repositories.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity CloseFinishedOrder(@RequestAttribute Client client, Long orderid)
    {
        Optional<Order> optOrder = orderRepo.findOrderByIdAndCloseDateIsNull(orderid);
        if(optOrder.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
        Order order = optOrder.get();
        if((order.getCustomer().getId() != client.getId()) && order.getDriver().getId() != client.getId())
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        order.setCloseDate(Instant.now());
        orderRepo.save(order);
        return new ResponseEntity(
                new OrderResponse("Okay", order.getId(), order.getDescription(), order.getPrice(), order.getCustomer().getId(), null), HttpStatus.OK);
    }

}
