package com.uplus.backend.plan.dto;

import static com.uplus.backend.global.util.PriceUtil.DEFAULT_MONTH;

import com.uplus.backend.device.entity.Device;
import com.uplus.backend.global.util.PriceUtil;
import com.uplus.backend.plan.entity.Plan;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PlanPriceResponseDto {

	private Long id;

	private String name;

	private int price;

	private int dPrice;

	private int sDiscount;

	public static PlanPriceResponseDto fromEntity(Device device, Plan plan, int discountType,
		int installmentType) {
		return PlanPriceResponseDto.builder()
			.id(plan.getId())
			.name(plan.getName())
			.price(plan.getPrice() * DEFAULT_MONTH / installmentType)
			.dPrice(PriceUtil.planDiscount(device, plan, discountType)
				* DEFAULT_MONTH / installmentType)
			.sDiscount((plan.getPrice() - PriceUtil.planDiscount(device, plan, discountType))
				* DEFAULT_MONTH / installmentType)
			.build();
	}
}
