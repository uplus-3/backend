package com.uplus.backend.order.entity;

import com.uplus.backend.device.entity.Color;
import com.uplus.backend.global.entity.BaseEntity;
import com.uplus.backend.plan.entity.Plan;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order extends BaseEntity {

	@Column(nullable = false)
	private Long number;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String phone;

	@Column(nullable = false)
	private String address;

	@Column(nullable = false)
	private int price;

	@Column(nullable = false, columnDefinition = "TINYINT(1)")
	private int discountType;

	@Column(nullable = false, columnDefinition = "TINYINT(1)")
	private int registrationType;

	@Column(nullable = false, columnDefinition = "TINYINT(1)")
	private int shipmentType;

	@Column(nullable = false)
	private int installmentType;

	@ManyToOne
	@JoinColumn(name = "color_id")
	private Color color;

	public void setColor(Color color) {
		this.color = color;

		if (!color.getOrders().contains(this)) {
			color.getOrders().add(this);
		}
	}

	@ManyToOne
	@JoinColumn(name = "plan_id")
	private Plan plan;

	public void setPlan(Plan plan) {
		this.plan = plan;

		if (!plan.getOrders().contains(this)) {
			plan.getOrders().add(this);
		}
	}


}