package com.uplus.backend.launchingdevice.dto;

import com.uplus.backend.launchingdevice.entity.LaunchingDevice;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

/**
 * 담당자 : 김수현
 */
@Getter
@Builder
public class LaunchingDeviceCreateResponseDto {

	@ApiModelProperty(name = "생성된 단말기 식별자", example = "1")
	private Long id;

	public static LaunchingDeviceCreateResponseDto fromEntity(LaunchingDevice launchingDevice) {
		return LaunchingDeviceCreateResponseDto.builder()
			.id(launchingDevice.getId())
			.build();
	}
}
