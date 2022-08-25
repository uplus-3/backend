package com.uplus.backend.global.util;

import com.uplus.backend.device.entity.Device;
import com.uplus.backend.plan.entity.Plan;

public class DiscountUtil {

	public static final int DEFAULT_MONTH = 24;

	public static final int SELECT_DISCOUNT_AMOUNT = 25;

	public static int deviceDiscount(Device device, int discountType) {
		switch (discountType) {
			case 0:
				return device.getPrice() - device.getPublicSupport()
					- device.getAdditionalSupport();
			case 1:
				return device.getPrice();
			default: // TODO : 예외 처리
				throw new RuntimeException();
		}
	}

	public static int planDiscount(Plan plan, int discountType) {
		switch (discountType) {
			case 0:
				return plan.getPrice();
			case 1:
				return plan.getPrice() * 75 / 100;
			default: // TODO : 예외 처리
				throw new RuntimeException();
		}
	}

	public static int getSelfCompPrice(Plan plan, Device device, int discountType) {
		switch (discountType) {
			case 0:
				return
					((device.getPrice() - device.getPublicSupport() - device.getAdditionalSupport())
						/ DEFAULT_MONTH) + plan.getPrice();
			case 1:
				return
					(device.getPrice() / DEFAULT_MONTH) +
						plan.getPrice() * (100 - SELECT_DISCOUNT_AMOUNT) / 100;
			default:
				throw new RuntimeException();
		}
	}
}
