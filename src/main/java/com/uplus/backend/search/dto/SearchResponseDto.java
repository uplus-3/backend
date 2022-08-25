package com.uplus.backend.search.dto;

import com.uplus.backend.device.entity.Device;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SearchResponseDto {

	@ApiModelProperty(name = "기기 ID", example = "123")
	private Long id;

	@ApiModelProperty(name = "기기명", example = "갤럭시 S20 중고폰")
	private String name;

	@ApiModelProperty(name = "이미지 url", example = "이미지 url 해당 이미지")
	private String imageUrl;

	public static SearchResponseDto fromEntity(Device device) {
		return SearchResponseDto.builder()
			.id(device.getId())
			.name(device.getName())
			.imageUrl(device.getRepresentativeImageUrl())
			.build();
	}
}
