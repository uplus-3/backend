package com.uplus.backend.device.dto;

import com.uplus.backend.device.entity.Device;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeviceResponseDto {

	@ApiModelProperty(name = "단말기 식별자", example = "1")
	private Long id;

	@ApiModelProperty(name = "시리얼번호", example = "A2633-128")
	private String serialNumber;

	@ApiModelProperty(name = "단말기 명", example = "아이폰 13 128GB")
	private String name;

	@ApiModelProperty(name = "정상가", example = "990000")
	private int price;

	private List<TagResponseDto> tags;

	private List<ColorRepResponseDto> colors;

	public static DeviceResponseDto fromEntity(Device device) {
		return DeviceResponseDto.builder()
			.id(device.getId())
			.serialNumber(device.getSerialNumber())
			.name(device.getName())
			.price(device.getPrice())
			.tags(device.getTags().stream().map(TagResponseDto::fromEntity)
				.collect(Collectors.toList()))
			.colors(device.getColors().stream().map(ColorRepResponseDto::fromEntity)
				.collect(Collectors.toList()))
			.build();
	}
}
