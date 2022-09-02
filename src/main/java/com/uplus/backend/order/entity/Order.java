package com.uplus.backend.order.entity;

import com.uplus.backend.device.entity.Color;
import com.uplus.backend.global.entity.BaseEntity;
import com.uplus.backend.plan.entity.Plan;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "orders")
@Getter
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @DecimalMin(value = "1000000000")
    @DecimalMax(value = "9999999999")
    @Column(nullable = false, unique = true)
    private Long number;

    @NotBlank(message = "주문자 이름을 입력해 주세요")
    @Size(max = 20, message = "주문자 이름은 최대 20자로 입력해 주세요")
    @Column(nullable = false, columnDefinition = "VARCHAR(20)")
    private String name;

    @Pattern(regexp = "^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$|")
    @Column(columnDefinition = "VARCHAR(15)")
    private String phoneNumber;

    @NotBlank(message = "주문자 주소를 입력해 주세요")
    @Size(max = 100, message = "주문자 주소는 최대 100자로 입력해 주세요")
    @Column(nullable = false, columnDefinition = "VARCHAR(100)")
    private String address;

    public void setAddress(String address) {
        this.address = address;
    }

    @Positive(message = "주문금액은 양수로 입력해 주세요")
    @Column(nullable = false)
    private int price;

    @PositiveOrZero(message = "할인 유형은 0(공시지원금) or 1(선택 약정)로 입력해 주세요")
    @Max(value = 9, message = "할인 유형은 0 ~ 9 값으로 입력해 주세요")
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private int discountType;

    @PositiveOrZero(message = "가입 유형은 0(기기변경) or 1(신규가입) or 2(번호이동)로 입력해 주세요")
    @Max(value = 9, message = "가입 유형은 0 ~ 9 값으로 입력해 주세요")
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private int registrationType;

    @PositiveOrZero(message = "네트워크 유형은 0(기기변경) or 1(신규가입) or 2(번호이동)로 입력해 주세요")
    @Max(value = 9, message = "네트워크 유형은 0 ~ 9 값으로 입력해 주세요")
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private int shipmentType;

    @Positive(message = "할부 기간은 1(일시불) 이상을 입력해 주세요")
    @Max(value = 36, message = "할부 기간은 최대 36(개월)로 입력해 주세요")
    @Column(nullable = false)
    private int installmentPeriod;

    @ManyToOne
    @JoinColumn(name = "color_id", nullable = false)
    private Color color;

    public void setColor(Color color) {
        this.color = color;

        if (!color.getOrders().contains(this)) {
            color.getOrders().add(this);
        }
    }

    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;

    public void setPlan(Plan plan) {
        this.plan = plan;

        if (!plan.getOrders().contains(this)) {
            plan.getOrders().add(this);
        }
    }

}
