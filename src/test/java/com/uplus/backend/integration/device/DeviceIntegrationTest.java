package com.uplus.backend.integration.device;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uplus.backend.device.entity.Color;
import com.uplus.backend.device.entity.Device;
import com.uplus.backend.device.entity.Image;
import com.uplus.backend.device.entity.Tag;
import com.uplus.backend.device.repository.ColorRepository;
import com.uplus.backend.device.repository.DeviceRepository;
import com.uplus.backend.device.repository.ImageRepository;
import com.uplus.backend.device.repository.TagRepository;
import com.uplus.backend.plan.entity.Plan;
import com.uplus.backend.plan.repository.PlanRepository;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
public class DeviceIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private DeviceRepository deviceRepository;

	@Autowired
	private PlanRepository planRepository;

	@Autowired
	private TagRepository tagRepository;

	@Autowired
	private ColorRepository colorRepository;

	@Autowired
	private ImageRepository imageRepository;

	private Plan plan1;

	private Device device1;

	private Tag tag1;

	private Color color1;

	private Image image1;

	@BeforeAll
	private void setup() {
		// given
		plan1 = Plan.builder()
			.name("요금제1")
			.networkType(5)
			.price(59000)
			.data("11GB(다쓰면 3mbps 무제한)")
			.voiceCall("무제한")
			.message("무제한")
			.build();
		plan1 = planRepository.save(plan1);

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
			.plan(plan1)
			.build();
		device1 = deviceRepository.save(device1);

		tag1 = Tag.builder()
			.content("최신")
			.rgb("#000000")
			.device(device1)
			.build();
		tag1 = tagRepository.save(tag1);

		color1 = Color.builder()
			.name("색상1")
			.rdb("#000000")
			.stock(1)
			.device(device1)
			.build();
		color1 = colorRepository.save(color1);

		image1 = Image.builder()
			.url("이미지URL")
			.color(color1)
			.build();
		image1 = imageRepository.save(image1);
	}

	@Test
	@Order(1)
	@DisplayName("단말기 리스트 조회 테스트")
	public void getDeviceListTest() throws Exception {
		// given
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("network-type", "5");
		params.add("plan", "1");
		params.add("discount-type", "0");
		params.add("installment-type", "24");

		// when & then
		mockMvc.perform(get("/api/devices").params(params))
			.andExpect(status().isOk())
			.andDo(print());
	}

	@Test
	@Order(2)
	@DisplayName("디바이스 디테일, 주문 페이지 할인, 요금제 변화 요청, 단말기별 비교 조회 테스트")
	public void getDeviceDetailTest() throws Exception {
		// given
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("plan", "1");
		params.add("discount-type", "0");
		params.add("installment-type", "24");

		// when & then
		mockMvc.perform(get("/api/devices/1").params(params))
			.andExpect(status().isOk())
			.andDo(print());
	}

	@Test
	@Order(3)
	@DisplayName("동일 기기 비교 조회  테스트")
	public void getDeviceSelfCompTest() throws Exception {
		// when & then
		mockMvc.perform(get("/api/devices/1/self"))
			.andExpect(status().isOk())
			.andDo(print());
	}
}
