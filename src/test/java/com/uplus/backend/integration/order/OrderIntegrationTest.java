package com.uplus.backend.integration.order;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import com.uplus.backend.order.dto.OrderCreateRequestDto;
import com.uplus.backend.order.repository.OrderRepository;
import com.uplus.backend.plan.entity.Plan;
import com.uplus.backend.plan.repository.PlanRepository;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
public class OrderIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private DeviceRepository deviceRepository;

	@Autowired
	private PlanRepository planRepository;

	@Autowired
	private OrderRepository orderRepository;

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
			.serialNumber("SM-F936N512")
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

		tag1 = Tag.builder()
			.content("최신")
			.rgb("#D44602")
			.device(device1)
			.build();
		tag1 = tagRepository.save(tag1);

		color1 = Color.builder()
			.name("색상1")
			.rdb("#B8AAC8")
			.stock(1_000)
			.device(device1)
			.build();
		color1 = colorRepository.save(color1);

		image1 = Image.builder()
			.imageUrl(
				"https://image.lguplus.com/static/pc-contents/images/prdv//20220812-025607-814-9q8rtAhk.jpg")
			.color(color1)
			.build();
		image1 = imageRepository.save(image1);
	}

	@Test
	@Order(1)
	void 주문_생성_테스트() throws Exception {
		// given
		OrderCreateRequestDto requestDto = OrderCreateRequestDto.builder()
			.name("윤유플")
			.phoneNumber("010-1234-5678")
			.address("(03924)서울특별시 마포구 월드컵북로 416")
			.price(100_236)
			.discountType(0)
			.registrationType(0)
			.shipmentType(0)
			.installmentPeriod(24)
			.colorId(1L)
			.planId(1L)
			.build();

		// when & then
		mockMvc.perform(post("/api/orders")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(requestDto)))
			.andExpect(status().isOk())
			.andDo(print());

	}

	@Test
	@Order(2)
	void 주문_조회_테스트() throws Exception {
		// given
		com.uplus.backend.order.entity.Order order = orderRepository.findById(1L).get();
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("name", "윤유플");
		params.add("number", String.valueOf(order.getNumber()));

		// when & then
		mockMvc.perform(get("/api/orders").params(params))
			.andExpect(status().isOk())
			.andDo(print());
	}

}
