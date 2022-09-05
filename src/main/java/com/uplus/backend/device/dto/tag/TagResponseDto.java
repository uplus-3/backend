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
public class TagResponseDto {

	@ApiModelProperty(name = "태그 내용", example = "최신")
	private String content;

	@ApiModelProperty(name = "색상값", example = "#0E0E0E")
	private String rgb;

	public static TagResponseDto fromEntity(Tag tag) {
		return TagResponseDto.builder()
			.content(tag.getContent())
			.rgb(tag.getRgb())
			.build();
	}
}
