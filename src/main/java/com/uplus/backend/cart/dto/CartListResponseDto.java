package com.uplus.backend.cart.dto;

import com.uplus.backend.cart.entity.Cart;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;


/**
 * 담당자 : 성아영
 */
@Getter
@Builder
public class CartListResponseDto {

    private List<CartResponseDto> carts;

    public static CartListResponseDto fromEntity(List<Cart> carts) {
        return CartListResponseDto.builder()
            .carts(carts.stream()
                .map(CartResponseDto::fromEntity)
                .collect(Collectors.toList()))
            .build();
    }
}
