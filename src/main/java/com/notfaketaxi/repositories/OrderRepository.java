package com.notfaketaxi.repositories;

import com.notfaketaxi.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findOrderByIdAndCloseDateIsNull(Long id);
    Optional<Order> findOrderByCloseDateIsNullAndAndDriverIsNull();


}
