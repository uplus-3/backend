package com.uplus.backend.device.dto.color;

import com.uplus.backend.device.entity.Color;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ColorRepResponseDto {

	private String name;

	private String rgb;

	private String imageUrl;

	public static ColorRepResponseDto fromEntity(Color color) {
		return ColorRepResponseDto.builder()
			.name(color.getName())
			.rgb(color.getRgb())
			.imageUrl(color.getImages().get(0).getImageUrl())
			.build();
	}
}
