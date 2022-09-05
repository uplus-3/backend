package com.uplus.backend.global.util;

import com.uplus.backend.device.entity.Device;
import com.uplus.backend.global.exception.CustomException;
import com.uplus.backend.global.exception.ErrorCode;
import com.uplus.backend.plan.entity.Plan;
import java.util.List;

/**
 * 담당자 : 이일환 가격 연산 관련 클래스 정의
 */

public class PriceUtil {

	public static final int TWO_YEAR = 24;

	public static final int SELECT_DISCOUNT_AMOUNT = 25;

	public static final int RECOMMENDED_DISCOUNT_TYPE = -1;

	public static final int PUBLIC_SUPPORT_DISCOUNT_TYPE = 0;

	public static final int SELECT_INSTALLMENT_DISCOUNT_TYPE = 1;

	public static int getRecommendedDiscountType(Device device, Plan plan) {
		int pSupportAmount = device.getPublicSupport() + device.getAdditionalSupport();
		int sDiscountAmount =
			plan.getPrice() - applyPercentDiscount(plan.getPrice(), SELECT_DISCOUNT_AMOUNT);

		if (pSupportAmount > sDiscountAmount) {
			return PUBLIC_SUPPORT_DISCOUNT_TYPE;
		} else {
			return SELECT_INSTALLMENT_DISCOUNT_TYPE;
		}
	}

	public static int getTDevicePriceByDiscountType(Device device, int discountType) {
		int tDevicePrice;

		switch (discountType) {
			case PUBLIC_SUPPORT_DISCOUNT_TYPE:
				tDevicePrice = applyAmountDiscount(device.getPrice(),
					List.of(device.getPublicSupport(), device.getAdditionalSupport()));
				break;
			case SELECT_INSTALLMENT_DISCOUNT_TYPE:
				tDevicePrice = device.getPrice();
				break;
			default:
				throw new CustomException(ErrorCode.INVALID_REQUEST_VALUE_ERROR);
		}

		return tDevicePrice;
	}

	public static int getDDevicePriceByDiscountType(Device device, int discountType,
		int installmentPeriod) {
		return divideByMonth(getTDevicePriceByDiscountType(device, discountType),
			installmentPeriod);
	}

	public static int getDPlanPriceByDiscountType(Plan plan, int discountType) {
		switch (discountType) {
			case PUBLIC_SUPPORT_DISCOUNT_TYPE:
				return plan.getPrice();
			case SELECT_INSTALLMENT_DISCOUNT_TYPE:
				return applyPercentDiscount(plan.getPrice(), SELECT_DISCOUNT_AMOUNT);
			default:
				throw new CustomException(ErrorCode.INVALID_REQUEST_VALUE_ERROR);
		}
	}

	public static int getSelfCompPrice(Plan plan, Device device, int discountType) {
		switch (discountType) {
			case PUBLIC_SUPPORT_DISCOUNT_TYPE:
				return divideByMonth(applyAmountDiscount(device.getPrice(),
						List.of(device.getPublicSupport(), device.getAdditionalSupport())),
					TWO_YEAR) + plan.getPrice();
			case SELECT_INSTALLMENT_DISCOUNT_TYPE:
				return divideByMonth(device.getPrice(), TWO_YEAR) + applyPercentDiscount(
					plan.getPrice(), SELECT_DISCOUNT_AMOUNT);
			default:
				throw new CustomException(ErrorCode.INVALID_REQUEST_VALUE_ERROR);
		}
	}

	public static int divideByMonth(int price, int month) {
		return roundDownPrice(price / month);
	}

	public static int applyAmountDiscount(int price, List<Integer> discounts) {
		return price - discounts.stream().reduce(0, Integer::sum);
	}

	public static int applyPercentDiscount(int price, int percent) {
		return roundDownPrice(price * (100 - percent) / 100);
	}

	public static int roundDownPrice(int price) {
		return (price / 10) * 10;
	}
}
