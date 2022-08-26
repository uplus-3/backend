package com.uplus.backend.device.dto;

import com.uplus.backend.device.entity.Device;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeviceCreateResponseDto {

	private Long id;

	public static DeviceCreateResponseDto fromEntity(Device device) {
		return DeviceCreateResponseDto.builder()
			.id(device.getId())
			.build();
	}
}
