package com.uplus.backend.launchingdevice.controller;

import com.uplus.backend.launchingdevice.dto.LaunchingDeviceListResponseDto;
import com.uplus.backend.launchingdevice.service.LaunchingDeviceService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/launching-devices")
@RequiredArgsConstructor
public class LaunchingDeviceController {

    private final LaunchingDeviceService launchingDeviceService;

    @GetMapping("")
    @ApiOperation(value = "출시 예정 단말기 리스트 조회", notes = "출시 예정 단말기 리스트를 조회할 수 있다.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "출시 예정 단말기 리스트 조회 성공")
    })
    public ResponseEntity<LaunchingDeviceListResponseDto> getLaunchingDevices(
        @RequestParam("network-type") int networkType) {
        // 단말기 불러오기
        LaunchingDeviceListResponseDto responseDto = launchingDeviceService.getLaunchingDevices(
            networkType);
        return ResponseEntity.ok().body(responseDto);
    }
}
