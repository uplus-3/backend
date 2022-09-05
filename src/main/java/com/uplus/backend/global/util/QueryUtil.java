package com.uplus.backend.global.util;


import static com.uplus.backend.global.exception.ErrorCode.NO_SEARCH_KEYWORD_ERROR;

import com.uplus.backend.global.exception.CustomException;

/**
 * 담당자 : 윤병찬 검색 키워드 변환 관련 클래스 정의
 */
public class QueryUtil {

	public static String getKeyword(String query) {
		String keyword = "";
		if (query.isBlank() || query.equals(" ")) {
			throw new CustomException(NO_SEARCH_KEYWORD_ERROR);
		} else {
			String queryList[] = query.split(" ");

			for (int i = 0; i < queryList.length; i++) {
				keyword = keyword + "+" + queryList[i] + "* ";
			}
			return keyword;
		}
	}
}
