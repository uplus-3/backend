package com.uplus.backend.device.dto;

import com.uplus.backend.device.entity.Device;
import com.uplus.backend.global.util.PriceUtil;
import com.uplus.backend.plan.dto.PlanPriceResponseDto;
import com.uplus.backend.plan.entity.Plan;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeviceResponseDto {

	private Long id;

	private String serialNumber;

	private String name;

	private int price;

	private int mPrice;

	private int dPrice;

	private PlanPriceResponseDto plan;

	private List<TagResponseDto> tags;

	private List<ColorResponseDto> colors;

	public static DeviceResponseDto fromEntity(Device device, Plan plan, int discountType,
		int installmentPeriod) {
		return DeviceResponseDto.builder()
			.id(device.getId())
			.serialNumber(device.getSerialNumber())
			.name(device.getName())
			.price(device.getPrice())
			.mPrice(device.getPrice() / installmentPeriod)
			.dPrice(PriceUtil.deviceDiscount(device, plan, discountType) / installmentPeriod)
			.plan(PlanPriceResponseDto.fromEntity(device, plan != null ? plan : device.getPlan(),
				discountType, installmentPeriod))
			.tags(device.getTags().stream()
				.map(TagResponseDto::fromEntity)
				.collect(Collectors.toList()))
			.colors(device.getColors().stream()
				.map(ColorResponseDto::fromEntity)
				.collect(Collectors.toList()))
			.build();
	}
}
