package com.uplus.backend.device.dto;

import com.uplus.backend.device.entity.Color;
import com.uplus.backend.device.entity.Device;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
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

	@NotBlank(message = "색상 이름을 입력해 주세요")
	@Size(max = 20, message = "색상 이름은 최대 20자로 입력해 주세요")
	@ApiModelProperty(name = "색상 이름", example = "보라 퍼플")
	private String name;

	@NotBlank(message = "색상 코드를 입력해 주세요")
	@Size(max = 7, message = "색상 코드는 #000000으로 입력해 주세요")
	@ApiModelProperty(name = "색상 코드", example = "#000000")
	private String rgb;

	@PositiveOrZero(message = "남은 재고 수량은 0이상이어야 합니다.")
	@ApiModelProperty(name = "남은 재고", example = "1")
	private int stock;

	@Positive(message = "단말기 식별자는 1이상의 Long형입니다.")
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
