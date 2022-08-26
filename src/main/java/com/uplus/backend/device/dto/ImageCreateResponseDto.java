package com.uplus.backend.device.dto;

import com.uplus.backend.device.entity.Image;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ImageCreateResponseDto {

	private Long id;

	public static ImageCreateResponseDto fromEntity(Image image) {
		return ImageCreateResponseDto.builder()
			.id(image.getId())
			.build();
	}
}
