package com.uplus.backend.cart.entity;

import com.uplus.backend.device.entity.Color;
import com.uplus.backend.global.entity.BaseEntity;
import com.uplus.backend.plan.entity.Plan;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "carts")
@Getter
public class Cart extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @DecimalMin(value = "10000000000000")
    @DecimalMax(value = "99999999999999")
    @Column(nullable = false)
    private Long cartId;

    @Positive(message = "할부 기간은 1(일시불) 이상을 입력해 주세요")
    @Max(value = 36, message = "할부 기간은 최대 36(개월)로 입력해 주세요")
    @Column(nullable = false)
    private int installmentPeriod;

    @PositiveOrZero(message = "할인 유형은 0(공시지원금) or 1(선택 약정)로 입력해 주세요")
    @Max(value = 9, message = "할인 유형은 0 ~ 9 값으로 입력해 주세요")
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private int discountType;

    @PositiveOrZero(message = "가입 유형은 0(기기변경) or 1(신규가입) or 2(번호이동)로 입력해 주세요")
    @Max(value = 9, message = "가입 유형은 0 ~ 9 값으로 입력해 주세요")
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private int registrationType;

    @ManyToOne
    @JoinColumn(name = "color_id", nullable = false)
    private Color color;

    public void setColor(Color color) {
        this.color = color;

        if (!color.getCarts().contains(this)) {
            color.getCarts().add(this);
        }
    }

    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;

    public void setPlan(Plan plan) {
        this.plan = plan;

        if (!plan.getCarts().contains(this)) {
            plan.getCarts().add(this);
        }
    }

}
