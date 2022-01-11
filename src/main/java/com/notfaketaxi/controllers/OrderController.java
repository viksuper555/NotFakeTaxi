package com.notfaketaxi.controllers;


import com.notfaketaxi.entities.Client;
import com.notfaketaxi.entities.Order;
import com.notfaketaxi.models.requests.CreateOrderRequest;
import com.notfaketaxi.models.responses.BaseResponse;
import com.notfaketaxi.models.responses.OrderResponse;
import com.notfaketaxi.repositories.OrderRepository;
import com.notfaketaxi.services.HttpRouteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderRepository orderRepo;


    public OrderController(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }


    @GetMapping("/fetch")
    public ResponseEntity getAllOrders(@RequestAttribute Client client) {
        if(!client.hasRole("Driver"))
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        return new ResponseEntity(orderRepo.findOrderByCloseDateIsNullAndAndDriverIsNull(), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity createOrder(@RequestAttribute Client client, @RequestBody CreateOrderRequest request){

        var routes = HttpRouteService.GetDirections(request.Origin, request.Destination);
        if(routes == null)
            return new ResponseEntity(new BaseResponse("Address not found."), HttpStatus.BAD_REQUEST);

        var route = routes.get(0).legs.get(0);
        if(request.Price == 0)
            request.Price = route.distance.value * 1.6 / 1000;
        if(request.Description == null || request.Description.length() == 0)
            request.Description = route.duration.text;


        Order order = orderRepo.save(new Order(client, request.Price, request.Origin, request.Destination, request.Description));
        return new ResponseEntity(new OrderResponse("Success!", order.getId(), request.Origin, request.Destination,
                request.Price, client.getId(), request.Description), HttpStatus.OK);
    }

    @PostMapping("/close")
    public ResponseEntity CloseFinishedOrder(@RequestAttribute Client client, Long id)
    {
        Optional<Order> optOrder = orderRepo.findOrderByIdAndCloseDateIsNull(id);
        if(optOrder.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
        Order order = optOrder.get();
        if((order.getCustomer().getId() != client.getId()) && order.getDriver().getId() != client.getId())
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        order.setCloseDate(Instant.now());
        orderRepo.save(order);
        return new ResponseEntity(
                new OrderResponse("Success!", order.getId(), order.getOrigin(), order.getDestination(),
                        order.getPrice(),order.getCustomer().getId(), order.getDescription()), HttpStatus.OK);
    }

    @PostMapping("/accept")
    public ResponseEntity AcceptOrder(@RequestAttribute Client client, Long id)
    {

        Optional<Order> optOrder = orderRepo.findOrderByIdAndCloseDateIsNull(id);
        if(optOrder.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
        Order order = optOrder.get();
        if(!client.hasRole("Driver"))
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        order.setDriver(client);
        orderRepo.save(order);
        return new ResponseEntity(
                new OrderResponse("Success!", order.getId(), order.getOrigin(), order.getDestination(),
                        order.getPrice(),order.getCustomer().getId(), order.getDescription(), order.getDriver().getId()), HttpStatus.OK);


    }

}
