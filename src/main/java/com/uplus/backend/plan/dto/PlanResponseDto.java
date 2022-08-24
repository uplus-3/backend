package com.uplus.backend.plan.dto;

import com.uplus.backend.plan.entity.Plan;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PlanResponseDto {



	public static PlanResponseDto fromEntity(Plan plan) {
		return PlanResponseDto.builder().build();
	}
}
