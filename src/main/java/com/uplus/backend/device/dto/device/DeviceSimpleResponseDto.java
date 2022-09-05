package com.uplus.backend.device.dto.device;

import com.uplus.backend.device.entity.Device;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

/**
 * 담당자 : 이일환
 */
@Getter
@Builder
public class DeviceSimpleResponseDto {

	@ApiModelProperty(name = "단말기 식별자", example = "1")
	private Long id;

	@ApiModelProperty(name = "단말기명", example = "갤럭시 S22")
	private String name;

	@ApiModelProperty(name = "제조사", example = "삼성")
	private String company;

	@ApiModelProperty(name = "네트워크 타입", example = "5")
	private int networkType;

	public static DeviceSimpleResponseDto fromEntity(Device device) {
		return DeviceSimpleResponseDto.builder()
			.id(device.getId())
			.name(device.getName())
			.company(device.getCompany())
			.networkType(device.getNetworkType())
			.build();
	}
}
