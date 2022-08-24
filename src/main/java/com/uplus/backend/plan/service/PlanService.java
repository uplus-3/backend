package com.uplus.backend.plan.service;

import com.uplus.backend.plan.controller.planResponseDto;
import com.uplus.backend.plan.dto.PlanResponseDto;
import com.uplus.backend.plan.dto.PlanResponseListDto;
import com.uplus.backend.plan.entity.Plan;
import com.uplus.backend.plan.repository.PlanRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlanService {

	private final PlanRepository planRepository;

	public List<PlanResponseDto> findByNetworkType(int networkType) {

		List<Plan> planList = planRepository.findByNetworkTypeOrderByPriceAsc(networkType);

		return PlanResponseListDto.fromEntity(planList);
	}
}
