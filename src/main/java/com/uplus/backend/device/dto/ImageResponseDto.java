package com.uplus.backend.device.dto;

import com.uplus.backend.device.entity.Image;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ImageResponseDto {

	private String url;

	public static ImageResponseDto fromEntity(Image image) {
		return ImageResponseDto.builder()
			.url(image.getUrl())
			.build();
	}
}
