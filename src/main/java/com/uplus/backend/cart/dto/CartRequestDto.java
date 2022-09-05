package com.uplus.backend.cart.dto;

import com.uplus.backend.cart.entity.Cart;
import com.uplus.backend.device.entity.Color;
import com.uplus.backend.plan.entity.Plan;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

/**
 * 담당자 : 성아영
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartRequestDto {

    @PositiveOrZero(message = "할인 유형은 0(공시지원금) or 1(선택 약정)로 입력해 주세요")
    @Max(value = 9, message = "할인 유형은 0 ~ 9 값으로 입력해 주세요")
    @ApiModelProperty(name = "할인 유형", example = "0(공시지원금) or 1(선택약정)")
    private int discountType;

    @PositiveOrZero(message = "가입 유형은 0(기기변경) or 1(신규가입) or 2(번호이동)로 입력해 주세요")
    @Max(value = 9, message = "가입 유형은 0 ~ 9 값으로 입력해 주세요")
    @ApiModelProperty(name = "가입 유형", example = "0(기기변경) or 1(신규가입) or 2(번호이동)")
    private int registrationType;

    @Positive(message = "할부 기간은 1(일시불) 이상을 입력해 주세요")
    @Max(value = 36, message = "할부 기간은 최대 36(개월)로 입력해 주세요")
    @ApiModelProperty(name = "할부 기간", example = "1(일시불) or 12(12개월), 24(24개월) 36(36개월)")
    private int installmentPeriod;

    @Positive(message = "기종 색상 식별자는 1이상의 Long형입니다.")
    @ApiModelProperty(name = "기종 색상 ID", example = "1")
    private Long colorId;

    @ApiModelProperty(name = "요금제 ID", example = "1")
    private Long planId;

    public Cart toEntity(Color color, Plan plan, Long cartId) {
        if (plan == null) {
            plan = color.getDevice().getPlan();
        }

        return Cart.builder()
            .discountType(discountType)
            .registrationType(registrationType)
            .installmentPeriod(installmentPeriod)
            .cartId(cartId)
            .color(color)
            .plan(plan)
            .build();
    }
}
