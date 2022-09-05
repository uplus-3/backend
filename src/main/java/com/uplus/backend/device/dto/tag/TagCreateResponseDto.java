package com.uplus.backend.device.dto.tag;

import com.uplus.backend.device.entity.Tag;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

/**
 * 담당자 : 이일환
 */
@Getter
@Builder
public class TagCreateResponseDto {

	@ApiModelProperty(name = "태그 Id", example = "1")
	private Long id;

	public static TagCreateResponseDto fromEntity(Tag tag) {
		return TagCreateResponseDto.builder()
			.id(tag.getId())
			.build();
	}
}
