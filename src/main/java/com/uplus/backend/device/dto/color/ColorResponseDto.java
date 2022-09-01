package com.uplus.backend.device.dto.color;

import com.uplus.backend.device.dto.image.ImageResponseDto;
import com.uplus.backend.device.entity.Color;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ColorResponseDto {

	@ApiModelProperty(name = "색상 이름", example = "보라 퍼플")
	private String name;

	@ApiModelProperty(name = "색상코드", example = "#000000")
	private String rgb;

	@ApiModelProperty(name = "해당 색상 단말기 재고", example = "1")
	private int stock;

	private List<ImageResponseDto> images;

	public static ColorResponseDto fromEntity(Color color) {
		return ColorResponseDto.builder()
			.name(color.getName())
			.rgb(color.getRgb())
			.stock(color.getStock())
			.images(color.getImages().stream()
				.map(ImageResponseDto::fromEntity)
				.collect(Collectors.toList()))
			.build();
	}
}
