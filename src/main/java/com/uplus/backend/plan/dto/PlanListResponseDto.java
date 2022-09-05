package com.uplus.backend.plan.dto;

import com.uplus.backend.plan.entity.Plan;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

/**
 * 담당자 : 윤병찬
 */
@Builder
@Getter
public class PlanListResponseDto {

	@ApiModelProperty(name = "요금제 리스트")
	private List<PlanResponseDto> planList;

	public static PlanListResponseDto fromEntity(List<Plan> planList) {
		return PlanListResponseDto.builder()
			.planList(planList.stream()
				.map(PlanResponseDto::fromEntity)
				.collect(Collectors.toList()))
			.build();
	}
}
