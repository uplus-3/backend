package com.uplus.backend.device.dto.image;

import com.uplus.backend.device.entity.Color;
import com.uplus.backend.device.entity.Image;
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
public class ImageCreateRequestDto {

	@NotBlank(message = "이미지 url을 입력해 주세요")
	@Size(max = 1024, message = "이미지 url은 최대 1024자로 입력해 주세요")
	@ApiModelProperty(name = "이미지 url", example = "이미지 url 해당 이미지")
	private String imageUrl;

	@Positive(message = "색상 식별자는 1이상의 Long형입니다.")
	@ApiModelProperty(name = "색상 ID", example = "1")
	private Long colorId;

	public Image toEntity(Color color) {
		return Image.builder()
			.imageUrl(imageUrl)
			.color(color)
			.build();
	}
}
