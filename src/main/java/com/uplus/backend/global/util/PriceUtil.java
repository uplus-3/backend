package com.uplus.backend.global.util;

import com.uplus.backend.device.entity.Device;
import com.uplus.backend.plan.entity.Plan;
import java.util.List;

/**
 * 가격 연산 관련 클래스 정의
 */

public class PriceUtil {

	public static final int DEFAULT_MONTH = 24;

	public static final int SELECT_DISCOUNT_AMOUNT = 25;

	public static final int RECOMMENDED_DISCOUNT_TYPE = -1;

	public static final int PUBLIC_SUPPORT_DISCOUNT_TYPE = 0;

	public static final int SELECT_INSTALLMENT_DISCOUNT_TYPE = 1;

	public static int getDiscountedDevicePriceByRecommended(Device device, Plan plan) {
		if (getRecommendedDiscountType(device, plan, RECOMMENDED_DISCOUNT_TYPE)
			== PUBLIC_SUPPORT_DISCOUNT_TYPE) {
			return applyAmountDiscount(device.getPrice(),
				List.of(device.getPublicSupport(), device.getAdditionalSupport()));
		} else {
			return device.getPrice();
		}
	}

	public static int getDiscountedPlanPriceByRecommended(Device device, Plan plan) {
		if (getRecommendedDiscountType(device, plan, RECOMMENDED_DISCOUNT_TYPE)
			== SELECT_INSTALLMENT_DISCOUNT_TYPE) {
			return applyPercentDiscount(plan.getPrice(), SELECT_DISCOUNT_AMOUNT);
		} else {
			return plan.getPrice();
		}
	}

	public static int getDiscountedDevicePriceByDiscountType(Device device, Plan plan,
		int discountType) {
		switch (discountType) {
			case RECOMMENDED_DISCOUNT_TYPE:
				return getDiscountedDevicePriceByRecommended(device, plan);
			case PUBLIC_SUPPORT_DISCOUNT_TYPE:
				return applyAmountDiscount(device.getPrice(),
					List.of(device.getPublicSupport(), device.getAdditionalSupport()));
			case SELECT_INSTALLMENT_DISCOUNT_TYPE:
				return device.getPrice();
			default: // TODO : 예외 처리
				throw new RuntimeException();
		}
	}

	public static int getRecommendedDiscountType(Device device, Plan plan, int discountType) {
		if (discountType != -1) {
			return discountType;
		}

		int pSupportAmount = device.getPublicSupport() + device.getAdditionalSupport();
		int sDiscountAmount =
			plan.getPrice() - applyPercentDiscount(plan.getPrice(), SELECT_DISCOUNT_AMOUNT);

		if (pSupportAmount > sDiscountAmount) {
			return PUBLIC_SUPPORT_DISCOUNT_TYPE;
		} else {
			return SELECT_INSTALLMENT_DISCOUNT_TYPE;
		}
	}

	public static int getDiscountedPlanPriceByDiscountType(Device device, Plan plan,
		int discountType) {
		switch (discountType) {
			case RECOMMENDED_DISCOUNT_TYPE:
				return getDiscountedPlanPriceByRecommended(device, plan);
			case PUBLIC_SUPPORT_DISCOUNT_TYPE:
				return plan.getPrice();
			case SELECT_INSTALLMENT_DISCOUNT_TYPE:
				return applyPercentDiscount(plan.getPrice(), SELECT_DISCOUNT_AMOUNT);
			default: // TODO : 예외 처리
				throw new RuntimeException();
		}
	}

	public static int getSelfCompPrice(Plan plan, Device device, int discountType) {
		switch (discountType) {
			case PUBLIC_SUPPORT_DISCOUNT_TYPE:
				return
					divideByDefaultMonth(applyAmountDiscount(device.getPrice(),
						List.of(device.getPublicSupport(), device.getAdditionalSupport())))
						+ plan.getPrice();
			case SELECT_INSTALLMENT_DISCOUNT_TYPE:
				return
					divideByDefaultMonth(device.getPrice()) +
						applyPercentDiscount(plan.getPrice(), SELECT_DISCOUNT_AMOUNT);
			default:
				throw new RuntimeException();
		}
	}

	public static int divideByDefaultMonth(int price) {
		return roundDownPrice(price / DEFAULT_MONTH);
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
