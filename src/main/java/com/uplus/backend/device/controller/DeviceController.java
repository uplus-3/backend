package com.uplus.backend.device.controller;


import com.uplus.backend.device.dto.DeviceDetailResponseDto;
import com.uplus.backend.device.dto.DeviceListResponseDto;
import com.uplus.backend.device.dto.DeviceSelfCompResponseDto;
import com.uplus.backend.device.service.DeviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 단말기 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Slf4j
@Api(value = "단말기 API", tags = {"Device"})
@RestController
@RequestMapping("api/devices")
@RequiredArgsConstructor
public class DeviceController {

	private final DeviceService deviceService;

	@GetMapping("")
	@ApiOperation(value = "단말기 리스트 조회 Test", notes = "Test")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Test 성공")
	})
	public ResponseEntity<DeviceListResponseDto> getDeviceList(
		@RequestParam("network-type") int networkType, @RequestParam("plan") Long planId,
		@RequestParam("discount-type") int discountType,
		@RequestParam("installment-type") int installmentType) {
		DeviceListResponseDto responseDto =
			deviceService.getDeviceList(networkType, planId, discountType, installmentType);

		return ResponseEntity.ok().body(responseDto);
	}

	@GetMapping("/{device-id}")
	@ApiOperation(value = "디바이스 디테일, 주문 페이지 할인, 요금제 변화 요청 Test", notes = "Test")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Test 성공")
	})
	public ResponseEntity<DeviceDetailResponseDto> getDeviceDetail(
		@PathVariable(name = "device-id") Long deviceId, @RequestParam("plan") Long planId,
		@RequestParam("discount-type") int discountType,
		@RequestParam("installment-type") int installmentType) {
		DeviceDetailResponseDto responseDto = deviceService.getDeviceDetail(deviceId, planId,
			discountType, installmentType);

		return ResponseEntity.ok().body(responseDto);
	}

	@GetMapping("/{device-id}/self")
	@ApiOperation(value = "동일 기기 비교 조회  Test", notes = "Test")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Test 성공")
	})
	public ResponseEntity<DeviceSelfCompResponseDto> getDeviceSelfComp(
		@PathVariable(name = "device-id") Long deviceId) {
		DeviceSelfCompResponseDto responseDto = deviceService.getDeviceSelfComp(deviceId);

		return ResponseEntity.ok().body(responseDto);
	}
}

