package com.uplus.backend.plan.entity;

import com.uplus.backend.device.entity.Device;
import com.uplus.backend.global.entity.BaseEntity;
import com.uplus.backend.order.entity.Order;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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

	@Column(nullable = false, columnDefinition = "VARCHAR(50)")
	private String name;

	@Column(nullable = false, columnDefinition = "TINYINT(1)")
	private int networkType;

	@Column(nullable = false)
	private int price;

	@Column(nullable = false, columnDefinition = "VARCHAR(50)")
	private String data;

	@Column(columnDefinition = "VARCHAR(50)")
	private String subData;

	@Column(columnDefinition = "VARCHAR(50)")
	private String shareData;

	@Column(nullable = false, columnDefinition = "VARCHAR(50)")
	private String voiceCall;

	@Column(columnDefinition = "VARCHAR(50)")
	private String subVoiceCall;

	@Column(nullable = false, columnDefinition = "VARCHAR(50)")
	private String message;

	@Column(columnDefinition = "VARCHAR(50)")
	private String smartDevice;

	@Column(columnDefinition = "VARCHAR(50)")
	private String premiumService;

	@Column(columnDefinition = "VARCHAR(50)")
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
