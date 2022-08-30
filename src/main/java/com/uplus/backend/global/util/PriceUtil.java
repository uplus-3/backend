package com.uplus.backend.global.util;

import com.uplus.backend.device.entity.Device;
import com.uplus.backend.plan.entity.Plan;

public class PriceUtil {

	public static final int DEFAULT_MONTH = 24;

	public static final int SELECT_DISCOUNT_AMOUNT = 25;

	public static final int RECOMMENDED_DISCOUNT_TYPE = -1;

	public static final int PUBLIC_SUPPORT_DISCOUNT_TYPE = 0;

	public static final int SELECT_INSTALLMENT_DISCOUNT_TYPE = 1;

	public static int discountDeviceByRecommended(Device device, Plan plan) {
		if (getRecommendedDiscountType(device, plan, RECOMMENDED_DISCOUNT_TYPE)
			== PUBLIC_SUPPORT_DISCOUNT_TYPE) {
			return device.getPrice() - device.getPublicSupport()
				- device.getAdditionalSupport();
		} else {
			return device.getPrice();
		}
	}

	public static int discountPlanByRecommended(Device device, Plan plan) {
		if (getRecommendedDiscountType(device, plan, RECOMMENDED_DISCOUNT_TYPE)
			== SELECT_INSTALLMENT_DISCOUNT_TYPE) {
			return plan.getPrice() * 75 / 100;
		} else {
			return plan.getPrice();
		}
	}

	public static int discountDevice(Device device, Plan plan, int discountType) {
		switch (discountType) {
			case RECOMMENDED_DISCOUNT_TYPE:
				return discountDeviceByRecommended(device, plan);
			case PUBLIC_SUPPORT_DISCOUNT_TYPE:
				return device.getPrice() - device.getPublicSupport()
					- device.getAdditionalSupport();
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
		int sDiscountAmount = plan.getPrice() * SELECT_DISCOUNT_AMOUNT / 100 * DEFAULT_MONTH;

		if (pSupportAmount > sDiscountAmount) {
			return PUBLIC_SUPPORT_DISCOUNT_TYPE;
		} else {
			return SELECT_INSTALLMENT_DISCOUNT_TYPE;
		}
	}

	public static int discountPlan(Device device, Plan plan, int discountType) {
		switch (discountType) {
			case RECOMMENDED_DISCOUNT_TYPE:
				return discountPlanByRecommended(device, plan);
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
