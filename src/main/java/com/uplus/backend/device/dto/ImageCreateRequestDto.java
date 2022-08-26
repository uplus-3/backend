package com.uplus.backend.device.dto;

import com.uplus.backend.device.entity.Color;
import com.uplus.backend.device.entity.Image;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ImageCreateRequestDto {

	private String url;

	private Long colorId;

	public Image toEntity(Color color) {
		return Image.builder()
			.url(url)
			.color(color)
			.build();
	}
}
