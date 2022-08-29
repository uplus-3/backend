package com.uplus.backend.device.dto;

import com.uplus.backend.device.entity.Color;
import com.uplus.backend.device.entity.Image;
import io.swagger.annotations.ApiModelProperty;
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

	@ApiModelProperty(name = "이미지URL", example = "이미지URL")
	private String url;

	@ApiModelProperty(name = "색상 식별자", example = "1")
	private Long colorId;

	public Image toEntity(Color color) {
		return Image.builder()
			.url(url)
			.color(color)
			.build();
	}
}
