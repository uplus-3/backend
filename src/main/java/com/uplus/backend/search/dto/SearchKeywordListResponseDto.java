package com.uplus.backend.search.dto;

import com.uplus.backend.device.entity.Device;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SearchKeywordListResponseDto {

	@ApiModelProperty(name = "검색 결과 리스트")
	private List<SearchKeywordResponseDto> searchKeywordList;

	public static SearchKeywordListResponseDto fromEntity(List<Device> searchKeywordList) {
		return SearchKeywordListResponseDto.builder()
			.searchKeywordList(searchKeywordList.stream()
				.map(SearchKeywordResponseDto::fromEntity)
				.collect(Collectors.toList()))
			.build();
	}
}
