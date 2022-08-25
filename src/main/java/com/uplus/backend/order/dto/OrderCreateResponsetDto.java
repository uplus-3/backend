package com.uplus.backend.order.dto;

import com.uplus.backend.order.entity.Order;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OrderCreateResponsetDto {

	@ApiModelProperty(name = "생성 주문번호 ", example = "2022082412")
	private Long number;

	public static OrderCreateResponsetDto fromEntity(Order order) {
		return OrderCreateResponsetDto.builder()
			.number(order.getNumber())
			.build();
	}
}
