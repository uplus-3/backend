package com.uplus.backend.order.repository;

import com.uplus.backend.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	Order findByNameAndNumber(String name, Long number);
}
