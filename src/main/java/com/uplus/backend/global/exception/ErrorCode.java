package com.uplus.backend.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 담당자 : 김수현, 성아영, 윤병찬, 이일환
 * 예외 처리 코드 정의.
 */
@Getter
@AllArgsConstructor
public enum ErrorCode {

	// 0XX : 공통
	INVALID_REQUEST_VALUE_ERROR(400, 0, "잘못된 요청"),

	// -1XX : Plan
	PLAN_NO_DATA_ERROR(404, -104, "존재하지 않는 요금제"),


	// -2XX : Device
	DEVICE_NO_DATA_ERROR(404, -204, "존재하지 않는 단말기"),


	// -3XX : Tag
	TAG_NO_DATA_ERROR(404, -304, "존재하지 않는 태그"),


	// -4XX : Color
	COLOR_NO_DATA_ERROR(404, -404, "존재하지 않는 색상"),
	NO_STOCK_ERROR(302, -402, "재고 없는 단말기"),

	// -5XX : Image
	IMAGE_NO_DATA_ERROR(404, -504, "존재하지 않는 이미지"),

	// -6XX : Order
	ORDER_NO_DATA_ERROR(404, -604, "존재하지 않는 주문"),


	// -7XX : Cart

	ALREADY_DELETED_CART_ITEM(400, -700, "이미 삭제된 장바구니 품목"),
	NO_CART_DATA_ERROR(404, -704, "존재하지 않는 장바구니"),

	// -8XX : Search
	NO_SEARCH_KEYWORD_ERROR(400, -800, "빈 검색 키워드");



	private final int status;
	private final int detailStatus;
	private final String message;
}