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
public class DeviceDetailResponseDto {

	@ApiModelProperty(name = "단말기 식별자", example = "1")
	private Long id;

	@ApiModelProperty(name = "시리얼번호", example = "A2633-128")
	private String serialNumber;

	@ApiModelProperty(name = "단말기 명", example = "아이폰 13 128GB")
	private String name;

	@ApiModelProperty(name = "저장 용량", example = "128GB")
	private String storage;

	@ApiModelProperty(name = "CPU", example = "Apple A14 Bionic")
	private String cpu;

	@ApiModelProperty(name = "디스플레이", example = "15.4cm")
	private String display;

	@ApiModelProperty(name = "정상가", example = "1078000")
	private int price;

	@ApiModelProperty(name = "정상가 / 약정 달수", example = "44916")
	private int mPrice;

	@ApiModelProperty(name = "할인 적용된 월 납부금액(단말기만)", example = "40000")
	private int dPrice;

	@ApiModelProperty(name = "공시지원금", example = "200000")
	private int pSupport;

	@ApiModelProperty(name = "추가지원금", example = "50000")
	private int aSupport;

	@ApiModelProperty(name = "요금제", example = "요금제")
	private PlanPriceResponseDto plan;

	@ApiModelProperty(name = "단말기 태그들", example = "단말기 태그들")
	private List<TagResponseDto> tags;

	@ApiModelProperty(name = "색상들", example = "색상들")
	private List<ColorResponseDto> colors;

	public static DeviceDetailResponseDto fromEntity(Device device, Plan plan, int discountType,
		int installmentPeriod) {
		return DeviceDetailResponseDto.builder()
			.id(device.getId())
			.serialNumber(device.getSerialNumber())
			.name(device.getName())
			.storage(device.getStorage())
			.cpu(device.getCpu())
			.display(device.getDisplay())
			.price(device.getPrice())
			.mPrice(device.getPrice() / installmentPeriod)
			.dPrice(PriceUtil.deviceDiscount(device, plan, discountType) / installmentPeriod)
			.pSupport(device.getPublicSupport())
			.aSupport(device.getAdditionalSupport())
			.plan(PlanPriceResponseDto.fromEntity(device, plan, discountType, installmentPeriod))
			.tags(device.getTags().stream()
				.map(TagResponseDto::fromEntity)
				.collect(Collectors.toList()))
			.colors(device.getColors().stream()
				.map(ColorResponseDto::fromEntity)
				.collect(Collectors.toList()))

			.build();
	}
}
