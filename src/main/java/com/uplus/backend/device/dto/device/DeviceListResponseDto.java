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
public class DeviceListResponseDto {

	private List<DeviceResponseDto> devices;

	public static DeviceListResponseDto fromEntity(List<Device> devices) {
		return DeviceListResponseDto.builder()
			.devices(devices.stream()
				.map(DeviceResponseDto::fromEntity)
				.collect(Collectors.toList()))
			.build();
	}
}
