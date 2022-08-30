package com.uplus.backend.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

	// 0XX : 공통
	INVALID_REQUEST_ERROR(400, 0, "잘못된 요청"),

	// -1XX : Plan
	PLAN_NO_DATA_ERROR(404, -104, "존재하지 않는 요금제"),

	// -2XX : Device
	DEVICE_NO_DATA_ERROR(404, -204, "존재하지 않는 단말기"),

	// -3XX : Tag
	TAG_NO_DATA_ERROR(404, -304, "존재하지 않는 태그"),

	// -4XX : Color
	COLOR_NO_DATA_ERROR(404, -404, "존재하지 않는 색상"),

	// -5XX : Image
	IMAGE_NO_DATA_ERROR(404, -504, "존재하지 않는 이미지"),

	// -6XX : Order
	ORDER_NO_DATA_ERROR(404, -604, "존재하지 않는 주문"),

	// -7XX : Cart
	CART_NO_DATA_ERROR(404, -704, "존재하지 않는 장바구니");

	// -8XX : Search

	private final int status;
	private final int detailStatus;
	private final String message;
}