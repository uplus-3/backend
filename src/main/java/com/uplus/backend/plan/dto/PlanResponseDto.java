package com.uplus.backend.plan.dto;

import com.uplus.backend.plan.entity.Plan;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PlanResponseDto {


	public static PlanResponseDto fromEntity(Plan plan) {
		return PlanResponseDto.builder().build();
	}
}
