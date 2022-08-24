package com.uplus.backend.search.dto;

import com.uplus.backend.device.entity.Device;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SearchResponseListDto {

	@ApiModelProperty(name = "검색 결과 리스트", example = "")
	private List<SearchResponseDto> searchResponseDtoList;

	public static List<SearchResponseDto> fromEntity(List<Device> deviceList) {
		return deviceList.stream()
			.map(SearchResponseDto::fromEntity)
			.collect(Collectors.toList());
	}
}
