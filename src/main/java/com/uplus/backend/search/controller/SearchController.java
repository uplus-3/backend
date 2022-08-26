package com.uplus.backend.search.controller;


import com.uplus.backend.search.dto.SearchKeywordListResponseDto;
import com.uplus.backend.search.dto.SearchListResponseDto;
import com.uplus.backend.search.service.SearchService;
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
 * 검색 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Slf4j
@Api(value = "검색 API", tags = {"Search"})
@RestController
@RequestMapping("api/search")
@RequiredArgsConstructor
public class SearchController {

	private final SearchService searchService;

	@GetMapping("/keyword")
	@ApiOperation(value = "검색 키워드(연관 검색어) 조회", notes = "전송 받은 검색 키워드와 검색 기준 유형(0 : 전체 조회, 4 : 4G, 5 : 5G)으로 검색 키워드(연관 검색어)를 조회합니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "검색 키워드(연관 검색어) 조회 성공"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<SearchKeywordListResponseDto> getSearchKeyword(
		@RequestParam("q") String query,
		@RequestParam(name = "network-type", required = false, defaultValue = "0") int networkType) {

		SearchKeywordListResponseDto searchKeywordListResponseDto = searchService.getSearchKeyword(
			query, networkType);

		return ResponseEntity.ok().body(searchKeywordListResponseDto);
	}

	@GetMapping("")
	@ApiOperation(value = "검색 결과 리스트 조회", notes = "전송 받은 검색 키워드와 검색 기준 유형(0 : 전체 조회, 4 : 4G, 5 : 5G)으로 데이터를 조회합니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "검색 성공"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<SearchListResponseDto> SearchByKeyword(
		@RequestParam("q") String query,
		@RequestParam(name = "network-type", required = false, defaultValue = "0") int networkType) {

		SearchListResponseDto searchResponseListDto = searchService.SearchByKeyword(query,
			networkType);

		return ResponseEntity.ok().body(searchResponseListDto);
	}

}

