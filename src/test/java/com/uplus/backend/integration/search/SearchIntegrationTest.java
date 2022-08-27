package com.uplus.backend.integration.search;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.uplus.backend.device.entity.Device;
import com.uplus.backend.device.repository.DeviceRepository;
import com.uplus.backend.plan.entity.Plan;
import com.uplus.backend.plan.repository.PlanRepository;
import java.time.LocalDateTime;
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

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
public class SearchIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private DeviceRepository deviceRepository;

	@Autowired
	private PlanRepository planRepository;

	private Plan plan1;

	private Device device1;

	@BeforeAll
	private void setup() {
		// given
		plan1 = Plan.builder()
			.name("5G 시그니처")
			.networkType(5)
			.price(130_000)
			.data("무제한")
			.voiceCall("집/이동전화 무제한")
			.message("기본제공")
			.build();
		plan1 = planRepository.save(plan1);

		device1 = Device.builder()
			.name("갤럭시 Z Fold 4 512GB")
			.serialNumber("SM-F936N513")
			.storage("512GB")
			.price(2_119_700)
			.launchedDate(LocalDateTime.now())
			.company("삼성")
			.networkType(5)
			.cpu("스냅드래곤 8+Gen1 (SM8475) (4nm, Octa-Core)")
			.display(
				"메인 : 6.7” (170.3 mm) FHD+ , Dynamic AMOLED 2X 커버 : 1.9”(48.2 mm) , Super AMOLED")
			.publicSupport(200_000)
			.additionalSupport(50_000)
			.repImageUrl(
				"https://image.lguplus.com/static/pc-contents/images/prdv//20220812-025607-814-9q8rtAhk.jpg")
			.plan(plan1)
			.build();
		device1 = deviceRepository.save(device1);

	}

	@Test
	void 검색_연관검색어_조회_테스트() throws Exception {
		// given
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("q", "갤럭시");
		params.add("network-type", String.valueOf(5));

		// when & then
		mockMvc.perform(get("/api/search/keyword").params(params))
			.andExpect(status().isOk())
			.andDo(print());
	}

	@Test
	void 검색_리스트_조회_테스트() throws Exception {
		// given
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("q", "갤럭시");
		params.add("network-type", String.valueOf(5));

		// when & then
		mockMvc.perform(get("/api/search").params(params))
			.andExpect(status().isOk())
			.andDo(print());
	}
}
