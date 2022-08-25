package com.uplus.backend.search.dto;

import com.uplus.backend.device.entity.Device;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SearchListResponseDto {

	@ApiModelProperty(name = "검색 결과 리스트", example = "")
	private List<SearchResponseDto> searchList;

	public static SearchListResponseDto fromEntity(List<Device> searchList) {
		return SearchListResponseDto.builder()
			.searchList(searchList.stream()
				.map(SearchResponseDto::fromEntity)
				.collect(Collectors.toList()))
			.build();
	}
}
