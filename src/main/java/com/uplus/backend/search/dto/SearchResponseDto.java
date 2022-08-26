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

	@ApiModelProperty(name = "기기 ID", example = "123")
	private Long id;

	@ApiModelProperty(name = "기기명", example = "갤럭시 S20 중고폰")
	private String name;

	@ApiModelProperty(name = "기기 정상가", example = "2000000")
	private int price;

	@ApiModelProperty(name = "기기 월 납부금액", example = "120000")
	private int mPrice;

	@ApiModelProperty(name = "기기 월 납부금액(할인 적용)", example = "56420")
	private int dPrice;

	@ApiModelProperty(name = "요금제 정보 리스트")
	private PlanPriceResponseDto plan;

	@ApiModelProperty(name = "태그 정보 리스트")
	private List<TagResponseDto> tags;

	@ApiModelProperty(name = "색상 정보 리스트")
	private List<ColorResponseDto> colors;

	public static SearchResponseDto fromEntity(Device device) {
		int recommendedDiscountType =
			PriceUtil.deviceDiscount(device, device.getPlan(), 0) <= PriceUtil.deviceDiscount(device, device.getPlan(), 1) ? 0 : 1;

		return SearchResponseDto.builder()
			.id(device.getId())
			.name(device.getName())
			.price(device.getPrice())
			.mPrice(device.getPrice() / PriceUtil.DEFAULT_MONTH)
			.dPrice(
				PriceUtil.deviceDiscount(device, device.getPlan(), recommendedDiscountType) / PriceUtil.DEFAULT_MONTH)
			.plan(PlanPriceResponseDto.fromEntity(device, device.getPlan(), recommendedDiscountType,
				PriceUtil.DEFAULT_MONTH))
			.tags(device.getTags().stream()
				.map(TagResponseDto::fromEntity)
				.collect(Collectors.toList()))
			.colors(device.getColors().stream()
				.map(ColorResponseDto::fromEntity)
				.collect(Collectors.toList()))
			.build();
	}
}
