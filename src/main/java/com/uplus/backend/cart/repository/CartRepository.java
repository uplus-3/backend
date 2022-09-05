package com.uplus.backend.cart.repository;

import com.uplus.backend.cart.entity.Cart;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 담당자 : 성아영
 */
public interface CartRepository extends JpaRepository<Cart, Long> {

	boolean existsByCartId(Long cartId);

	List<Cart> findAllByCartIdAndDeletedFalseAndCreatedAtGreaterThanOrderByCreatedAtDesc(
		Long cartId, LocalDateTime localDateTime);
}
