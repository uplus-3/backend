package com.uplus.backend.plan.dto;

import com.uplus.backend.device.entity.Device;
import com.uplus.backend.plan.entity.Plan;
import com.uplus.backend.search.dto.SearchResponseDto;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PlanResponseListDto {

	@ApiModelProperty(name = "검색 결과 리스트", example = "")
	private List<PlanResponseDto> planResponseDtoList;

	public static List<PlanResponseDto> fromEntity(List<Plan> planList) {
		return planList.stream()
			.map(PlanResponseDto::fromEntity)
			.collect(Collectors.toList());
	}
}
