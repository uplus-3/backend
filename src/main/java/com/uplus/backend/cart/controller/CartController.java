package com.uplus.backend.cart.controller;


import com.uplus.backend.cart.dto.CartListResponseDto;
import com.uplus.backend.cart.dto.CartRequestDto;
import com.uplus.backend.cart.dto.CartResponseDto;
import com.uplus.backend.cart.service.CartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * 장바구니 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Slf4j
@Api(value = "장바구니 API", tags = {"Cart"})
@RestController
@RequestMapping("api/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/{cartId}")
    @ApiOperation(value = "장바구니 항목 추가", notes = "장바구니 항목을 추가한다.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "장바구니 항목 추가 성공"),
        @ApiResponse(code = 404, message = "존재하지 않는 데이터"),
    })
    public ResponseEntity<CartListResponseDto> createCartItem(
        @Valid @RequestBody CartRequestDto request, @PathVariable("cartId") Long cartId) {

        CartListResponseDto cartListResponseDto;   cartListResponseDto = cartService.create(request, cartId);

        return ResponseEntity.ok().body(cartListResponseDto);
    }


    @GetMapping("/{cartId}")
    @ApiOperation(value = "장바구니 조회 성공", notes = "장바구니 ID를 통해 조회를 합니다.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "장바구니 조회 성공"),
        @ApiResponse(code = 404, message = "존재하지 않는 데이터"),
    })
    public ResponseEntity<CartListResponseDto> getCartListByCartId(@PathVariable Long cartId) {

        CartListResponseDto cartListResponseDto = cartService.getCartListByCartId(cartId);

        return ResponseEntity.ok().body(cartListResponseDto);
    }

    @DeleteMapping("/{cartItemId}")
    @ApiOperation(value = "장바구니 항목 삭제", notes = "장바구니 항목을 삭제한다.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "장바구니 항목 삭제 성공"),
        @ApiResponse(code = 404, message = "존재하지 않는 데이터"),
    })
    public ResponseEntity<CartListResponseDto> deleteCartItem(@PathVariable("cartItemId") Long cartItemId) {
        cartService.delete(cartItemId);

        return ResponseEntity.ok().build();
    }

}

