package com.uplus.backend.unit.plan.service;

import com.uplus.backend.plan.dto.PlanListResponseDto;
import com.uplus.backend.plan.entity.Plan;
import com.uplus.backend.plan.repository.PlanRepository;
import com.uplus.backend.plan.service.PlanService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class PlanServiceUnitTest {

	@InjectMocks
	private PlanService planService;

	@Mock
	private PlanRepository planRepository;

	private Plan plan1 = Plan.builder()
			.id(1L)
			.name("5G 시그니처")
			.networkType(5)
			.price(130_000)
			.data("무제한")
			.voiceCall("집/이동전화 무제한")
			.message("기본제공")
			.build();

	@Test
	void 요금제_리스트_조회_테스트() {
		// given
		given(planRepository.findByNetworkTypeOrderByPriceAsc(plan1.getNetworkType())).willReturn(List.of(plan1));

		// when
		PlanListResponseDto responseDto = planService.findByNetworkType(plan1.getNetworkType());

		// then
		assertThat(responseDto.getPlanList().get(0).getName()).isEqualTo("5G 시그니처");
	}

}
