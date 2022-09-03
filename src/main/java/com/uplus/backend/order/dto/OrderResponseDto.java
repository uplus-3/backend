package com.uplus.backend.order.dto;

import com.uplus.backend.order.entity.Order;
import io.swagger.annotations.ApiModelProperty;
import java.time.format.DateTimeFormatter;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OrderResponseDto {

	@ApiModelProperty(name = "주문 데이터 ID", example = "123")
	private Long id;

	@ApiModelProperty(name = "주문 번호", example = "2022082401")
	private Long number;

	@ApiModelProperty(name = "주문자 이름", example = "윤유플")
	private String name;

	@ApiModelProperty(name = "주문자 주소", example = "(03924)서울특별시 마포구 월드컵북로 416")
	private String address;

	@ApiModelProperty(name = "주문 생성 시간", example = "2022.08.24")
	private String createdAt;

	@ApiModelProperty(name = "기기명", example = "갤럭시 S20 중고폰")
	private String deviceName;

	@ApiModelProperty(name = "기기 네트워크 유형", example = "5(5G)")
	private int networkType;

	@ApiModelProperty(name = "기기 ID", example = "1")
	private Long deviceId;

	@ApiModelProperty(name = "기기 제품 번호", example = "1234-L123")
	private String serialNumber;

	@ApiModelProperty(name = "요금제명", example = "5G 라이트 +")
	private String planName;

	@ApiModelProperty(name = "요금제 ID", example = "갤럭시 S20 중고폰")
	private Long planId;

	@ApiModelProperty(name = "색상명", example = "보라퍼플")
	private String colorName;

	@ApiModelProperty(name = "저장 용량", example = "128GB")
	private String storage;

	@ApiModelProperty(name = "주문금액", example = "100236")
	private int price;

	@ApiModelProperty(name = "할인 유형", example = "0(공시지원금) or 1(선택약정)")
	private int discountType;

	@ApiModelProperty(name = "가입 유형", example = "0(기기변경) or 1(신규가입) or 2(번호이동)")
	private int registrationType;

	@ApiModelProperty(name = "할부 기간", example = "1(일시불) or 12(12개월), 24(24개월) 36(36개월)")
	private int installmentPeriod;

	@ApiModelProperty(name = "이미지 url", example = "이미지 url 해당 이미지")
	private String imageUrl;

	public static OrderResponseDto fromEntity(Order order) {
		return OrderResponseDto.builder()
			.id(order.getId())
			.number(order.getNumber())
			.name(order.getName())
			.address(order.getAddress())
			.createdAt(order.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")))
			.deviceName(order.getColor().getDevice().getName())
			.networkType(order.getColor().getDevice().getNetworkType())
			.deviceId(order.getColor().getDevice().getId())
			.serialNumber(order.getColor().getDevice().getSerialNumber())
			.planName(order.getPlan().getName())
			.planId(order.getPlan().getId())
			.colorName(order.getColor().getName())
			.storage(order.getColor().getDevice().getStorage())
			.price(order.getPrice())
			.discountType(order.getDiscountType())
			.registrationType(order.getRegistrationType())
			.installmentPeriod(order.getInstallmentPeriod())
			.imageUrl(order.getColor().getDevice().getRepImageUrl())
			.build();
	}
}
