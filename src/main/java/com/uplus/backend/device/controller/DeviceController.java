package com.uplus.backend.device.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 단말기 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Slf4j
@Api(value = "단말기 API", tags = {"Device"})
@RestController
@RequestMapping("api/device")
@RequiredArgsConstructor
public class DeviceController {

//    private final DeviceService deviceService;

    @GetMapping("")
    @ApiOperation(value = "단말기 생성 Test", notes = "Test")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Test 성공"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<String> createBoard(
            HttpServletRequest request) {

        return new ResponseEntity<String>("Test성공", HttpStatus.CREATED);
    }

}

