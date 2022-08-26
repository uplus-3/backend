package com.uplus.backend.device.controller;


import com.uplus.backend.device.dto.DeviceCreateRequestDto;
import com.uplus.backend.device.dto.DeviceCreateResponseDto;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@PostMapping("")
	@ApiOperation(value = "단말기 생성", notes = "단말기를 생성할 수 있다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "단말기 생성 성공")
	})
	public ResponseEntity<DeviceCreateResponseDto> create(
		@RequestBody DeviceCreateRequestDto reqeustDto) {
		DeviceCreateResponseDto responseDto = deviceService.create(reqeustDto);

		return ResponseEntity.ok().body(responseDto);
	}

	@GetMapping("")
	@ApiOperation(value = "단말기 리스트 조회", notes = "단말기 리스트를 조회할 수 있다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "단말기 리스트 조회 성공")
	})
	public ResponseEntity<DeviceListResponseDto> getDeviceList(
		@RequestParam("network-type") int networkType,
		@RequestParam("plan") Long planId,
		@RequestParam("discount-type") int discountType,
		@RequestParam(name = "installment-period", required = false, defaultValue = "24") int installmentPeriod) {
		DeviceListResponseDto responseDto =
			deviceService.getDeviceList(networkType, planId, discountType, installmentPeriod);

		return ResponseEntity.ok().body(responseDto);
	}

	@GetMapping("/{device-id}")
	@ApiOperation(value = "입력값에 따른 단말기 정보 조회",
		notes = "입력값에 따른 단말기 정보를 조회할 수 있다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "단말기 리스트 조회 성공")
	})
	public ResponseEntity<DeviceDetailResponseDto> getDeviceDetail(
		@PathVariable("device-id") Long deviceId,
		@RequestParam("plan") Long planId,
		@RequestParam("discount-type") int discountType,
		@RequestParam("installment-period") int installmentPeriod) {
		DeviceDetailResponseDto responseDto = deviceService.getDeviceDetail(deviceId, planId,
			discountType, installmentPeriod);

		return ResponseEntity.ok().body(responseDto);
	}

	@GetMapping("/{device-id}/self")
	@ApiOperation(value = "동일 기기 비교 조회", notes = "동일 기기 비교 조회할 수 있다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "단말기 리스트 조회 성공")
	})
	public ResponseEntity<DeviceSelfCompResponseDto> getDeviceSelfComp(
		@PathVariable("device-id") Long deviceId) {
		DeviceSelfCompResponseDto responseDto = deviceService.getDeviceSelfComp(deviceId);

		return ResponseEntity.ok().body(responseDto);
	}
}

