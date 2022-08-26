package com.uplus.backend.device.dto;

import com.uplus.backend.device.entity.Tag;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TagCreateResponseDto {

	private Long id;

	public static TagCreateResponseDto fromEntity(Tag tag) {
		return TagCreateResponseDto.builder()
			.id(tag.getId())
			.build();
	}
}
