package com.uplus.backend.launchingdevice.controller;

import com.uplus.backend.launchingdevice.dto.LaunchingDeviceCreateRequestDto;
import com.uplus.backend.launchingdevice.dto.LaunchingDeviceCreateResponseDto;
import com.uplus.backend.launchingdevice.dto.LaunchingDeviceListResponseDto;
import com.uplus.backend.launchingdevice.service.LaunchingDeviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 담당자 : 김수현
 */
@Api(value = "출시 예정 단말기 API", tags = {"LaunchingDevice"})
@RestController
@RequestMapping("api/launching-devices")
@RequiredArgsConstructor
public class LaunchingDeviceController {

	private final LaunchingDeviceService launchingDeviceService;

	@PostMapping("")
	@ApiOperation(value = "출시 예정 단말기 생성", notes = "출시 예정 단말기를 생성할 수 있다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "출시 예정 단말기 생성 성공")
	})
	public ResponseEntity<LaunchingDeviceCreateResponseDto> create(
		@Valid @RequestBody LaunchingDeviceCreateRequestDto requestDto) {
		LaunchingDeviceCreateResponseDto responseDto = launchingDeviceService.create(requestDto);

		return ResponseEntity.ok().body(responseDto);
	}

	@GetMapping("")
	@ApiOperation(value = "출시 예정 단말기 리스트 조회", notes = "출시 예정 단말기 리스트를 조회할 수 있다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "출시 예정 단말기 리스트 조회 성공")
	})
	public ResponseEntity<LaunchingDeviceListResponseDto> getLaunchingDevices(
		@RequestParam("network-type") int networkType) {
		LaunchingDeviceListResponseDto responseDto = launchingDeviceService.getLaunchingDevices(
			networkType);
		return ResponseEntity.ok().body(responseDto);
	}
}
