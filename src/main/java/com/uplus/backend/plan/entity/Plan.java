package com.uplus.backend.plan.entity;

import com.uplus.backend.device.entity.Device;
import com.uplus.backend.global.entity.BaseEntity;
import com.uplus.backend.order.entity.Order;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
@Entity
@Getter
public class Plan extends BaseEntity {

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String networkType;

	@Column(nullable = false)
	private int price;

	@Column(nullable = false)
	private String data;

	private String subData;

	private String shareData;

	@Column(nullable = false)
	private String voiceCall;

	private String subVoiceCall;

	@Column(nullable = false)
	private String message;

	private String smartDevice;

	private String premiumService;

	private String basicPromotion;

	@OneToOne
	@JoinColumn(name = "device_id")
	private Device device;

	@OneToMany
	@JoinColumn(name = "orders_id")
	private List<Order> orders = new ArrayList<>();

	public void addOrders(Order order) {
		this.orders.add(order);
		if (order.getPlan() != this) {
			order.setPlan(this);
		}
	}


}
