package com.uplus.backend.global.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ErrorResponseDto {

	private int detailStatus;
	private String message;
}
