package com.uplus.backend.search.controller;


import com.uplus.backend.order.dto.OrderResponseDto;
import com.uplus.backend.search.dto.SearchResponseDto;
import com.uplus.backend.search.service.SearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 검색 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Slf4j
@Api(value = "검색 API", tags = {"Search"})
@RestController
@RequestMapping("api/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

	@GetMapping("")
	@ApiOperation(value = "검색 키워드 조회", notes = "전송 받은 검색 키워드와 검색 기준 유형으로 조회합니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "검색 성공"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<List<SearchResponseDto>> search(
		@RequestParam("q") String query, @RequestParam("network-type") int networkType) {

		List<SearchResponseDto> searchResponseListDto = searchService.search(query, networkType);

		return ResponseEntity.ok(searchResponseListDto);
	}

}

