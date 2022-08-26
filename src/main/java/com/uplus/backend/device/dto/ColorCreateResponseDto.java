package com.uplus.backend.device.dto;

import com.uplus.backend.device.entity.Color;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ColorCreateResponseDto {

	private Long id;

	public static ColorCreateResponseDto fromEntity(Color color) {
		return ColorCreateResponseDto.builder()
			.id(color.getId())
			.build();
	}
}
