package com.uplus.backend.device.dto;

import com.uplus.backend.device.entity.Device;
import com.uplus.backend.plan.entity.Plan;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeviceListResponseDto {

	private List<DeviceResponseDto> devices;

	public static DeviceListResponseDto fromEntity(List<Device> devices, Plan plan,
		int discountType, int installmentPeriod) {
		return DeviceListResponseDto.builder()
			.devices(devices.stream()
				.map(device -> DeviceResponseDto.fromEntity(device, plan, discountType,
					installmentPeriod))
				.collect(Collectors.toList()))
			.build();
	}
}
