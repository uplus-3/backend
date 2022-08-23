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
public class Image extends BaseEntity {

	@Column(nullable = false)
	private String url;

	@ManyToOne
	@JoinColumn(name = "color_id")
	private Color color;

	public void setColor(Color color) {
		this.color = color;

		if (!color.getImages().contains(this)) {
			color.getImages().add(this);
		}
	}

}
