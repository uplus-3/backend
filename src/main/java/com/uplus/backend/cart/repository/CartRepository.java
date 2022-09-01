package com.uplus.backend.cart.repository;

import com.uplus.backend.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {


    boolean existsByCartId(Long cartId);

    List<Cart> findAllByCartIdAndCreatedAtGreaterThanOrderByCreatedAtDesc(Long cartId, LocalDateTime localDateTime);
}
