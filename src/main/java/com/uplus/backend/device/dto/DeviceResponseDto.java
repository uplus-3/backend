package com.uplus.backend.device.dto;

import com.uplus.backend.device.entity.Device;
import com.uplus.backend.global.util.PriceUtil;
import com.uplus.backend.plan.dto.PlanPriceResponseDto;
import com.uplus.backend.plan.entity.Plan;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeviceResponseDto {

	@ApiModelProperty(name = "단말기 식별자", example = "1")
	private Long id;

	@ApiModelProperty(name = "시리얼번호", example = "A2633-128")
	private String serialNumber;

	@ApiModelProperty(name = "단말기 명", example = "아이폰 13 128GB")
	private String name;

	@ApiModelProperty(name = "정상가", example = "1078000")
	private int price;

	@ApiModelProperty(name = "정상가 / 약정 달수", example = "44916")
	private int mPrice;

	@ApiModelProperty(name = "할인 적용된 월 납부금액(단말기만)", example = "40000")
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
			.dPrice(PriceUtil.deviceDiscount(device, plan != null ? plan : device.getPlan(),
				discountType) / installmentPeriod)
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
