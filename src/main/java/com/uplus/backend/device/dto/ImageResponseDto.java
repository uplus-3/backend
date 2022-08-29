package com.uplus.backend.device.dto;

import com.uplus.backend.device.entity.Image;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ImageResponseDto {

	@ApiModelProperty(name = "이미지 url", example = "이미지 url 해당 이미지")
	private String imageUrl;

	public static ImageResponseDto fromEntity(Image image) {
		return ImageResponseDto.builder()
			.imageUrl(image.getUrl())
			.build();
	}
}
