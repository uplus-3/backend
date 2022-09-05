package com.uplus.backend.device.entity;

import com.uplus.backend.global.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 담당자 : 이일환, 윤병찬
 */
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

	@NotBlank(message = "태그 내용을 입력해 주세요")
	@Size(max = 10, message = "태그 내용은 최대 10자로 입력해 주세요")
	@Column(nullable = false, columnDefinition = "VARCHAR(10)")
	private String content;

	@NotBlank(message = "색상 코드를 입력해 주세요")
	@Size(max = 7, message = "색상 코드는 #000000으로 입력해 주세요")
	@Column(nullable = false, columnDefinition = "CHAR(7)")
	private String rgb;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "device_id", nullable = false)
	private Device device;

	public void setDevice(Device device) {
		this.device = device;
		if (device.getTags() != this) {
			device.getTags().add(this);
		}
	}
}
