package com.uplus.backend.device.dto;

import com.uplus.backend.device.entity.Device;
import com.uplus.backend.plan.entity.Plan;
import java.time.LocalDateTime;
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

	private String name;

	private String serialNumber;

	private String storage;

	private int price;

	private LocalDateTime launchedDate;

	private String company;

	private int networkType;

	private String cpu;

	private String display;

	private int publicSupport;

	private int additionalSupport;

	private String repImageUrl;

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