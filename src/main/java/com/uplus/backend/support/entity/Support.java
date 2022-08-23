package com.uplus.backend.support.entity;

import com.uplus.backend.device.entity.Device;
import com.uplus.backend.global.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
public class Support extends BaseEntity {

	@Column(nullable = false)
	private String publicSupport;

	@Column(nullable = false)
	private String addtionalSupport;

	@OneToOne
	@JoinColumn(name = "device_id")
	private Device device;
}
