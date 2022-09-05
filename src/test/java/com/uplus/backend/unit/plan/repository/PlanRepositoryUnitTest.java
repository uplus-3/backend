package com.uplus.backend.unit.plan.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.uplus.backend.plan.entity.Plan;
import com.uplus.backend.plan.repository.PlanRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

/**
 * 담당자 : 윤병찬
 */
@DataJpaTest
public class PlanRepositoryUnitTest {

	@Autowired
	private PlanRepository planRepository;

	@Autowired
	private TestEntityManager em;

	private Plan plan5G1;

	private Plan plan4G1;

	private Plan plan5G2;

	private Plan plan4G2;

	@BeforeEach
	void setup() {
		// given
		plan5G1 = Plan.builder()
			.name("5G요금제1")
			.networkType(5)
			.price(59_000)
			.data("11GB(다쓰면 3mbps 무제한)")
			.voiceCall("무제한")
			.message("무제한")
			.build();
		plan5G1 = em.persist(plan5G1);

		plan5G2 = Plan.builder()
			.name("5G요금제2")
			.networkType(5)
			.price(49_000)
			.data("11GB(다쓰면 3mbps 무제한)")
			.voiceCall("무제한")
			.message("무제한")
			.build();
		plan5G2 = em.persist(plan5G2);

		plan4G1 = Plan.builder()
			.name("4G요금제1")
			.networkType(4)
			.price(69_000)
			.data("12GB(다쓰면 12mbps 무제한)")
			.voiceCall("무제한")
			.message("무제한")
			.build();
		plan4G1 = em.persist(plan4G1);

		plan4G2 = Plan.builder()
			.name("4G요금제2")
			.networkType(4)
			.price(109_000)
			.data("10GB(다쓰면 12mbps 무제한)")
			.voiceCall("무제한")
			.message("무제한")
			.build();
		plan4G2 = em.persist(plan4G2);

	}

	@Test
	void 네트워크유형_기준_요금제_조회_테스트() {
		// when
		List<Plan> plan5G = planRepository.findByNetworkTypeOrderByPriceAsc(5);
		List<Plan> plan4G = planRepository.findByNetworkTypeOrderByPriceAsc(4);
		// then
		assertThat(plan5G.size()).isEqualTo(2);
		assertThat(plan4G.size()).isEqualTo(2);
	}

	@Test
	void 모든_요금제_조회_테스트() {
		// when
		List<Plan> planList = planRepository.findAllByOrderByPriceAsc();
		// then
		assertThat(planList.size()).isEqualTo(4);
	}
}

