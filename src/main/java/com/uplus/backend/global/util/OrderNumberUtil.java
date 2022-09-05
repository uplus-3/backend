package com.uplus.backend.global.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 담당자 : 윤병찬
 * 주문 코드 관련 클래스 정의
 */
public class OrderNumberUtil {

	private static final int RANDOM_BOUND = 10;

	private static final ThreadLocalRandom random = ThreadLocalRandom.current();

	public static Long createOrderNumber() {
		String orderNumber = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
		for (int i = 0; i < 4; i++) {
			orderNumber += random.nextInt(RANDOM_BOUND);
		}
		return Long.parseLong(orderNumber);
	}
}
