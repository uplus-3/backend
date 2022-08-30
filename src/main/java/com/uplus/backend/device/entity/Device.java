package com.uplus.backend.device.entity;

import com.uplus.backend.global.entity.BaseEntity;
import com.uplus.backend.plan.entity.Plan;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
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
public class Device extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false)
	private Long id;

	@NotBlank(message = "단말기 명을 입력해 주세요")
	@Size(max = 30, message = "단말기 명은 최대 30자로 입력해 주세요")
	@Column(nullable = false, columnDefinition = "VARCHAR(30)")
	private String name;

	@NotBlank(message = "시리얼 번호를 입력해 주세요")
	@Size(max = 30, message = "시리얼 번호는 최대 30자로 입력해 주세요")
	@Column(nullable = false, unique = true, columnDefinition = "VARCHAR(30)")
	private String serialNumber;

	@NotBlank(message = "저장 용량을 입력해 주세요")
	@Size(max = 30, message = "저장 용량은 최대 30자로 입력해 주세요")
	@Column(nullable = false, columnDefinition = "VARCHAR(30)")
	private String storage;

	@Positive(message = "정상가는 양수를 입력해 주세요")
	@ApiModelProperty(name = "정상가", example = "1078000")
	@Column(nullable = false)
	private int price;

	@PastOrPresent(message = "")
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date launchedDate;

	@NotBlank(message = "제조회사를 입력해 주세요")
	@Size(max = 30, message = "제조회사는 최대 30자로 입력해 주세요")
	@Column(nullable = false, columnDefinition = "VARCHAR(30)")
	private String company;

	@PositiveOrZero(message = "네트워크 유형은 4(4G) 또는 5(5G)로 입력해 주세요")
	@DecimalMax(value = "9", message = "네트워크 유형은 0 ~ 9 값으로 입력해 주세요")
	@Column(nullable = false, columnDefinition = "TINYINT(1)")
	private int networkType;

	@NotBlank(message = "CPU스펙을 입력해 주세요")
	@Size(max = 100, message = "CPU스펙은 최대 100자로 입력해 주세요")
	@Column(nullable = false, columnDefinition = "VARCHAR(100)")
	private String cpu;

	@NotBlank(message = "디스플레이 스펙을 입력해 주세요")
	@Size(max = 100, message = "디스플레이 스펙은 최대 100자로 입력해 주세요")
	@Column(nullable = false, columnDefinition = "VARCHAR(100)")
	private String display;

	@Positive(message = "공시지원금은 양수로 입력해 주세요")
	@Column(nullable = false)
	private int publicSupport;

	@PositiveOrZero(message = "추가지원금은 0이상의 양수로 입력해 주세요")
	@Column(nullable = false)
	private int additionalSupport;

	@NotBlank(message = "대표이미지Url을 입력해 주세요")
	@Size(max = 1024, message = "대표이미지 url은 최대 1024자로 입력해 주세요")
	@Column(nullable = false, columnDefinition = "VARCHAR(1024)")
	private String repImageUrl;

	@OneToOne
	private Plan plan;

	@OneToMany(mappedBy = "device")
	private List<Color> colors = new ArrayList<>();

	@OneToMany(mappedBy = "device")
	private List<Tag> tags = new ArrayList<>();

	public void addColor(Color color) {
		this.colors.add(color);
		if (color.getDevice() != this) {
			color.setDevice(this);
		}
	}

	public void addTag(Tag tag) {
		this.tags.add(tag);
		if (tag.getDevice() != this) {
			tag.setDevice(this);
		}
	}
}
