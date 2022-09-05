package com.uplus.backend.plan.dto;

import com.uplus.backend.plan.entity.Plan;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

/**
 * 담당자 : 윤병찬
 */
@Getter
@Builder
public class PlanCreateResponseDto {

	@ApiModelProperty(name = "요금제 id", example = "1")
	private Long id;

	public static PlanCreateResponseDto fromEntity(Plan plan) {
		return PlanCreateResponseDto.builder()
			.id(plan.getId())
			.build();
	}
}
