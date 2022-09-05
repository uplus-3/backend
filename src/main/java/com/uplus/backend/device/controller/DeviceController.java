package com.uplus.backend.device.controller;


import com.uplus.backend.device.dto.device.DeviceCreateRequestDto;
import com.uplus.backend.device.dto.device.DeviceCreateResponseDto;
import com.uplus.backend.device.dto.device.DeviceDetailResponseDto;
import com.uplus.backend.device.dto.device.DeviceListResponseDto;
import com.uplus.backend.device.dto.device.DeviceSelfCompResponseDto;
import com.uplus.backend.device.dto.device.DeviceSimpleListResponseDto;
import com.uplus.backend.device.dto.price.PriceDetailResponseDto;
import com.uplus.backend.device.dto.price.PriceListResponseDto;
import com.uplus.backend.device.service.DeviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;
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
 * 담당자 : 이일환
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
		@ApiResponse(code = 200, message = "단말기 생성 성공"),
		@ApiResponse(code = 404, message = "존재하지 않는 데이터"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<DeviceCreateResponseDto> create(
		@Valid @RequestBody DeviceCreateRequestDto requestDto) {
		DeviceCreateResponseDto responseDto = deviceService.create(requestDto);

		return ResponseEntity.ok().body(responseDto);
	}

	@GetMapping("/simple")
	@ApiOperation(value = "단말기 심플 리스트 조회", notes = "단말기 심플 리스트를 조회할 수 있다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "단말기 심플 리스트 조회 성공"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<DeviceSimpleListResponseDto> getSimpleDevices() {
		DeviceSimpleListResponseDto responseDto = deviceService.getSimpleDevices();

		return ResponseEntity.ok().body(responseDto);
	}

	@GetMapping("")
	@ApiOperation(value = "네트워크 타입별 단말기 리스트 조회", notes = "네트워크 타입별 단말기 리스트를 조회할 수 있다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "단말기 리스트 조회 성공"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<DeviceListResponseDto> getDevices(
		@RequestParam("network-type") int networkType) {
		DeviceListResponseDto responseDto = deviceService.getDevices(networkType);

		return ResponseEntity.ok().body(responseDto);
	}

	@GetMapping("/plans/{plan-id}")
	@ApiOperation(value = "가격 리스트 조회", notes = "네트워크 타입별 단말기 리스트를 조회할 수 있다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "단말기 리스트 조회 성공"),
		@ApiResponse(code = 404, message = "존재하지 않는 데이터"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<PriceListResponseDto> getPrices(
		@PathVariable("plan-id") Long planId,
		@RequestParam("network-type") int networkType,
		@RequestParam("discount-type") int discountType,
		@RequestParam(name = "installment-period", defaultValue = "24") int installmentPeriod
	) {
		PriceListResponseDto responseDto = deviceService.getPrices(planId, networkType,
			discountType, installmentPeriod);

		return ResponseEntity.ok().body(responseDto);
	}

	@GetMapping("/{device-id}")
	@ApiOperation(value = "단말기 상세 정보 조회",
		notes = "단말기 상세 정보를 조회할 수 있다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "단말기 리스트 조회 성공"),
		@ApiResponse(code = 404, message = "존재하지 않는 데이터"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<DeviceDetailResponseDto> getDeviceDetail(
		@PathVariable("device-id") Long deviceId) {
		DeviceDetailResponseDto responseDto = deviceService.getDeviceDetail(deviceId);

		return ResponseEntity.ok().body(responseDto);
	}

	@GetMapping("/{device-id}/plans/{plan-id}")
	@ApiOperation(value = "상세 가격 조회", notes = "상세 가격 정보를 조회할 수 있다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "상세 가격 조회 성공"),
		@ApiResponse(code = 404, message = "존재하지 않는 데이터"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<PriceDetailResponseDto> getPriceDetail(
		@PathVariable("device-id") Long deviceId,
		@PathVariable("plan-id") Long planId,
		@RequestParam("discount-type") int discountType,
		@RequestParam(name = "installment-period", defaultValue = "24") int installmentPeriod
	) {
		PriceDetailResponseDto responseDto = deviceService.getPriceDetail(deviceId, planId,
			discountType, installmentPeriod);

		return ResponseEntity.ok().body(responseDto);
	}

	@GetMapping("/{device-id}/self")
	@ApiOperation(value = "동일 기기 비교 조회", notes = "동일 기기 비교 조회할 수 있다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "단말기 리스트 조회 성공"),
		@ApiResponse(code = 404, message = "존재하지 않는 데이터"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<DeviceSelfCompResponseDto> getDeviceSelfComp(
		@PathVariable("device-id") Long deviceId) {
		DeviceSelfCompResponseDto responseDto = deviceService.getDeviceSelfComp(deviceId);

		return ResponseEntity.ok().body(responseDto);
	}
}

