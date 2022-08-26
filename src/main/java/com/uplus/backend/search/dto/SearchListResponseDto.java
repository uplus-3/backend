package com.uplus.backend.search.dto;

import com.uplus.backend.device.entity.Device;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SearchListResponseDto {

	private List<SearchResponseDto> searchList;

	public static SearchListResponseDto fromEntity(List<Device> searchList) {
		return SearchListResponseDto.builder()
			.searchList(searchList.stream()
				.map(SearchResponseDto::fromEntity)
				.collect(Collectors.toList()))
			.build();
	}
}
