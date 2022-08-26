package com.uplus.backend.device.dto;

import com.uplus.backend.device.entity.Color;
import com.uplus.backend.device.entity.Device;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ColorCreateRequestDto {

	private String name;

	private String rgb;

	private int stock;

	private Long deviceId;

	public Color toEntity(Device device) {
		return Color.builder()
			.name(name)
			.rdb(rgb)
			.stock(stock)
			.device(device)
			.build();
	}
}
