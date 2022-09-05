package com.uplus.backend.cart.service;

import static com.uplus.backend.global.exception.ErrorCode.ALREADY_DELETED_CART_ITEM;
import static com.uplus.backend.global.exception.ErrorCode.COLOR_NO_DATA_ERROR;
import static com.uplus.backend.global.exception.ErrorCode.NO_CART_DATA_ERROR;
import static com.uplus.backend.global.exception.ErrorCode.PLAN_NO_DATA_ERROR;

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
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 담당자 : 성아영
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CartService {

	private final CartRepository cartRepository;

	private final ColorRepository colorRepository;

	private final PlanRepository planRepository;

	@Transactional
	public CartListResponseDto create(CartRequestDto requestDto, Long cartId) {
		// 접속자의 브라우저 쿠키에 cartId 여부 체크
		if (cartId == -1) {
			boolean checkCartIdDuplicated;
			do {
				cartId = CartIdUtil.createCartId();
				checkCartIdDuplicated = cartRepository.existsByCartId(
					CartIdUtil.createCartId());
			} while (checkCartIdDuplicated);
		} else {
			// 해당 장바구니 Id가 db에 없을 경우 에러 처리
			boolean isExitedCartId = cartRepository.existsByCartId(cartId);
			if (!isExitedCartId) {
				throw new CustomException(NO_CART_DATA_ERROR);
			}
		}

		Color color = colorRepository.findById(requestDto.getColorId())
			.orElseThrow(() -> new CustomException(COLOR_NO_DATA_ERROR));

		Plan plan = null;
		if (requestDto.getPlanId() != -1) {
			plan = planRepository.findById(requestDto.getPlanId())
				.orElseThrow(() -> new CustomException(PLAN_NO_DATA_ERROR));
		}

		Cart cart = requestDto.toEntity(color, plan, cartId);
		cartRepository.save(cart);

		LocalDateTime localDateTime = LocalDateTime.now().minusDays(90);

		List<Cart> cartList = cartRepository.findAllByCartIdAndDeletedFalseAndCreatedAtGreaterThanOrderByCreatedAtDesc(
			cartId, localDateTime);

		return CartListResponseDto.fromEntity(cartList);
	}

	@Transactional
	public void delete(Long cartItemId) {
		Cart cart = cartRepository.findById(cartItemId)
			.orElseThrow(() -> new CustomException(NO_CART_DATA_ERROR));
		if (cart.isDeleted()) {
			throw new CustomException(ALREADY_DELETED_CART_ITEM);
		} else {
			cart.setDeleted(true);
			cartRepository.save(cart);
		}
	}

	@Transactional(readOnly = true)
	public CartListResponseDto getCartListByCartId(Long cartId) {
		boolean isExitedCartId = cartRepository.existsByCartId(cartId);
		if (!isExitedCartId) {
			throw new CustomException(NO_CART_DATA_ERROR);
		}

		LocalDateTime localDateTime = LocalDateTime.now().minusDays(90);

		List<Cart> cartList = cartRepository.findAllByCartIdAndDeletedFalseAndCreatedAtGreaterThanOrderByCreatedAtDesc(
			cartId, localDateTime);

		return CartListResponseDto.fromEntity(cartList);
	}
}
