package com.uplus.backend.global.util;

import com.uplus.backend.device.entity.Device;
import com.uplus.backend.plan.entity.Plan;

public class DiscountUtil {

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
}
