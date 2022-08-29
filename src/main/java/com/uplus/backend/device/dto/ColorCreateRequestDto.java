package com.uplus.backend.device.dto;

import com.uplus.backend.device.entity.Color;
import com.uplus.backend.device.entity.Device;
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
public class ColorCreateRequestDto {

	@ApiModelProperty(name = "색상 이름", example = "보라 퍼플")
	private String name;

	@ApiModelProperty(name = "색상 코드", example = "#000000")
	private String rgb;

	@ApiModelProperty(name = "남은 재고", example = "1")
	private int stock;

	@ApiModelProperty(name = "단말기 식별자", example = "1")
	private Long deviceId;

	public Color toEntity(Device device) {
		return Color.builder()
			.name(name)
			.rgb(rgb)
			.stock(stock)
			.device(device)
			.build();
	}
}
