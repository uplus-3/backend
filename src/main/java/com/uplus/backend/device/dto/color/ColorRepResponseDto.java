package com.uplus.backend.device.dto.color;

import com.uplus.backend.device.entity.Color;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

/**
 * 담당자 : 이일환
 */
@Getter
@Builder
public class ColorRepResponseDto {

	@ApiModelProperty(name = "색상 식별자", example = "1")
	private Long id;

	@ApiModelProperty(name = "색상 이름", example = "보라 퍼플")
	private String name;

	@ApiModelProperty(name = "색상 코드", example = "#000000")
	private String rgb;

	@ApiModelProperty(name = "색상별 대표 이미지 URL", example = "#000000")
	private String imageUrl;

	@ApiModelProperty(name = "재고", example = "10")
	private int stock;

	public static ColorRepResponseDto fromEntity(Color color) {
		return ColorRepResponseDto.builder()
			.id(color.getId())
			.name(color.getName())
			.rgb(color.getRgb())
			.imageUrl(color.getImages().get(0).getImageUrl())
			.stock(color.getStock())
			.build();
	}
}
