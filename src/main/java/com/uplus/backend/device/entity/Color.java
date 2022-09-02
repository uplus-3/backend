package com.uplus.backend.device.entity;

import com.uplus.backend.cart.entity.Cart;
import com.uplus.backend.global.entity.BaseEntity;
import com.uplus.backend.order.entity.Order;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
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
public class Color extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false)
	private Long id;

	@NotBlank(message = "색상 이름을 입력해 주세요")
	@Size(max = 20, message = "색상 이름은 최대 20자로 입력해 주세요")
	@Column(nullable = false, columnDefinition = "VARCHAR(20)")
	private String name;

	@NotBlank(message = "색상 코드를 입력해 주세요")
	@Size(max = 7, message = "색상 코드는 #000000으로 입력해 주세요")
	@Column(nullable = false, columnDefinition = "CHAR(7)")
	private String rgb;

	@PositiveOrZero(message = "남은 재고 수량은 0이상이어야 합니다.")
	@Column(nullable = false)
	private int stock;

	@ManyToOne
	@JoinColumn(name = "device_id", nullable = false)
	private Device device;

	@OneToMany(mappedBy = "color", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private List<Image> images = new ArrayList<>();

	@OneToMany(mappedBy = "color")
	private List<Order> orders = new ArrayList<>();

	@OneToMany(mappedBy = "color")
	private List<Cart> carts = new ArrayList<>();

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
