package com.uplus.backend.cart.dto;

import static com.uplus.backend.global.util.PriceUtil.divideByMonth;
import static com.uplus.backend.global.util.PriceUtil.getDPlanPriceByDiscountType;
import static com.uplus.backend.global.util.PriceUtil.getTDevicePriceByDiscountType;

import com.uplus.backend.cart.entity.Cart;
import com.uplus.backend.device.entity.Device;
import com.uplus.backend.global.util.PriceUtil;
import com.uplus.backend.plan.entity.Plan;
import io.swagger.annotations.ApiModelProperty;
import java.time.format.DateTimeFormatter;
import lombok.Builder;
import lombok.Getter;

/**
 * 담당자 : 성아영
 */
@Builder
@Getter
public class CartResponseDto {

	@ApiModelProperty(name = "장바구니 아이템 ID", example = "1")
	private Long id;

	@ApiModelProperty(name = "장바구니 품목 추가 시간", example = "2022. 08. 31")
	private String createdAt;

	@ApiModelProperty(name = "요금제 id", example = "2")
	private Long planId;

	@ApiModelProperty(name = "요금제명", example = "5G 라이트 +")
	private String planName;

	@ApiModelProperty(name = "기기 ID", example = "2")
	private Long deviceId;

	@ApiModelProperty(name = "기기명", example = "갤럭시 S20 중고폰")
	private String deviceName;

	@ApiModelProperty(name = "기기 시리얼 넘버", example = "SM-7293")
	private String serialNumber;

	@ApiModelProperty(name = "색상명", example = "보라퍼플")
	private String colorName;

	@ApiModelProperty(name = "저장 용량", example = "256GB")
	private String storage;

	@ApiModelProperty(name = "할인 유형", example = "0(공시지원금) or 1(선택약정)")
	private int discountType;

	@ApiModelProperty(name = "가격", example = "100235")
	private int price;

	@ApiModelProperty(name = "네트워크 타입", example = "4 or 5")
	private String networkType;

	@ApiModelProperty(name = "가입 유형", example = "0(기기변경) or 1(신규가입) or 2(번호이동)")
	private int registrationType;

	@ApiModelProperty(name = "할부 기간", example = "1(일시불) or 12(12개월), 24(24개월) 36(36개월)")
	private int installmentPeriod;

	@ApiModelProperty(name = "이미지 url", example = "이미지 url 해당 이미지")
	private String imageUrl;

	@ApiModelProperty(name = "카트 ID", example = "1")
	private Long cartId;

	public static CartResponseDto fromEntity(Cart cart) {
		Plan plan = cart.getPlan();
		Device device = cart.getColor().getDevice();

		int planPrice = getDPlanPriceByDiscountType(plan, cart.getDiscountType());
		int tPrice = getTDevicePriceByDiscountType(device, cart.getDiscountType());
		int devicePrice = divideByMonth(tPrice, cart.getInstallmentPeriod());

		return CartResponseDto.builder()
			.id(cart.getId())
			.createdAt(cart.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")))
			.planName(plan.getName())
			.planId(plan.getId())
			.cartId(cart.getCartId())
			.deviceId(device.getId())
			.deviceName(device.getName())
			.serialNumber(device.getSerialNumber())
			.networkType(device.getNetworkType() + "g-phone")
			.colorName(cart.getColor().getName())
			.price(PriceUtil.roundDownPrice(planPrice + devicePrice))
			.storage(device.getStorage())
			.discountType(cart.getDiscountType())
			.registrationType(cart.getRegistrationType())
			.installmentPeriod(cart.getInstallmentPeriod())
			.imageUrl(device.getRepImageUrl())
			.build();
	}
}
