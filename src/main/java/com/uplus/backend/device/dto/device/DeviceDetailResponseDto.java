package com.uplus.backend.device.dto.device;

import com.uplus.backend.device.dto.color.ColorResponseDto;
import com.uplus.backend.device.dto.tag.TagResponseDto;
import com.uplus.backend.device.entity.Device;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

/**
 * 담당자 : 이일환
 */
@Getter
@Builder
public class DeviceDetailResponseDto {

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

	@ApiModelProperty(name = "네트워크 타입", example = "5")
	private int networkType;

	@ApiModelProperty(name = "CPU", example = "Apple A15")
	private String cpu;

	@ApiModelProperty(name = "디스플레이", example = "6.1인치")
	private String display;

	@ApiModelProperty(name = "추천 요금제 식별자", example = "1")
	private Long recommendedPlanId;

	@ApiModelProperty(name = "추천 요금제 명", example = "5G 베이직")
	private String recommendedPlanName;

	private List<ColorResponseDto> colors;

	private List<TagResponseDto> tags;

	public static DeviceDetailResponseDto fromEntity(Device device) {
		return DeviceDetailResponseDto.builder()
			.id(device.getId())
			.serialNumber(device.getSerialNumber())
			.name(device.getName())
			.price(device.getPrice())
			.launchedDate(device.getLaunchedDate())
			.company(device.getCompany())
			.storage(device.getStorage())
			.networkType(device.getNetworkType())
			.cpu(device.getCpu())
			.display(device.getDisplay())
			.recommendedPlanId(device.getPlan().getId())
			.recommendedPlanName(device.getPlan().getName())
			.colors(device.getColors().stream()
				.map(ColorResponseDto::fromEntity)
				.collect(Collectors.toList()))
			.tags(device.getTags().stream()
				.map(TagResponseDto::fromEntity)
				.collect(Collectors.toList()))
			.build();
	}
}
