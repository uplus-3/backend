package com.uplus.backend.order.repository;

import com.uplus.backend.order.entity.Order;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 담당자 : 윤병찬
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	Optional<Order> findByNameAndNumber(String name, Long number);

	Boolean existsByNumber(Long number);
}
