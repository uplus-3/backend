package com.uplus.backend.global.util;

import com.uplus.backend.device.entity.Device;
import com.uplus.backend.plan.entity.Plan;

public class PriceUtil {

	public static final int DEFAULT_MONTH = 24;

	public static final int DEFAULT_INSTALLMENT_PERIOD = 24;

	public static final int SELECT_DISCOUNT_AMOUNT = 25;

	public static final int RECOMMENDED_DISCOUNT_TYPE = -1;

	public static final int PUBLIC_SUPPORT_DISCOUNT_TYPE = 0;

	public static final int SELECT_INSTALLMENT_DISCOUNT_TYPE = 1;

	public static int recommendedDeviceDiscount(Device device, Plan plan) {
		if (device.getPublicSupport() + device.getAdditionalSupport()
			> plan.getPrice() * SELECT_DISCOUNT_AMOUNT / 100 * DEFAULT_MONTH) {
			return device.getPrice() - device.getPublicSupport()
				- device.getAdditionalSupport();
		} else {
			return device.getPrice();
		}
	}

	public static int recommendedPlanDiscount(Device device, Plan plan) {
		if (device.getPublicSupport() + device.getAdditionalSupport()
			> plan.getPrice() * SELECT_DISCOUNT_AMOUNT / 100 * DEFAULT_MONTH) {
			return plan.getPrice();
		} else {
			return plan.getPrice() * 75 / 100;
		}
	}

	public static int deviceDiscount(Device device, Plan plan, int discountType) {
		switch (discountType) {
			case RECOMMENDED_DISCOUNT_TYPE:
				return recommendedDeviceDiscount(device, plan);
			case PUBLIC_SUPPORT_DISCOUNT_TYPE:
				return device.getPrice() - device.getPublicSupport()
					- device.getAdditionalSupport();
			case SELECT_INSTALLMENT_DISCOUNT_TYPE:
				return device.getPrice();
			default: // TODO : 예외 처리
				throw new RuntimeException();
		}
	}

	public static int getRecommendedDiscountType(Device device, Plan plan) {
		int sDiscountPrice = deviceDiscount(device, device.getPlan(), 0);
		int psDiscountPrice = deviceDiscount(device, device.getPlan(), 1);

		if (sDiscountPrice >= psDiscountPrice) {
			return 0;
		} else {
			return 1;
		}
	}

	public static int planDiscount(Device device, Plan plan, int discountType) {
		switch (discountType) {
			case RECOMMENDED_DISCOUNT_TYPE:
				return recommendedPlanDiscount(device, plan);
			case PUBLIC_SUPPORT_DISCOUNT_TYPE:
				return plan.getPrice();
			case SELECT_INSTALLMENT_DISCOUNT_TYPE:
				return plan.getPrice() * 75 / 100;
			default: // TODO : 예외 처리
				throw new RuntimeException();
		}
	}

	public static int getSelfCompPrice(Plan plan, Device device, int discountType) {
		switch (discountType) {
			case PUBLIC_SUPPORT_DISCOUNT_TYPE:
				return
					((device.getPrice() - device.getPublicSupport() - device.getAdditionalSupport())
						/ DEFAULT_MONTH) + plan.getPrice();
			case SELECT_INSTALLMENT_DISCOUNT_TYPE:
				return
					(device.getPrice() / DEFAULT_MONTH) +
						plan.getPrice() * (100 - SELECT_DISCOUNT_AMOUNT) / 100;
			default:
				throw new RuntimeException();
		}
	}
}
