package com.uplus.backend.global.exception;


import static com.uplus.mobile.global.error.ErrorCode.INTERNAL_SERVER_ERROR;
import static com.uplus.mobile.global.error.ErrorCode.INVALID_PARAMETER;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // Custom Exception Handler
    @ExceptionHandler({ CustomException.class })
    protected ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        log.error("error : {}", e.getErrorCode().getMessage());
        e.printStackTrace();

        return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getErrorCode().getStatus(), e.getErrorCode().getMessage()),
                HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    // Server Error or Undefined Exception Handler
    @ExceptionHandler({ Exception.class })
    protected ResponseEntity<ErrorResponse> handleServerException(Exception e) {
        log.error("Server error : {}", e.getMessage());
        e.printStackTrace();

        return new ResponseEntity<ErrorResponse>(
                new ErrorResponse(INTERNAL_SERVER_ERROR.getStatus(), INTERNAL_SERVER_ERROR.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // @Valid : Dto validation Exception
    @ExceptionHandler({ MethodArgumentNotValidException.class })
    public ResponseEntity<ErrorResponse> processValidationError(MethodArgumentNotValidException e) {
        log.error("error : {}", e.getMessage());
        e.printStackTrace();

        return new ResponseEntity<ErrorResponse>(new ErrorResponse(INVALID_PARAMETER.getStatus(), INVALID_PARAMETER.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

}
