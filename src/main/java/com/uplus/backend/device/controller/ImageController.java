package com.uplus.backend.device.controller;

import com.uplus.backend.device.dto.image.ImageCreateRequestDto;
import com.uplus.backend.device.dto.image.ImageCreateResponseDto;
import com.uplus.backend.device.service.ImageService;
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

@Slf4j
@Api(value = "이미지 API", tags = {"Image"})
@RestController
@RequestMapping("api/images")
@RequiredArgsConstructor
public class ImageController {

	private final ImageService imageService;

	@PostMapping("")
	@ApiOperation(value = "이미지 생성", notes = "이미지를 생성할 수 있다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "이미지 생성 성공"),
		@ApiResponse(code = 404, message = "존재하지 않는 데이터"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<ImageCreateResponseDto> create(
		@Valid @RequestBody ImageCreateRequestDto requestDto) {
		ImageCreateResponseDto responseDto = imageService.create(requestDto);

		return ResponseEntity.ok().body(responseDto);
	}
}
