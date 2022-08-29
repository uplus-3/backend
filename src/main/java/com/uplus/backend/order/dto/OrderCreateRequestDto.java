package com.uplus.backend.order.dto;

import com.uplus.backend.device.entity.Color;
import com.uplus.backend.order.entity.Order;
import com.uplus.backend.plan.entity.Plan;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
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

	@NotBlank(message = "주문자 이름을 입력해 주세요")
	@Size(max = 20, message = "주문자 이름은 최대 20자로 입력해 주세요")
	@ApiModelProperty(name = "주문자 이름", example = "윤유플")
	private String name;

	@Pattern(regexp = "^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$|")
	@ApiModelProperty(name = "주문자 휴대폰 번호", example = "010-1234-5678")
	private String phoneNumber;

	@NotBlank(message = "주문자 주소를 입력해 주세요")
	@Size
	@ApiModelProperty(name = "주문자 주소", example = "(03924)서울특별시 마포구 월드컵북로 416")
	private String address;

	@Positive(message = "주문금액은 양수로 입력해 주세요")
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

	public Order toEntity(Color color, Plan plan, Long number) {
		return Order.builder()
			.name(name)
			.number(number)
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
