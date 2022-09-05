package com.uplus.backend.device.dto.tag;

import com.uplus.backend.device.entity.Device;
import com.uplus.backend.device.entity.Tag;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 담당자 : 이일환
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class TagCreateRequestDto {


	@NotBlank(message = "태그 내용을 입력해 주세요")
	@Size(max = 10, message = "태그 내용은 최대 10자로 입력해 주세요")
	@ApiModelProperty(name = "태그 내용", example = "최신")
	private String content;

	@NotBlank(message = "색상 코드를 입력해 주세요")
	@Size(max = 7, message = "색상 코드는 #000000으로 입력해 주세요")
	@ApiModelProperty(name = "색상값", example = "#0E0E0E")
	private String rgb;

	@Positive(message = "기기 식별자는 1이상의 Long형입니다.")
	@ApiModelProperty(name = "기기 Id", example = "1")
	private Long deviceId;

	public Tag toEntity(Device device) {
		return Tag.builder()
			.content(content)
			.rgb(rgb)
			.device(device)
			.build();
	}
}
