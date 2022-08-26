package com.uplus.backend.device.controller;

import com.uplus.backend.device.dto.TagCreateRequestDto;
import com.uplus.backend.device.dto.TagCreateResponseDto;
import com.uplus.backend.device.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(value = "태그 API", tags = {"Tag"})
@RestController
@RequestMapping("api/tags")
@RequiredArgsConstructor
public class TagController {

	private final TagService tagService;

	@PostMapping("")
	@ApiOperation(value = "태그 생성", notes = "태그를 생성할 수 있다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "태그 생성 성공")
	})
	public ResponseEntity<TagCreateResponseDto> create(
		@RequestBody TagCreateRequestDto reqeustDto) {
		TagCreateResponseDto responseDto = tagService.create(reqeustDto);

		return ResponseEntity.ok().body(responseDto);
	}
}
