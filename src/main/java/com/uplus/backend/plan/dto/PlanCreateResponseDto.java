package com.uplus.backend.plan.dto;

import com.uplus.backend.plan.entity.Plan;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PlanCreateResponseDto {

	private Long id;

	public static PlanCreateResponseDto fromEntity(Plan plan) {
		return PlanCreateResponseDto.builder()
			.id(plan.getId())
			.build();
	}
}
