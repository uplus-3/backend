package com.uplus.backend.device.entity;

import com.uplus.backend.global.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
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
public class Image extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false)
	private Long id;

	@NotBlank(message = "이미지Url을 입력해 주세요")
	@Size(max = 1024, message = "이미지 url은 최대 1024자로 입력해 주세요")
	@Column(nullable = false, columnDefinition = "VARCHAR(1024)")
	private String imageUrl;

	@ManyToOne
	@JoinColumn(name = "color_id", nullable = false)
	private Color color;

	public void setColor(Color color) {
		this.color = color;

		if (!color.getImages().contains(this)) {
			color.getImages().add(this);
		}
	}
}
