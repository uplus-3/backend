package com.uplus.backend.unit.device.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.uplus.backend.device.entity.Color;
import com.uplus.backend.device.entity.Device;
import com.uplus.backend.device.entity.Image;
import com.uplus.backend.device.entity.Tag;
import com.uplus.backend.device.repository.DeviceRepository;
import com.uplus.backend.plan.entity.Plan;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

/**
 * 담당자 : 이일환
 */
@DataJpaTest
public class DeviceRepositoryUnitTest {

	@Autowired
	private DeviceRepository deviceRepository;

	@Autowired
	private TestEntityManager em;

	private Plan plan1;

	private Device device1;

	private Tag tag1;

	private Color color1;

	private Image image1;

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
			.launchedDate(Timestamp.valueOf(LocalDateTime.now()))
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

		color1 = Color.builder()
			.name("색상1")
			.rgb("#000000")
			.stock(1)
			.device(device1)
			.build();
		color1 = em.persist(color1);

		image1 = Image.builder()
			.imageUrl("이미지URL")
			.color(color1)
			.build();
		image1 = em.persist(image1);
	}

	@Test
	void 단말기__조회_테스트() {
		// when
		Device device = deviceRepository.findById(device1.getId()).get();

		// then
		assertThat(device.getName()).isEqualTo("스마트폰1");
	}

	@Test
	void 단말기_삭제_테스트() {
		// given
		em.remove(tag1);
		em.remove(image1);
		em.remove(color1);

		// when
		deviceRepository.deleteById(device1.getId());
		List<Device> devices = deviceRepository.findAll();

		// then
		assertThat(devices.size()).isEqualTo(0);
	}

	@Test
	void 네트워크별_단말기_조회_테스트() {
		// when
		List<Device> devices4G = deviceRepository.findByNetworkType(4);
		List<Device> devices5G = deviceRepository.findByNetworkType(5);

		// then
		assertThat(devices4G.size()).isEqualTo(0);
		assertThat(devices5G.size()).isEqualTo(1);
	}
}
