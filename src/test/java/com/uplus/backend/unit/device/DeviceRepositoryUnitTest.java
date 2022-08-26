package com.uplus.backend.unit.device;

import static org.assertj.core.api.Assertions.assertThat;

import com.uplus.backend.device.entity.Color;
import com.uplus.backend.device.entity.Device;
import com.uplus.backend.device.entity.Image;
import com.uplus.backend.device.entity.Tag;
import com.uplus.backend.device.repository.DeviceRepository;
import com.uplus.backend.plan.entity.Plan;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Transactional;

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
			.price(59000)
			.data("11GB(다쓰면 3mbps 무제한)")
			.voiceCall("무제한")
			.message("무제한")
			.build();
		em.persist(plan1);

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
			.publicSupport(200000)
			.additionalSupport(50000)
			.repImageUrl("대표이미지URL")
			.plan(plan1)
			.build();
		em.persist(device1);

		tag1 = Tag.builder()
			.content("최신")
			.rgb("#000000")
			.device(device1)
			.build();
		em.persist(tag1);

		color1 = Color.builder()
			.name("색상1")
			.rdb("#000000")
			.stock(1)
			.device(device1)
			.build();
		em.persist(color1);

		image1 = Image.builder()
			.url("이미지URL")
			.color(color1)
			.build();
		em.persist(image1);

		em.flush();

		em.detach(device1);
	}

	@Test
	void 단말기_생성_그리고_조회_테스트() {
		// when
		List<Device> devices = deviceRepository.findAll();

		// then
		assertThat(devices.size()).isEqualTo(1);
	}

	@Test
	void 단말기_삭제_테스트() {
		// given
		em.remove(tag1);
		em.remove(image1);
		em.remove(color1);

		// when
		deviceRepository.deleteAll();
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