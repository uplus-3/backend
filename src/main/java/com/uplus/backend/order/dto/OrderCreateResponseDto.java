package com.uplus.backend.order.dto;

import com.uplus.backend.order.entity.Order;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

/**
 * 담당자 : 윤병찬
 */
@Builder
@Getter
public class OrderCreateResponseDto {

	@ApiModelProperty(name = "생성 주문번호 ", example = "2022082412")
	private Long number;

	public static OrderCreateResponseDto fromEntity(Order order) {
		return OrderCreateResponseDto.builder()
			.number(order.getNumber())
			.build();
	}
}
