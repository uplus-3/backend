package com.uplus.backend.device.controller;


import com.uplus.backend.device.dto.DeviceListResponseDto;
import com.uplus.backend.device.service.DeviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
		@RequestParam("plan") Long planId,
		@RequestParam("discount-type") int discountType
	) {

		DeviceListResponseDto responseDto = deviceService.getDeviceList(planId, discountType);

		return ResponseEntity.ok().body(responseDto);
	}
}

