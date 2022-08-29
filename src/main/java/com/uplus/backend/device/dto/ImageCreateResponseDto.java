package com.uplus.backend.device.dto;

import com.uplus.backend.device.entity.Image;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ImageCreateResponseDto {

	@ApiModelProperty(name = "이미지 ID", example = "1")
	private Long id;

	public static ImageCreateResponseDto fromEntity(Image image) {
		return ImageCreateResponseDto.builder()
			.id(image.getId())
			.build();
	}
}
