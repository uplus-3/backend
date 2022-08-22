package com.uplus.backend.global.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponseDto {

	private int code;
	private String message;

	@Builder
	public ErrorResponseDto(ErrorCode errorCode) {
		this.code = errorCode.getStatus();
		this.message = errorCode.getMessage();
	}

}
