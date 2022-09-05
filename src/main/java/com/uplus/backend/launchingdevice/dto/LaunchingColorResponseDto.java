package com.uplus.backend.launchingdevice.dto;

import com.uplus.backend.launchingdevice.entity.LaunchingColor;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

/**
 * 담당자 : 김수현
 */
@Builder
@Getter
public class LaunchingColorResponseDto {

	@ApiModelProperty(name = "출시예정 단말기 식별자", example = "1")
	private Long id;

	@ApiModelProperty(name = "출시예정 단말기 명", example = "아이폰 14")
	private String name;

	@ApiModelProperty(name = "색상코드", example = "#000000")
	private String rgb;

	private List<LaunchingImageResponseDto> launchingImages;

	public static LaunchingColorResponseDto fromEntity(LaunchingColor launchingColor) {
		return LaunchingColorResponseDto.builder()
			.id(launchingColor.getId())
			.name(launchingColor.getName())
			.rgb(launchingColor.getRgb())
			.launchingImages(launchingColor.getLaunchingImages().stream()
				.map(LaunchingImageResponseDto::fromEntity)
				.collect(Collectors.toList()))
			.build();
	}
}
