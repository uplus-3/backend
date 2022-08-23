package com.uplus.backend.device.entity;

import com.uplus.backend.global.entity.BaseEntity;
import com.uplus.backend.order.entity.Order;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
public class Color extends BaseEntity {

	@Column(nullable = false)
	private String name;

	@Column(nullable = false, columnDefinition = "CHAR(7)")
	private String rdb;

	@ManyToOne
	@JoinColumn(name = "device_id")
	private Device device;

	public void setDevice(Device device) {
		this.device = device;
		if (device.getColors() != this) {
			device.getColors().add(this);
		}
	}

	@OneToMany(mappedBy = "color")
	private List<Image> images = new ArrayList<>();

	public void addImages(Image image) {
		this.images.add(image);
		if (image.getColor() != this) {
			image.setColor(this);
		}
	}

	@OneToMany(mappedBy = "color")
	List<Order> orders = new ArrayList<>();

	public void addOrders(Order order) {
		this.orders.add(order);
		if (order.getColor() != this) {
			order.setColor(this);
		}
	}

	// stock entity

}
