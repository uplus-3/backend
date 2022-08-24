package com.uplus.backend.plan.dto;

import com.uplus.backend.global.util.DiscountUtil;
import com.uplus.backend.plan.entity.Plan;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PlanPriceResponseDto {

	private String name;

	private int price;

	private int dPrice;

	private int sDiscount;

	public static PlanPriceResponseDto fromEntity(Plan plan, int discountType,
		int installmentType) {
		return PlanPriceResponseDto.builder()
			.name(plan.getName())
			.price(plan.getPrice() * DiscountUtil.DEFAULT_MONTH / installmentType)
			.dPrice(DiscountUtil.planDiscount(plan, discountType)
				* DiscountUtil.DEFAULT_MONTH / installmentType)
			.sDiscount((plan.getPrice() - DiscountUtil.planDiscount(plan, discountType))
				* DiscountUtil.DEFAULT_MONTH / installmentType)
			.build();
	}
}
