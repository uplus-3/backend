package com.uplus.backend.unit.device.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.uplus.backend.device.entity.Device;
import com.uplus.backend.device.entity.Tag;
import com.uplus.backend.device.repository.TagRepository;
import com.uplus.backend.plan.entity.Plan;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class TagRepositoryUnitTest {

	@Autowired
	private TagRepository tagRepository;

	@Autowired
	private TestEntityManager em;

	private Plan plan1;

	private Device device1;

	private Tag tag1;

	@BeforeEach
	void setup() {
		// given
		plan1 = Plan.builder()
			.name("요금제1")
			.networkType(5)
			.price(59_000)
			.data("11GB(다쓰면 3mbps 무제한)")
			.voiceCall("무제한")
			.message("무제한")
			.build();
		plan1 = em.persist(plan1);

		device1 = Device.builder()
			.name("스마트폰1")
			.serialNumber("1234-5678")
			.storage("256GB")
			.price(990_000)
			.launchedDate(LocalDateTime.now())
			.company("제조회사")
			.networkType(5)
			.cpu("CPU")
			.display("디스플레이")
			.publicSupport(200_000)
			.additionalSupport(50_000)
			.repImageUrl("대표이미지URL")
			.plan(plan1)
			.build();
		device1 = em.persist(device1);

		tag1 = Tag.builder()
			.content("최신")
			.rgb("#000000")
			.device(device1)
			.build();
		tag1 = em.persist(tag1);
	}

	@Test
	void 태그_조회_테스트() {
		// when
		Tag tag = tagRepository.findById(tag1.getId()).get();

		// then
		assertThat(tag.getContent()).isEqualTo("최신");
	}

	@Test
	void 태그_삭제_테스트() {
		// when
		tagRepository.deleteById(tag1.getId());
		List<Tag> tags = tagRepository.findAll();

		// then
		assertThat(tags.size()).isEqualTo(0);
	}
}
