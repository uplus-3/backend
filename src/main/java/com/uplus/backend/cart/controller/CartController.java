package com.uplus.backend.cart.controller;


import com.uplus.backend.cart.dto.CartListResponseDto;
import com.uplus.backend.cart.dto.CartRequestDto;
import com.uplus.backend.cart.service.CartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 담당자 : 성아영 장바구니 관련 API 요청 처리를 위한 컨트롤러 정의.
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
		@ApiResponse(code = 400, message = "잘못된 장바구니 생성 정보 전송"),
		@ApiResponse(code = 404, message = "존재하지 않는 데이터"),
	})
	public ResponseEntity<CartListResponseDto> createCartItem(
		@Valid @RequestBody CartRequestDto request, @PathVariable("cartId") Long cartId) {
		CartListResponseDto cartListResponseDto = cartService.create(request, cartId);

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
	public ResponseEntity<CartListResponseDto> deleteCartItem(
		@PathVariable("cartItemId") Long cartItemId) {
		cartService.delete(cartItemId);

		return ResponseEntity.ok().build();
	}
}

