package com.uplus.backend.device.dto;

import com.uplus.backend.device.entity.Device;
import com.uplus.backend.device.entity.Tag;
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
public class TagCreateRequestDto {

	@ApiModelProperty(name = "태그 내용", example = "최신")
	private String content;

	@ApiModelProperty(name = "태그 색상코드", example = "#000000")
	private String rgb;

	@ApiModelProperty(name = "단말기 식별자", example = "1")
	private Long deviceId;

	public Tag toEntity(Device device) {
		return Tag.builder()
			.content(content)
			.rgb(rgb)
			.device(device)
			.build();
	}
}
