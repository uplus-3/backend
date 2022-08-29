package com.uplus.backend.device.dto;

import com.uplus.backend.device.entity.Device;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeviceCreateResponseDto {

	@ApiModelProperty(name = "생성된 단말기 식별자", example = "1")
	private Long id;

	public static DeviceCreateResponseDto fromEntity(Device device) {
		return DeviceCreateResponseDto.builder()
			.id(device.getId())
			.build();
	}
}
