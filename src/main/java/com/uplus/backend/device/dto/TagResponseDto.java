package com.uplus.backend.device.dto;

import com.uplus.backend.device.entity.Tag;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TagResponseDto {

	private String content;

	private String rgb;

	public static TagResponseDto fromEntity(Tag tag) {
		return TagResponseDto.builder()
			.content(tag.getContent())
			.rgb(tag.getRgb())
			.build();
	}
}
