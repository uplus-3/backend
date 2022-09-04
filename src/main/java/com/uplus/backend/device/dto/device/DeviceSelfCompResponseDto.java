package com.uplus.backend.device.dto.device;

import com.uplus.backend.device.entity.Device;
import com.uplus.backend.plan.dto.PlanDetailResponseDto;
import com.uplus.backend.plan.entity.Plan;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeviceSelfCompResponseDto {

	private List<PlanDetailResponseDto> plans;

	public static DeviceSelfCompResponseDto fromEntity(Device device, List<Plan> plans) {
		return DeviceSelfCompResponseDto.builder()
			.plans(plans.stream()
				.map(plan -> PlanDetailResponseDto.fromEntity(plan, device))
				.collect(Collectors.toList()))
			.build();
	}
}
