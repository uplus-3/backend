package com.uplus.backend.global.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {

	public static LocalDateTime strToLDT(String launchedDate) {
		return LocalDateTime.parse(launchedDate + " HH:mm:ss",
			DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
}
