package com.uplus.backend.device.entity;

import com.uplus.backend.global.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class Tag extends BaseEntity {

	@Column(nullable = false)
	private String content;

	@Column(nullable = false, columnDefinition = "CHAR(7)")
	private String rgb;

	@ManyToOne
	@JoinColumn(name = "device_id")
	private Device device;

	public void setDevice(Device device) {
		this.device = device;
		if (device.getTags() != this) {
			device.getTags().add(this);
		}
	}
}
