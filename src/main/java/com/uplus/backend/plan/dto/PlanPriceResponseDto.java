package com.uplus.backend.plan.dto;

import com.uplus.backend.global.util.DiscountUtil;
import com.uplus.backend.plan.entity.Plan;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PlanPriceResponseDto {

	private int price;

	private int d_price;

	public static PlanPriceResponseDto fromEntity(Plan plan, int discountType) {
		return PlanPriceResponseDto.builder()
			.price(plan.getPrice())
			.d_price(DiscountUtil.planDiscount(plan, discountType))
			.build();
	}
}
