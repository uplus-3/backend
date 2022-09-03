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

    @PostMapping("")
    @ApiOperation(value = "장바구니 항목 추가", notes = "장바구니 항목을 추가한다.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "장바구니 항목 추가 성공"),
        @ApiResponse(code = 404, message = "존재하지 않는 데이터"),
    })
    public ResponseEntity<CartListResponseDto> createCartItem(
        @Valid @RequestBody CartRequestDto request, @CookieValue(name = "cartId", required = false) Long cartId, HttpServletResponse response) {

        CartListResponseDto cartListResponseDto;

        if (cartId == null) {
            cartId = -1L;
            cartListResponseDto = cartService.create(request, cartId);
            Cookie cartIdCookie = new Cookie("cartId", String.valueOf(cartListResponseDto.getCarts().get(0).getCartId()));
            Cookie cartCountCookie = new Cookie("cartCount", String.valueOf(cartListResponseDto.getCarts().size()));
            cartIdCookie.setMaxAge(60 * 60 * 24 * 365);
            cartIdCookie.setDomain("localhost");
            cartIdCookie.setPath("/");
            cartIdCookie.setSecure(true);
            response.addCookie(cartIdCookie);

            cartCountCookie.setMaxAge(60 * 60 * 24 * 365);
            cartCountCookie.setDomain("localhost");
            cartCountCookie.setPath("/");
            cartCountCookie.setSecure(true);
            response.addCookie(cartCountCookie);
        } else {
            cartListResponseDto = cartService.create(request, cartId);
        }

        return ResponseEntity.ok().body(cartListResponseDto);
    }


    @GetMapping("/{cartId}")
    @ApiOperation(value = "장바구니 조회 성공", notes = "장바구니 ID를 통해 조회를 합니다.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "장바구니 조회 성공"),
        @ApiResponse(code = 404, message = "존재하지 않는 데이터"),
    })
    public ResponseEntity<CartListResponseDto> getCartListByCartId(@PathVariable Long cartId) {
        //TODO : Cookie value 없을 때 validation 처리
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

