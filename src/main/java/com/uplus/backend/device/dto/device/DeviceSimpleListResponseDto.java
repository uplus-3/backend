package com.uplus.backend.device.dto.device;

import com.uplus.backend.device.entity.Device;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

/**
 * 담당자 : 이일환
 */
@Getter
@Builder
public class DeviceSimpleListResponseDto {

	private List<DeviceSimpleResponseDto> devices;

	public static DeviceSimpleListResponseDto fromEntity(List<Device> devices) {
		return DeviceSimpleListResponseDto.builder()
			.devices(devices.stream()
				.map(DeviceSimpleResponseDto::fromEntity)
				.collect(Collectors.toList()))
			.build();
	}
}
