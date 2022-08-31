package com.uplus.backend.device.dto;

import static com.uplus.backend.global.util.PriceUtil.RECOMMENDED_DISCOUNT_TYPE;
import static com.uplus.backend.global.util.PriceUtil.getDDevicePriceByDiscountType;
import static com.uplus.backend.global.util.PriceUtil.getDPlanPriceByDiscountType;
import static com.uplus.backend.global.util.PriceUtil.getRecommendedDiscountType;

import com.uplus.backend.device.entity.Device;
import com.uplus.backend.plan.entity.Plan;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PriceResponseDto {

	private Long deviceId;

	private Long planId;

	private String planName;

	private int discountType;

	private int dDevicePrice;

	private int dPlanPrice;

	public static PriceResponseDto fromEntity(Device device, Plan plan, int discountType,
		int installmentPeriod) {
		if (plan == null) {
			plan = device.getPlan();
		}

		if (discountType == RECOMMENDED_DISCOUNT_TYPE) {
			discountType = getRecommendedDiscountType(device, plan);
		}

		return PriceResponseDto.builder()
			.deviceId(device.getId())
			.planId(plan.getId())
			.planName(plan.getName())
			.discountType(discountType)
			.dDevicePrice(getDDevicePriceByDiscountType(device, discountType, installmentPeriod))
			.dPlanPrice(getDPlanPriceByDiscountType(plan, discountType))
			.build();
	}
}
