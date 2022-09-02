package com.uplus.backend.integration.device;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uplus.backend.device.dto.DeviceCreateRequestDto;
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
import java.sql.Timestamp;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@SpringBootTest
@AutoConfigureMockMvc
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
			.price(59_000)
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
		device1 = deviceRepository.save(device1);

		tag1 = Tag.builder()
			.content("최신")
			.rgb("#000000")
			.device(device1)
			.build();
		tag1 = tagRepository.save(tag1);

		color1 = Color.builder()
			.name("색상1")
			.rgb("#000000")
			.stock(1)
			.device(device1)
			.build();
		color1 = colorRepository.save(color1);

		image1 = Image.builder()
			.imageUrl("이미지URL")
			.color(color1)
			.build();
		image1 = imageRepository.save(image1);
	}

	@Test
	void 단말기_생성_테스트() throws Exception {
		// given
		DeviceCreateRequestDto requestDto = DeviceCreateRequestDto.builder()
			.name("스마트폰1")
			.serialNumber("1234-5679")
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
			.planId(plan1.getId())
			.build();

		// when & then
		mockMvc.perform(post("/api/devices/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(requestDto)))
			.andExpect(status().isOk())
			.andDo(print());
	}

	@Test
	void 단말기_생성_테스트_404에러() throws Exception {
		// given
		DeviceCreateRequestDto requestDto = DeviceCreateRequestDto.builder()
			.name("스마트폰1")
			.serialNumber("1234-5679")
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
			.planId(plan1.getId() + 1)
			.build();

		// when & then
		mockMvc.perform(post("/api/devices/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(requestDto)))
			.andExpect(status().isNotFound())
			.andDo(print());
	}

	@Test
	void 네트워크별_단말기_리스트_조회_테스트() throws Exception {
		// given
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("network-type", String.valueOf(5));
		// when & then
		mockMvc.perform(get("/api/devices/").params(params))
			.andExpect(status().isOk())
			.andDo(print());
	}

	@Test
	void 네트워크별_가격_리스트_조회_테스트() throws Exception {
		// given
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("network-type", String.valueOf(5));
		params.add("discount-type", String.valueOf(1));
		params.add("installment-period", String.valueOf(24));

		// when & then
		mockMvc.perform(get("/api/devices/plans/" + plan1.getId()).params(params))
			.andExpect(status().isOk())
			.andDo(print());
	}

	@Test
	void 네트워크별_가격_리스트_조회_테스트_404에러() throws Exception {
		// given
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("network-type", String.valueOf(5));
		params.add("discount-type", String.valueOf(1));
		params.add("installment-period", String.valueOf(24));

		// when & then
		mockMvc.perform(get("/api/devices/plans/" + (plan1.getId() + 1)).params(params))
			.andExpect(status().isNotFound())
			.andDo(print());
	}

	@Test
	void 단말기_상세정보_조회_테스트() throws Exception {
		// when & then
		mockMvc.perform(get("/api/devices/" + device1.getId()))
			.andExpect(status().isOk())
			.andDo(print());
	}

	@Test
	void 단말기_상세정보_조회_테스트_404에러() throws Exception {
		// when & then
		mockMvc.perform(get("/api/devices/" + (device1.getId() + 1)))
			.andExpect(status().isNotFound())
			.andDo(print());
	}

	@Test
	void 가격_상세정보_조회_테스트() throws Exception {
		// given
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("network-type", String.valueOf(5));
		params.add("discount-type", String.valueOf(1));
		params.add("installment-period", String.valueOf(24));

		// when & then
		mockMvc.perform(
				get("/api/devices/" + device1.getId() + "/plans/" + plan1.getId()).params(params))
			.andExpect(status().isOk())
			.andDo(print());
	}

	@Test
	void 가격_상세정보_조회_테스트_404에러() throws Exception {
		// given
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("network-type", String.valueOf(5));
		params.add("discount-type", String.valueOf(1));
		params.add("installment-period", String.valueOf(24));

		// when & then
		mockMvc.perform(
				get("/api/devices/" + device1.getId() + "/plans/" + (plan1.getId() + 1)).params(params))
			.andExpect(status().isNotFound())
			.andDo(print());
	}

	@Test
	void 동일_기기_비교_조회_테스트() throws Exception {
		// when & then
		mockMvc.perform(get("/api/devices/" + device1.getId() + "/self"))
			.andExpect(status().isOk())
			.andDo(print());
	}

	@Test
	void 동일_기기_비교_조회_테스트_404에러() throws Exception {
		// when & then
		mockMvc.perform(get("/api/devices/" + (device1.getId() + 1) + "/self"))
			.andExpect(status().isNotFound())
			.andDo(print());
	}
}
