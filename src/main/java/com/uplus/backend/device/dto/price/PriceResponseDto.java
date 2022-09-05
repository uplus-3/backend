package com.uplus.backend.device.dto.price;

import static com.uplus.backend.global.util.PriceUtil.RECOMMENDED_DISCOUNT_TYPE;
import static com.uplus.backend.global.util.PriceUtil.divideByMonth;
import static com.uplus.backend.global.util.PriceUtil.getDDevicePriceByDiscountType;
import static com.uplus.backend.global.util.PriceUtil.getDPlanPriceByDiscountType;
import static com.uplus.backend.global.util.PriceUtil.getRecommendedDiscountType;

import com.uplus.backend.device.entity.Device;
import com.uplus.backend.plan.entity.Plan;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

/**
 * 담당자 : 이일환
 */
@Getter
@Builder
public class PriceResponseDto {

	@ApiModelProperty(name = "단말기 식별자", example = "1")
	private Long deviceId;

	@ApiModelProperty(name = "요금제명", example = "5G 슬림")
	private String planName;

	@ApiModelProperty(name = "할인 유형", example = "0")
	private int discountType;

	@ApiModelProperty(name = "단말기 할인 전 월 납부액", example = "59000")
	private int mDevicePrice;

	@ApiModelProperty(name = "단말기 할인 후 월 납부액", example = "40000")
	private int dDevicePrice;

	@ApiModelProperty(name = "할인 전 요금제 월 납부액", example = "59000")
	private int mPlanPrice;

	@ApiModelProperty(name = "할인 후 요금제 월 납부액", example = "40000")
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
