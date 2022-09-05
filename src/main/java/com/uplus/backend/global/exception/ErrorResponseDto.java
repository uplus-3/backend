package com.uplus.backend.global.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * 담당자 : 윤병찬
 */
@Getter
@AllArgsConstructor
@Builder
public class ErrorResponseDto {

	private int detailStatus;
	private String message;
}
