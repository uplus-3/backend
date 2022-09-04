package com.uplus.backend.device.dto.device;

import com.uplus.backend.device.dto.color.ColorRepResponseDto;
import com.uplus.backend.device.dto.tag.TagResponseDto;
import com.uplus.backend.device.entity.Device;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
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

	@ApiModelProperty(name = "출시일", example = "2022-02-02")
	private Date launchedDate;

	@ApiModelProperty(name = "제조사", example = "애플")
	private String company;

	@ApiModelProperty(name = "저장용량", example = "256GB")
	private String storage;

	private List<TagResponseDto> tags;

	private List<ColorRepResponseDto> colors;

	public static DeviceResponseDto fromEntity(Device device) {
		return DeviceResponseDto.builder()
			.id(device.getId())
			.serialNumber(device.getSerialNumber())
			.name(device.getName())
			.price(device.getPrice())
			.launchedDate(device.getLaunchedDate())
			.company(device.getCompany())
			.storage(device.getStorage())
			.tags(device.getTags().stream().map(TagResponseDto::fromEntity)
				.collect(Collectors.toList()))
			.colors(device.getColors().stream().map(ColorRepResponseDto::fromEntity)
				.collect(Collectors.toList()))
			.build();
	}
}
