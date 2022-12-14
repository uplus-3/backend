package com.uplus.backend.plan.service;

import com.uplus.backend.plan.dto.PlanCreateRequestDto;
import com.uplus.backend.plan.dto.PlanCreateResponseDto;
import com.uplus.backend.plan.dto.PlanListResponseDto;
import com.uplus.backend.plan.entity.Plan;
import com.uplus.backend.plan.repository.PlanRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 담당자 : 윤병찬
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PlanService {

	private final PlanRepository planRepository;

	@Transactional
	public PlanCreateResponseDto create(PlanCreateRequestDto requestDto) {
		Plan plan = planRepository.save(requestDto.toEntity());

		return PlanCreateResponseDto.fromEntity(plan);
	}

	@Transactional(readOnly = true)
	public PlanListResponseDto findByNetworkType(int networkType) {
		List<Plan> planList =
			networkType == 0 ?
				planRepository.findAllByOrderByPriceAsc() :
				planRepository.findByNetworkTypeOrderByPriceAsc(networkType);

		return PlanListResponseDto.fromEntity(planList);
	}
}
