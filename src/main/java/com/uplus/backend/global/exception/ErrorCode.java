package com.uplus.backend.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public enum ErrorCode {

    //400 BAD_REQUEST 잘못된 요청
    INVALID_PARAMETER(400, "잘못된 파라미터 값 전송"),

    //404 NOT_FOUND 잘못된 리소스 접근
    DEVICE_NOT_FOUND(404, "존재하지 않는 단말기"),
    ORDER_NOT_FOUND(404, "존재하지 않는 단말기"),
    PLAN_NOT_FOUND(404, "존재하지 않는 단말기"),

    //409 CONFLICT 중복된 리소스
    ORDER_ALREADY_EXISTED(409, "이미 저장되어 있는 주문 ID"),

    //500 INTERNAL SERVER ERROR
    INTERNAL_SERVER_ERROR(500, "서버 에러");
    private final int status;
    private final String message;
}