package com.uplus.backend.device.dto;

import com.uplus.backend.device.entity.Device;
import com.uplus.backend.global.util.TimeUtil;
import com.uplus.backend.plan.entity.Plan;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class DeviceCreateRequestDto {

	@NotBlank(message = "단말기 명을 입력해 주세요")
	@Size(max = 30, message = "단말기 명은 최대 30자로 입력해 주세요")
	@ApiModelProperty(name = "단말기 명", example = "아이폰 13 128GB")
	private String name;

	@NotBlank(message = "시리얼 번호를 입력해 주세요")
	@Size(max = 30, message = "시리얼 번호는 최대 30자로 입력해 주세요")
	@ApiModelProperty(name = "시리얼 번호", example = "A2633-128")
	private String serialNumber;

	@NotBlank(message = "저장 용량을 입력해 주세요")
	@Size(max = 30, message = "저장 용량은 최대 30자로 입력해 주세요")
	@ApiModelProperty(name = "저장 용량", example = "128GB")
	private String storage;

	@Positive(message = "정상가는 양수를 입력해 주세요")
	@ApiModelProperty(name = "정상가", example = "1078000")
	private int price;

//	TODO : Validation 추가
//	@Pattern(regexp = "^(19|20)\\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])$", message = "출시일은 YYYY-MM-DD 형식으로 입력해 주세요")
	@ApiModelProperty(name = "출시일", example = "2021-01-23")
	private Date launchedDate;

	@NotBlank(message = "제조회사를 입력해 주세요")
	@Size(max = 30, message = "제조회사는 최대 30자로 입력해 주세요")
	@ApiModelProperty(name = "제조회사", example = "Apple")
	private String company;

	@Positive(message = "네트워크 유형은 현재 4(4G) 또는 5(5G)로 입력해 주세요")
	@DecimalMax(value = "9", message = "네트워크 유형은 0 ~ 9 값으로 입력해 주세요")
	@ApiModelProperty(name = "4G or 5G", example = "5")
	private int networkType;

	@NotBlank(message = "CPU스펙을 입력해 주세요")
	@Size(max = 100, message = "CPU스펙은 최대 100자로 입력해 주세요")
	@ApiModelProperty(name = "CPU", example = "Apple A14 Bionic")
	private String cpu;

	@NotBlank(message = "디스플레이 스펙을 입력해 주세요")
	@Size(max = 100, message = "디스플레이 스펙은 최대 100자로 입력해 주세요")
	@ApiModelProperty(name = "디스플레이", example = "15.4cm")
	private String display;

	@Positive(message = "공시지원금은 양수로 입력해 주세요")
	@ApiModelProperty(name = "공시지원금", example = "200000")
	private int publicSupport;


	// TODO : nullable Validation 추가
	@ApiModelProperty(name = "추가지원금", example = "50000")
	private int additionalSupport;

	@NotBlank(message = "대표이미지Url을 입력해 주세요")
	@Size(max = 1024, message = "대표이미지 url은 최대 1024자로 입력해 주세요")
	@ApiModelProperty(name = "대표이미지", example = "이미지URL")
	private String repImageUrl;

	@Positive(message = "요금제 식별자는 1이상의 Long형입니다.")
	@ApiModelProperty(name = "추천(대표)요금제 식별자", example = "1")
	private Long planId;

	public Device toEntity(Plan plan) {
		return Device.builder()
			.name(name)
			.serialNumber(serialNumber)
			.storage(storage)
			.price(price)
			.launchedDate(launchedDate)
			.company(company)
			.networkType(networkType)
			.cpu(cpu)
			.display(display)
			.publicSupport(publicSupport)
			.additionalSupport(additionalSupport)
			.repImageUrl(repImageUrl)
			.plan(plan)
			.build();
	}
}