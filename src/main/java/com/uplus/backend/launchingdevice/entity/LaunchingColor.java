package com.uplus.backend.launchingdevice.entity;

import com.uplus.backend.global.entity.BaseEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 담당자 : 김수현
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class LaunchingColor extends BaseEntity {

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

	@ManyToOne
	@JoinColumn(name = "launching_device_id", nullable = false)
	private LaunchingDevice launchingDevice;

	@OneToMany(mappedBy = "launchingColor", cascade = CascadeType.PERSIST)
	private List<LaunchingImage> launchingImages = new ArrayList<>();

	public void addLaunchingImage(LaunchingImage launchingImage) {
		this.launchingImages.add(launchingImage);
		if (launchingImage.getLaunchingColor() != this) {
			launchingImage.setLaunchingColor(this);
		}
	}

	public void setLaunchingDevice(LaunchingDevice launchingDevice) {
		if (this.launchingDevice != null) {
			this.launchingDevice.getLaunchingColors().remove(this);
		}
		this.launchingDevice = launchingDevice;
		launchingDevice.getLaunchingColors().add(this);
	}
}
