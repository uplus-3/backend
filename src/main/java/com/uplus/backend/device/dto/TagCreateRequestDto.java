package com.uplus.backend.device.dto;

import com.uplus.backend.device.entity.Device;
import com.uplus.backend.device.entity.Tag;
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

	private String content;

	private String rgb;

	private Long deviceId;

	public Tag toEntity(Device device) {
		return Tag.builder()
			.content(content)
			.rgb(rgb)
			.device(device)
			.build();
	}
}
