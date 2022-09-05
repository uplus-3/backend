package com.uplus.backend.global.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 담당자 : 성아영
 */
public class CartIdUtil {

    private static final int RANDOM_BOUND = 10;

    private static final ThreadLocalRandom random = ThreadLocalRandom.current();

    public static Long createCartId() {
        String orderNumber = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss"));
        for (int i = 0; i < 2; i++) {
            orderNumber += random.nextInt(RANDOM_BOUND);
        }
        return Long.parseLong(orderNumber);
    }
}
