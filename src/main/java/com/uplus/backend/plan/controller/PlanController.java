package com.uplus.backend.plan.controller;


import com.uplus.backend.plan.dto.PlanCreateRequestDto;
import com.uplus.backend.plan.dto.PlanCreateResponseDto;
import com.uplus.backend.plan.dto.PlanListResponseDto;
import com.uplus.backend.plan.service.PlanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 담당자 : 윤병찬
 * 요금제 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Slf4j
@Api(value = "요금제 API", tags = {"Plan"})
@RestController
@RequestMapping("api/plans")
@RequiredArgsConstructor
public class PlanController {

	private final PlanService planservice;

	@PostMapping("")
	@ApiOperation(value = "요금제 생성", notes = "요금제 생성")
	@ApiResponses({
		@ApiResponse(code = 200, message = "요금제 리스트 조회 성공"),
	})
	public ResponseEntity<PlanCreateResponseDto> create(
		@RequestBody PlanCreateRequestDto requestDto) {
		PlanCreateResponseDto responseDto = planservice.create(requestDto);

		return ResponseEntity.ok().body(responseDto);
	}

	@GetMapping("")
	@ApiOperation(value = "요금제 리스트 조회", notes = "네트워크 유형(0 : 전체 조회, 4 : 4G, 5 : 5G) 으로 요금제 리스트 조회")
	@ApiResponses({
		@ApiResponse(code = 200, message = "요금제 리스트 조회 성공"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<PlanListResponseDto> findByNetworkType(
		@RequestParam(name = "network-type", required = false, defaultValue = "0") int networkType) {

		PlanListResponseDto planListResponseDto = planservice.findByNetworkType(networkType);

		return ResponseEntity.ok().body(planListResponseDto);
	}

}

