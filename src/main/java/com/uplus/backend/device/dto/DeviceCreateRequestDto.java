package com.uplus.backend.device.dto;

import com.uplus.backend.device.entity.Device;
import com.uplus.backend.global.util.TimeUtil;
import com.uplus.backend.plan.entity.Plan;
import io.swagger.annotations.ApiModelProperty;
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

	@ApiModelProperty(name = "단말기 명", example = "아이폰 13 128GB")
	private String name;

	@ApiModelProperty(name = "시리얼 번호", example = "A2633-128")
	private String serialNumber;

	@ApiModelProperty(name = "저장 용량", example = "128GB")
	private String storage;

	@ApiModelProperty(name = "정상가", example = "1078000")
	private int price;

	@ApiModelProperty(name = "출시일", example = "2021-01-23")
	private String launchedDate;

	@ApiModelProperty(name = "제조회사", example = "Apple")
	private String company;

	@ApiModelProperty(name = "4G or 5G", example = "5")
	private int networkType;

	@ApiModelProperty(name = "CPU", example = "Apple A14 Bionic")
	private String cpu;

	@ApiModelProperty(name = "디스플레이", example = "15.4cm")
	private String display;

	@ApiModelProperty(name = "공시지원금", example = "200000")
	private int publicSupport;

	@ApiModelProperty(name = "추가지원금", example = "50000")
	private int additionalSupport;

	@ApiModelProperty(name = "대표이미지", example = "이미지URL")
	private String repImageUrl;

	@ApiModelProperty(name = "추천(대표)요금제 식별자", example = "1")
	private Long planId;

	public Device toEntity(Plan plan) {
		return Device.builder()
			.name(name)
			.serialNumber(serialNumber)
			.storage(storage)
			.price(price)
			.launchedDate(TimeUtil.strToLDT(launchedDate))
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