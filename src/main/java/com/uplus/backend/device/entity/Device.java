package com.uplus.backend.device.entity;

import com.uplus.backend.global.entity.BaseEntity;
import com.uplus.backend.plan.entity.Plan;
import com.uplus.backend.support.entity.Support;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
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
public class Device extends BaseEntity {

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String serialNumber;

	@Column(nullable = false)
	private String storage;

	@Column(nullable = false)
	private int price;

	@Column(nullable = false)
	private LocalDateTime launchedDate;

	@Column(nullable = false)
	private String company;

	@Column(nullable = false)
	private int networkType;

	@Column(nullable = false)
	private String cpu;

	@Column(nullable = false)
	private String display;

	@OneToOne
	private Plan plan;

	@OneToOne(mappedBy = "device")
	private Support support;

	@OneToMany(mappedBy = "device")
	private List<Color> colors = new ArrayList<>();

	public void addColors(Color color) {
		this.colors.add(color);
		if (color.getDevice() != this) {
			color.setDevice(this);
		}
	}

	// tag entity


}
