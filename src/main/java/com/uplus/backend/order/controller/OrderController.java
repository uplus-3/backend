package com.uplus.backend.order.controller;


import com.uplus.backend.order.dto.OrderCreateRequestDto;
import com.uplus.backend.order.dto.OrderCreateResponseDto;
import com.uplus.backend.order.dto.OrderResponseDto;
import com.uplus.backend.order.dto.OrderUpdateRequestDto;
import com.uplus.backend.order.service.OrderService;
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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 주문 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Slf4j
@Api(value = "주문 API", tags = {"Order"})
@RestController
@RequestMapping("api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("")
    @ApiOperation(value = "주문 생성", notes = "전송 받은 정보로 주문 데이터를 생성합니다.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "주문 생성 완료"),
        @ApiResponse(code = 400, message = "잘못된 주문 생성 정보 전송"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<OrderCreateResponseDto> createOrder(
        @Valid @RequestBody OrderCreateRequestDto request) {

        OrderCreateResponseDto orderCreateResponseDto = orderService.create(request);

        return ResponseEntity.ok().body(orderCreateResponseDto);
    }

    @GetMapping("")
    @ApiOperation(value = "주문 조회", notes = "전송 받은 주문자 이름과 주문 번호로 주문 데이터를 조회합니다.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "주문 조회 성공"),
        @ApiResponse(code = 400, message = "잘못된 주문 조회 정보 전송"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<OrderResponseDto> getByNameAndNumber(
        @RequestParam("name") String name, @RequestParam("number") Long number) {

        OrderResponseDto orderResponseDto = orderService.getByNameAndNumber(name, number);

        return ResponseEntity.ok().body(orderResponseDto);
    }

    @PatchMapping("/{order-id}")
    @ApiOperation(value = "주문 수정", notes = "전송 받은 주문 번호에 해당하는 주문 데이터를 전송 받은 데이터로 수정합니다.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "주문 수정 성공"),
        @ApiResponse(code = 404, message = "주문 정보 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<OrderResponseDto> update(
        @PathVariable("order-id") Long orderId,
        @Valid @RequestBody OrderUpdateRequestDto orderUpdateRequestDto) {

        OrderResponseDto orderResponseDto = orderService.update(orderId, orderUpdateRequestDto);

        return ResponseEntity.ok().body(orderResponseDto);
    }

    @DeleteMapping("/{order-id}")
    @ApiOperation(value = "주문 삭제", notes = "전송 받은 주문 번호에 해당하는 주문 데이터를 삭제합니다.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "주문 삭제 성공"),
        @ApiResponse(code = 404, message = "주문 정보 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<Void> delete(
        @PathVariable("order-id") Long orderId) {
        orderService.delete(orderId);

        return ResponseEntity.ok().build();
    }
}

