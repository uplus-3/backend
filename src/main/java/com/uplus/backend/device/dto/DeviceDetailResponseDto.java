package com.uplus.backend.device.dto;

import static com.uplus.backend.global.util.PriceUtil.divideByDefaultMonth;
import static com.uplus.backend.global.util.PriceUtil.getDiscountedDevicePriceByDiscountType;
import static com.uplus.backend.global.util.PriceUtil.getRecommendedDiscountType;

import com.uplus.backend.device.entity.Device;
import com.uplus.backend.plan.dto.PlanPriceResponseDto;
import com.uplus.backend.plan.entity.Plan;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
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

	@ApiModelProperty(name = "출시일", example = "2022-02-02")
	private Date launchedDate;

	@ApiModelProperty(name = "정상가", example = "1078000")
	private int price;

	@ApiModelProperty(name = "정상가 / 할부 달수", example = "44910")
	private int mPrice;

	@ApiModelProperty(name = "할인 적용된 월 납부금액(단말기만)", example = "40000")
	private int dPrice;

	@ApiModelProperty(name = "정상가 - 공시 지원금 - 추가 지원금", example = "828000")
	private int tPrice;

	@ApiModelProperty(name = "할인유형", example = "0")
	private int discountType;

	@ApiModelProperty(name = "공시지원금", example = "200000")
	private int pSupport;

	@ApiModelProperty(name = "추가지원금", example = "50000")
	private int aSupport;

	private PlanPriceResponseDto plan;

	private List<TagResponseDto> tags;

	private List<ColorResponseDto> colors;

	public static DeviceDetailResponseDto fromEntity(Device device, Plan plan, int discountType,
		int installmentPeriod) {
		if (plan == null) {
			plan = device.getPlan();
		}

		int mPrice = divideByDefaultMonth(device.getPrice());
		int tPrice = getDiscountedDevicePriceByDiscountType(device, plan, discountType);
		int dPrice = divideByDefaultMonth(tPrice);

		return DeviceDetailResponseDto.builder()
			.id(device.getId())
			.serialNumber(device.getSerialNumber())
			.name(device.getName())
			.storage(device.getStorage())
			.cpu(device.getCpu())
			.display(device.getDisplay())
			.launchedDate(device.getLaunchedDate())
			.price(device.getPrice())
			.mPrice(mPrice)
			.dPrice(dPrice)
			.tPrice(tPrice)
			.pSupport(device.getPublicSupport())
			.aSupport(device.getAdditionalSupport())
			.discountType(getRecommendedDiscountType(device, plan, discountType))
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
