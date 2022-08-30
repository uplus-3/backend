package com.uplus.backend.search.dto;

import com.uplus.backend.device.dto.ColorResponseDto;
import com.uplus.backend.device.dto.TagResponseDto;
import com.uplus.backend.device.entity.Device;
import com.uplus.backend.global.util.PriceUtil;
import com.uplus.backend.plan.dto.PlanPriceResponseDto;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SearchResponseDto {

	@ApiModelProperty(name = "단말기 ID", example = "123")
	private Long id;

	@ApiModelProperty(name = "시리얼번호", example = "A2633-128")
	private String serialNumber;

	@ApiModelProperty(name = "단말기 명", example = "갤럭시 S20 중고폰")
	private String name;

	@ApiModelProperty(name = "저장 용량", example = "128GB")
	private String storage;

	@ApiModelProperty(name = "CPU", example = "Apple A14 Bionic")
	private String cpu;

	@ApiModelProperty(name = "디스플레이", example = "15.4cm")
	private String display;

	@ApiModelProperty(name = "단말기 정상가", example = "2000000")
	private int price;

	@ApiModelProperty(name = "단말기 월 납부금액(정상가 / 약정 달수)", example = "120000")
	private int mPrice;

	@ApiModelProperty(name = "기기 월 납부금액(할인 적용)", example = "56420")
	private int dPrice;

	@ApiModelProperty(name = "정상가 - 공시 지원금 - 추가 지원금", example = "828000")
	private int tPrice;

	@ApiModelProperty(name = "할인유형", example = "0")
	private int discountType;

	@ApiModelProperty(name = "공시지원금", example = "200000")
	private int pSupport;

	@ApiModelProperty(name = "추가지원금", example = "50000")
	private int aSupport;

	@ApiModelProperty(name = "요금제 정보 리스트")
	private PlanPriceResponseDto plan;

	@ApiModelProperty(name = "태그 정보 리스트")
	private List<TagResponseDto> tags;

	@ApiModelProperty(name = "색상 정보 리스트")
	private List<ColorResponseDto> colors;

	public static SearchResponseDto fromEntity(Device device) {
		return SearchResponseDto.builder()
			.id(device.getId())
			.serialNumber(device.getSerialNumber())
			.name(device.getName())
			.storage(device.getStorage())
			.cpu(device.getCpu())
			.display(device.getDisplay())
			.price(device.getPrice())
			.mPrice(device.getPrice() / PriceUtil.DEFAULT_MONTH)
			.dPrice(PriceUtil.getDiscountedDevicePriceByDiscountType(device, device.getPlan(),
				PriceUtil.getRecommendedDiscountType(device, device.getPlan(),
					PriceUtil.RECOMMENDED_DISCOUNT_TYPE)) / PriceUtil.DEFAULT_MONTH)
			.discountType(PriceUtil.getRecommendedDiscountType(device, device.getPlan(),
				PriceUtil.RECOMMENDED_DISCOUNT_TYPE))
			.plan(PlanPriceResponseDto.fromEntity(device, device.getPlan(),
				PriceUtil.getRecommendedDiscountType(device, device.getPlan(),
					PriceUtil.RECOMMENDED_DISCOUNT_TYPE), PriceUtil.DEFAULT_MONTH))
			.tags(device.getTags().stream()
				.map(TagResponseDto::fromEntity)
				.collect(Collectors.toList()))
			.colors(device.getColors().stream()
				.map(ColorResponseDto::fromEntity)
				.collect(Collectors.toList()))
			.build();
	}
}
