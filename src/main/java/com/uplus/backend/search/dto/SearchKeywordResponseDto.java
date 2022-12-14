package com.uplus.backend.search.dto;

import com.uplus.backend.device.entity.Device;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

/**
 * 담당자 : 윤병찬
 */
@Builder
@Getter
public class SearchKeywordResponseDto {

	@ApiModelProperty(name = "기기 ID", example = "123")
	private Long id;

	@ApiModelProperty(name = "기기명", example = "갤럭시 S20 중고폰")
	private String name;

	@ApiModelProperty(name = "이미지 url", example = "이미지 url 해당 이미지")
	private String imageUrl;

	@ApiModelProperty(name = "시리얼번호", example = "A2633-128")
	private String serialNumber;


	public static SearchKeywordResponseDto fromEntity(Device device) {
		return SearchKeywordResponseDto.builder()
			.id(device.getId())
			.name(device.getName())
			.imageUrl(device.getRepImageUrl())
			.serialNumber(device.getSerialNumber())
			.build();
	}
}
