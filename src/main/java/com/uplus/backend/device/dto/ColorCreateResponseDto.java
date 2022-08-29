package com.uplus.backend.device.dto;

import com.uplus.backend.device.entity.Color;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ColorCreateResponseDto {

	@ApiModelProperty(name = "생성된 색상 식별자", example = "1")
	private Long id;

	public static ColorCreateResponseDto fromEntity(Color color) {
		return ColorCreateResponseDto.builder()
			.id(color.getId())
			.build();
	}
}
