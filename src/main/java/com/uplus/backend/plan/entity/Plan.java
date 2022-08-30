package com.uplus.backend.plan.entity;

import com.uplus.backend.global.entity.BaseEntity;
import com.uplus.backend.order.entity.Order;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotBlank;
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
@Getter
public class Plan extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false)
	private Long id;

	@NotBlank(message = "요금제 명을 입력해 주세요")
	@Size(max = 50, message = "단말기 명은 최대 50자로 입력해 주세요")
	@Column(nullable = false, columnDefinition = "VARCHAR(50)")
	private String name;

	@PositiveOrZero(message = "네트워크 유형은 4(4G) 또는 5(5G)로 입력해 주세요")
	@DecimalMax(value = "9", message = "네트워크 유형은 0 ~ 9 값으로 입력해 주세요")
	@Column(nullable = false, columnDefinition = "TINYINT(1)")
	private int networkType;

	@Positive(message = "월정액요금은 양수를 입력해 주세요")
	@Column(nullable = false)
	private int price;

	@NotBlank(message = "데이터 스펙을 입력해 주세요")
	@Size(max = 50, message = "데이터 스펙은 최대 50자로 입력해 주세요")
	@Column(nullable = false, columnDefinition = "VARCHAR(50)")
	private String data;

	// TODO : nullable 컬럼 Validation 처리 필요
	@Column(columnDefinition = "VARCHAR(50)")
	private String subData;

	// TODO : nullable 컬럼 Validation 처리 필요
	@Column(columnDefinition = "VARCHAR(50)")
	private String shareData;

	@NotBlank(message = "음성 통화 스펙을 입력해 주세요")
	@Size(max = 50, message = "음성 통화 스펙은 최대 50자로 입력해 주세요")
	@Column(nullable = false, columnDefinition = "VARCHAR(50)")
	private String voiceCall;

	// TODO : nullable 컬럼 Validation 처리 필요
	@Column(columnDefinition = "VARCHAR(50)")
	private String subVoiceCall;

	@NotBlank(message = "음성 통화 스펙을 입력해 주세요")
	@Size(max = 50, message = "음성 통화 스펙은 최대 50자로 입력해 주세요")
	@Column(nullable = false, columnDefinition = "VARCHAR(50)")
	private String message;

	//TODO : nullable validation 적용
	@Column(columnDefinition = "VARCHAR(50)")
	private String smartDevice;

	//TODO : nullable validation 적용
	@Column(columnDefinition = "VARCHAR(50)")
	private String premiumService;

	//TODO : nullable validation 적용
	@Column(columnDefinition = "VARCHAR(50)")
	private String basicPromotion;


	@OneToMany(mappedBy = "plan")
	private List<Order> orders = new ArrayList<>();

	public void addOrders(Order order) {
		this.orders.add(order);
		if (order.getPlan() != this) {
			order.setPlan(this);
		}
	}


}
