package com.uplus.backend.order.dto;

import com.uplus.backend.device.entity.Color;
import com.uplus.backend.order.entity.Order;
import com.uplus.backend.plan.entity.Plan;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderCreateRequestDto {

	// validation 조건 설정 필요

	@ApiModelProperty(name = "주문자 이름", example = "윤유플")
	private String name;

	@ApiModelProperty(name = "주문자 휴대폰 번호", example = "010-1234-5678")
	private String phoneNumber;

	@ApiModelProperty(name = "주문자 주소", example = "(03924)서울특별시 마포구 월드컵북로 416")
	private String address;

	@ApiModelProperty(name = "주문금액", example = "100236")
	private int price;

	@ApiModelProperty(name = "할인 유형", example = "0(공시지원금) or 1(선택약정)")
	private int discountType;

	@ApiModelProperty(name = "가입 유형", example = "0(기기변경) or 1(신규가입) or 2(번호이동)")
	private int registrationType;

	@ApiModelProperty(name = "배송 방법", example = "1(우체국 택배) or 2(오늘 도착)")
	private int shipmentType;

	@ApiModelProperty(name = "할부 기간", example = "1(일시불) or 12(12개월), 24(24개월) 36(36개월)")
	private int installmentPeriod;

	@ApiModelProperty(name = "기종 색상 ID", example = "1")
	private Long colorId;

	@ApiModelProperty(name = "요금제 ID", example = "1")
	private Long planId;

	public Order toEntity(Color color, Plan plan) {
		return Order.builder()
			.name(name)
			.phoneNumber(phoneNumber)
			.address(address)
			.price(price)
			.discountType(discountType)
			.registrationType(registrationType)
			.shipmentType(shipmentType)
			.installmentPeriod(installmentPeriod)
			.color(color)
			.plan(plan)
			.build();
	}
}
