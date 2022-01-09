package com.example.notfaketaxi.repositories;

import com.example.notfaketaxi.entities.Client;
import com.example.notfaketaxi.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.OptionalInt;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findOrderByIdAndCloseDateIsNull(Long id);


}
