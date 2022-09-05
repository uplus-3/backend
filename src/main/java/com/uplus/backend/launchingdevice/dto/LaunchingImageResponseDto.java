package com.uplus.backend.launchingdevice.dto;

import com.uplus.backend.launchingdevice.entity.LaunchingImage;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

/**
 * 담당자 : 김수현
 */
@Builder
@Getter
public class LaunchingImageResponseDto {

	@ApiModelProperty(name = "이미지 id", example = "1")
	private Long id;

	@ApiModelProperty(name = "이미지 URL", example = "http://uplus.com/images.jpg")
	private String imageUrl;

	public static LaunchingImageResponseDto fromEntity(LaunchingImage launchingImage) {
		return LaunchingImageResponseDto.builder()
			.id(launchingImage.getId())
			.imageUrl(launchingImage.getImageUrl())
			.build();
	}

}
