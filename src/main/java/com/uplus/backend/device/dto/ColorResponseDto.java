package com.uplus.backend.device.dto;

import com.uplus.backend.device.entity.Color;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ColorResponseDto {

	private String name;

	private String rgb;

	private int stock;

	private List<ImageResponseDto> images;

	public static ColorResponseDto fromEntity(Color color) {
		return ColorResponseDto.builder()
			.name(color.getName())
			.rgb(color.getRdb())
			.stock(color.getStock())
			.images(color.getImages().stream()
				.map(ImageResponseDto::fromEntity)
				.collect(Collectors.toList()))
			.build();
	}
}
