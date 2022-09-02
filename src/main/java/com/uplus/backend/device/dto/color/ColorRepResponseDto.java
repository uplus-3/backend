package com.uplus.backend.device.dto.color;

import com.uplus.backend.device.entity.Color;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ColorRepResponseDto {

	@ApiModelProperty(name = "색상 식별자", example = "1")
	private Long id;

	private String name;

	private String rgb;

	private String imageUrl;

	public static ColorRepResponseDto fromEntity(Color color) {
		return ColorRepResponseDto.builder()
			.id(color.getId())
			.name(color.getName())
			.rgb(color.getRgb())
			.imageUrl(color.getImages().get(0).getImageUrl())
			.build();
	}
}
