package com.uplus.backend.device.entity;

import com.uplus.backend.global.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class Tag extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false)
	private Long id;

	@Column(nullable = false, columnDefinition = "VARCHAR(10)")
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
