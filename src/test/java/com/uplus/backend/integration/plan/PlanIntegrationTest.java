package com.uplus.backend.integration.plan;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.uplus.backend.plan.entity.Plan;
import com.uplus.backend.plan.repository.PlanRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * 담당자 : 윤병찬
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
public class PlanIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private PlanRepository planRepository;

	private Plan plan1;

	private Plan plan2;


	@BeforeAll
	private void setup() {
		// given
		plan1 = Plan.builder()
			.id(1L)
			.name("5G 시그니처")
			.networkType(5)
			.price(130_000)
			.data("무제한")
			.voiceCall("집/이동전화 무제한")
			.message("기본제공")
			.build();

		plan1 = planRepository.save(plan1);

		plan2 = Plan.builder()
			.id(2L)
			.name("5G 프리미어")
			.networkType(5)
			.price(90_000)
			.data("60GB")
			.voiceCall("집/이동전화 무제한")
			.message("기본제공")
			.build();

		plan2 = planRepository.save(plan2);

	}


	@Test
	void 요금제_조회_테스트() throws Exception {
		// given

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("network-type", String.valueOf(5));

		// when & then
		mockMvc.perform(get("/api/plans").params(params))
			.andExpect(status().isOk())
			.andDo(print());
	}

}
