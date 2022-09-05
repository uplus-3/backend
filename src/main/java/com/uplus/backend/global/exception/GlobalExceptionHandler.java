package com.uplus.backend.global.exception;

import static com.uplus.backend.global.exception.ErrorCode.INVALID_REQUEST_VALUE_ERROR;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 담당자 : 윤병찬 전역 예외 처리 Handler 정의
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	// Custom Exception Handler
	@ExceptionHandler({CustomException.class})
	protected ResponseEntity<ErrorResponseDto> handleCustomException(CustomException e) {
		log.error("error : {}", e.getErrorCode().getMessage());
		e.printStackTrace();
		ErrorCode errorCode = e.getErrorCode();
		ErrorResponseDto responseDto = ErrorResponseDto.builder()
			.detailStatus(errorCode.getDetailStatus())
			.message(errorCode.getMessage())
			.build();

		return ResponseEntity.status(errorCode.getStatus()).body(responseDto);
	}

	// @Valid : Dto validation Exception
	@ExceptionHandler({MethodArgumentNotValidException.class})
	public ResponseEntity<ErrorResponseDto> processValidationError(
		MethodArgumentNotValidException e) {
		log.error("error : {}", e.getMessage());
		e.printStackTrace();
		ErrorResponseDto responseDto = ErrorResponseDto.builder()
			.detailStatus(INVALID_REQUEST_VALUE_ERROR.getDetailStatus())
			.message(INVALID_REQUEST_VALUE_ERROR.getMessage())
			.build();

		return ResponseEntity.badRequest().body(responseDto);
	}

	// Server Error or Undefined Exception Handle
	@ExceptionHandler({Exception.class})
	protected ResponseEntity<ErrorResponseDto> handleServerException(Exception e) {
		log.error("Server error : {}", e.getMessage());
		e.printStackTrace();

		return ResponseEntity.internalServerError().build();
	}
}
