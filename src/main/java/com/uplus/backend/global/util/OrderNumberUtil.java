package com.uplus.backend.global.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

public class OrderNumberUtil {

	private static final int RANDOM_BOUND = 10;

	private static final ThreadLocalRandom random = ThreadLocalRandom.current();

	public static Long createOrderNumber() {
		String orderNumber = LocalDate.now().format(DateTimeFormatter.ofPattern("YYMMDD"));
		for (int i = 0; i < 4; i++) {
			orderNumber += random.nextInt(RANDOM_BOUND);
		}
		return Long.parseLong(orderNumber);
	}
}
