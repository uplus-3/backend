package com.uplus.backend.plan.dto;

import static com.uplus.backend.global.util.PriceUtil.getDiscountedPlanPriceByDiscountType;

import com.uplus.backend.plan.entity.Plan;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PlanPriceResponseDto {

	@ApiModelProperty(name = "요금제 ID", example = "1")
	private Long id;

	@ApiModelProperty(name = "요금제 명", example = "5G+ 프리미엄 요금제")
	private String name;

	@ApiModelProperty(name = "요금제 금액", example = "59000")
	private int price;

	@ApiModelProperty(name = "요금제 월 납부금액(할인 적용)", example = "53420")
	private int dPrice;

	@ApiModelProperty(name = "요금제 선택약정 할인금액", example = "53420 * 25%")
	private int sDiscount;

	public static PlanPriceResponseDto fromEntity(Plan plan, int discountType) {
		return PlanPriceResponseDto.builder()
			.id(plan.getId())
			.name(plan.getName())
			.price(plan.getPrice())
			.dPrice(getDiscountedPlanPriceByDiscountType(plan, discountType))
			.sDiscount(plan.getPrice() - getDiscountedPlanPriceByDiscountType(plan, discountType))
			.build();
	}
}
