package com.uplus.backend.device.controller;

import com.uplus.backend.device.dto.color.ColorCreateRequestDto;
import com.uplus.backend.device.dto.color.ColorCreateResponseDto;
import com.uplus.backend.device.service.ColorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 담당자 : 이일환
 */
@Slf4j
@Api(value = "색상 API", tags = {"Color"})
@RestController
@RequestMapping("/api/colors")
@RequiredArgsConstructor
public class ColorController {

	private final ColorService colorService;

	@PostMapping("")
	@ApiOperation(value = "색상 생성", notes = "색상을 생성할 수 있다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "색상 생성 성공"),
		@ApiResponse(code = 404, message = "존재하지 않는 데이터"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<ColorCreateResponseDto> create(
		@Valid @RequestBody ColorCreateRequestDto requestDto) {
		ColorCreateResponseDto responseDto = colorService.create(requestDto);

		return ResponseEntity.ok().body(responseDto);
	}
}
