package com.uplus.backend.plan.dto;

import com.uplus.backend.plan.entity.Plan;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PlanResponseDto {

	@ApiModelProperty(name = "요금제 ID", example = "1")
	private Long id;

	@ApiModelProperty(name = "요금제 명", example = "5G+ 프리미엄 요금제")
	private String name;

	public static PlanResponseDto fromEntity(Plan plan) {
		return PlanResponseDto.builder()
			.id(plan.getId())
			.name(plan.getName())
			.build();
	}
}
