package com.uplus.backend.device.dto.price;

import static com.uplus.backend.global.util.PriceUtil.RECOMMENDED_DISCOUNT_TYPE;
import static com.uplus.backend.global.util.PriceUtil.SELECT_INSTALLMENT_DISCOUNT_TYPE;
import static com.uplus.backend.global.util.PriceUtil.divideByMonth;
import static com.uplus.backend.global.util.PriceUtil.getDPlanPriceByDiscountType;
import static com.uplus.backend.global.util.PriceUtil.getRecommendedDiscountType;

import com.uplus.backend.device.entity.Device;
import com.uplus.backend.global.util.PriceUtil;
import com.uplus.backend.plan.entity.Plan;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PriceDetailResponseDto {

	@ApiModelProperty(name = "단말기 정상가", example = "1078000")
	private int devicePrice;

	@ApiModelProperty(name = "식별 번호", example = "1234-5678")
	private String serialNumber;

	@ApiModelProperty(name = "정상가 / 할부 달수", example = "44910")
	private int mDevicePrice;

	@ApiModelProperty(name = "할인 적용된 월 납부금액(단말기만)", example = "40000")
	private int dDevicePrice;

	@ApiModelProperty(name = "정상가 - 공시 지원금 - 추가 지원금", example = "828000")
	private int tDevicePrice;

	@ApiModelProperty(name = "할인유형", example = "0")
	private int discountType;

	@ApiModelProperty(name = "공시지원금", example = "200000")
	private int pSupport;

	@ApiModelProperty(name = "추가지원금", example = "50000")
	private int aSupport;

	@ApiModelProperty(name = "요금제 금액", example = "59000")
	private int mPlanPrice;

	@ApiModelProperty(name = "요금제 월 납부금액(할인 적용)", example = "53420")
	private int dPlanPrice;

	@ApiModelProperty(name = "요금제 선택약정 할인금액", example = "53420 * 25%")
	private int sDiscount;

	public static PriceDetailResponseDto fromEntity(Device device, Plan plan, int discountType,
		int installmentPeriod) {
		if (plan == null) {
			plan = device.getPlan();
		}
		if (discountType == RECOMMENDED_DISCOUNT_TYPE) {
			discountType = getRecommendedDiscountType(device, plan);
		}

		int mDevicePrice = divideByMonth(device.getPrice(), installmentPeriod);
		int tDevicePrice = PriceUtil.getTDevicePriceByDiscountType(device, discountType);
		int dDevicePrice = divideByMonth(tDevicePrice, installmentPeriod);
		int dPlanPrice = getDPlanPriceByDiscountType(plan, discountType);
		int sDiscount = plan.getPrice() - getDPlanPriceByDiscountType(plan,
			SELECT_INSTALLMENT_DISCOUNT_TYPE);

		return PriceDetailResponseDto.builder()
			.devicePrice(device.getPrice())
			.serialNumber(device.getSerialNumber())
			.mDevicePrice(mDevicePrice)
			.dDevicePrice(dDevicePrice)
			.tDevicePrice(tDevicePrice)
			.pSupport(device.getPublicSupport())
			.aSupport(device.getAdditionalSupport())
			.discountType(discountType)
			.mPlanPrice(plan.getPrice())
			.dPlanPrice(dPlanPrice)
			.sDiscount(sDiscount)
			.build();
	}
}
