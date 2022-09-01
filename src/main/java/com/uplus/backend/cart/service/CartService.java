package com.uplus.backend.cart.service;

import com.uplus.backend.cart.dto.CartListResponseDto;
import com.uplus.backend.cart.dto.CartRequestDto;
import com.uplus.backend.cart.entity.Cart;
import com.uplus.backend.cart.repository.CartRepository;
import com.uplus.backend.device.entity.Color;
import com.uplus.backend.device.repository.ColorRepository;
import com.uplus.backend.global.exception.CustomException;
import com.uplus.backend.global.util.CartIdUtil;
import com.uplus.backend.plan.entity.Plan;
import com.uplus.backend.plan.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.uplus.backend.global.exception.ErrorCode.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    private final ColorRepository colorRepository;

    private final PlanRepository planRepository;

    @Transactional
    public CartListResponseDto create(CartRequestDto requestDto, Long cartId) {

        if (cartId == -1) {
            boolean checkCartIdDuplicated;

            do {
                cartId = CartIdUtil.createCartId();
                checkCartIdDuplicated = cartRepository.existsByCartId(
                    CartIdUtil.createCartId());
            } while (checkCartIdDuplicated);
        }

        Color color = colorRepository.findById(requestDto.getColorId()).orElseThrow(() -> new CustomException(COLOR_NO_DATA_ERROR));

        Plan plan = planRepository.findById(requestDto.getPlanId()).orElseThrow(() -> new CustomException(PLAN_NO_DATA_ERROR));

        Cart cart = requestDto.toEntity(color, plan, cartId);

        cartRepository.save(cart);

        LocalDateTime localDateTime = LocalDateTime.now().minusDays(90);

        List<Cart> cartList = cartRepository.findAllByCartIdAndDeletedFalseAndCreatedAtGreaterThanOrderByCreatedAtDesc(cartId, localDateTime);


        return CartListResponseDto.fromEntity(cartList);
    }

    @Transactional
    public void delete(Long cartItemId) {

        Cart cart = cartRepository.findById(cartItemId).orElseThrow(() -> new CustomException(CART_NO_DATA_ERROR));
        cart.setDeleted();
    }

    @Transactional(readOnly = true)
    public CartListResponseDto getCartListByCartId(Long cartId) {

        LocalDateTime localDateTime = LocalDateTime.now().minusDays(90);

        List<Cart> cartList = cartRepository.findAllByCartIdAndDeletedFalseAndCreatedAtGreaterThanOrderByCreatedAtDesc(cartId, localDateTime);

        return CartListResponseDto.fromEntity(cartList);
    }
}
