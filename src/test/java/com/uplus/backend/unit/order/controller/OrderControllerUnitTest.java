package com.uplus.backend.unit.order.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uplus.backend.device.entity.Color;
import com.uplus.backend.device.entity.Device;
import com.uplus.backend.device.entity.Image;
import com.uplus.backend.device.entity.Tag;
import com.uplus.backend.order.controller.OrderController;
import com.uplus.backend.order.dto.OrderCreateRequestDto;
import com.uplus.backend.order.dto.OrderCreateResponseDto;
import com.uplus.backend.order.dto.OrderResponseDto;
import com.uplus.backend.order.entity.Order;
import com.uplus.backend.order.service.OrderService;
import com.uplus.backend.plan.entity.Plan;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@WebMvcTest(controllers = OrderController.class)
public class OrderControllerUnitTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private OrderService orderService;

	private Image image1 = Image.builder()
		.id(1L)
		.url(
			"https://image.lguplus.com/static/pc-contents/images/prdv//20220812-025607-814-9q8rtAhk.jpg")
		.build();

	List<Image> images = List.of(image1);


	private Plan plan1 = Plan.builder()
		.id(1L)
		.name("5G 시그니처")
		.networkType(5)
		.price(130_000)
		.data("무제한")
		.voiceCall("집/이동전화 무제한")
		.message("기본제공")
		.build();


	private Tag tag1 = Tag.builder()
		.content("최신")
		.rgb("#0F0F0F")
		.build();

	private List<Tag> tags = List.of(tag1);

	private Device device1 = Device.builder()
		.name("갤럭시 Z Fold 4 512GB")
		.serialNumber("SM-F936N513")
		.storage("512GB")
		.price(2_119_700)
		.launchedDate(Timestamp.valueOf(LocalDateTime.now()))
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
		.tags(tags)
		.build();

	private Color color1 = Color.builder()
		.id(1L)
		.name("색상1")
		.rgb("#010101")
		.stock(1)
		.images(images)
		.device(device1)
		.build();

	private Order order1 = Order.builder()
		.id(1L)
		.name("윤유플")
		.number(2208281234L)
		.phoneNumber("010-1234-5678")
		.address("(03924)서울특별시 마포구 월드컵북로 416")
		.price(100_236)
		.discountType(0)
		.registrationType(0)
		.shipmentType(0)
		.installmentPeriod(24)
		.color(color1)
		.plan(plan1)
		.build();


	@Test
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

		OrderCreateResponseDto responseDto = OrderCreateResponseDto.fromEntity(order1);

		given(orderService.create(requestDto)).willReturn(responseDto);

		// when & then
		mockMvc.perform(post("/api/orders")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(requestDto)))
			.andExpect(status().isOk())
			.andDo(print());

	}

	@Test
	void 주문_조회_테스트() throws Exception {
		// given
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		String name = "윤유플";
		Long number = order1.getNumber();
		params.add("name", name);
		params.add("number", String.valueOf(number));
		order1.setCreatedAt(LocalDateTime.now());

		OrderResponseDto responseDto = OrderResponseDto.fromEntity(order1);
		given(orderService.getByNameAndNumber(name, number)).willReturn(responseDto);

		// when & then
		mockMvc.perform(get("/api/orders").params(params))
			.andExpect(status().isOk())
			.andDo(print());
	}
}
