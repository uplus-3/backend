package com.uplus.backend.device.dto;

import com.uplus.backend.device.entity.Image;
import com.uplus.backend.order.entity.Order;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
