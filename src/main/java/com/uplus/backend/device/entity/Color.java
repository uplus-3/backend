package com.uplus.backend.device.entity;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
public class Color extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false, columnDefinition = "CHAR(7)")
	private String rdb;

	@Column(nullable = false)
	private int stock;

	@ManyToOne
	@JoinColumn(name = "device_id")
	private Device device;

	@OneToMany(mappedBy = "color")
	private List<Image> images = new ArrayList<>();

	@OneToMany(mappedBy = "color")
	private List<Order> orders = new ArrayList<>();


	public void setStock(int stock) {
		this.stock = stock;
	}

	public void setDevice(Device device) {
		this.device = device;
		if (device.getColors() != this) {
			device.getColors().add(this);
		}
	}

	public void addImages(Image image) {
		this.images.add(image);
		if (image.getColor() != this) {
			image.setColor(this);
		}
	}

	public void addOrders(Order order) {
		this.orders.add(order);
		if (order.getColor() != this) {
			order.setColor(this);
		}
	}

}
