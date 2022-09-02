package com.uplus.backend.device.dto.price;

import static com.uplus.backend.global.util.PriceUtil.RECOMMENDED_DISCOUNT_TYPE;
import static com.uplus.backend.global.util.PriceUtil.divideByMonth;
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

	private String planName;

	private int discountType;

	private int mDevicePrice;

	private int dDevicePrice;

	private int mPlanPrice;

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
			.planName(plan.getName())
			.discountType(discountType)
			.mPlanPrice(divideByMonth(device.getPrice(), installmentPeriod))
			.dDevicePrice(getDDevicePriceByDiscountType(device, discountType, installmentPeriod))
			.mPlanPrice(plan.getPrice())
			.dPlanPrice(getDPlanPriceByDiscountType(plan, discountType))
			.build();
	}
}
