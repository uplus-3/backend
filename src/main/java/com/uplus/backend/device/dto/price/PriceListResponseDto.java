package com.uplus.backend.device.dto.price;

import com.uplus.backend.device.entity.Device;
import com.uplus.backend.plan.entity.Plan;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

/**
 * 담당자 : 이일환
 */
@Getter
@Builder
public class PriceListResponseDto {

	private List<PriceResponseDto> devices;

	public static PriceListResponseDto fromEntity(List<Device> devices, Plan plan,
		int discountType, int installmentPeriod) {
		return PriceListResponseDto.builder()
			.devices(devices.stream()
				.map(device -> PriceResponseDto.fromEntity(device, plan, discountType, installmentPeriod))
				.collect(Collectors.toList()))
			.build();
	}
}
