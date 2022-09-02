package com.uplus.backend.unit.device.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uplus.backend.device.controller.DeviceController;
import com.uplus.backend.device.dto.device.DeviceDetailResponseDto;
import com.uplus.backend.device.dto.device.DeviceSelfCompResponseDto;
import com.uplus.backend.device.entity.Color;
import com.uplus.backend.device.entity.Device;
import com.uplus.backend.device.entity.Image;
import com.uplus.backend.device.entity.Tag;
import com.uplus.backend.device.service.DeviceService;
import com.uplus.backend.plan.entity.Plan;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@WebMvcTest(controllers = DeviceController.class)
public class DeviceControllerUnitTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private DeviceService deviceService;

	private Plan plan1 = Plan.builder()
		.id(1L)
		.name("요금제1")
		.networkType(5)
		.price(59_000)
		.data("11GB(다쓰면 3mbps 무제한)")
		.voiceCall("무제한")
		.message("무제한")
		.build();

	private Tag tag1 = Tag.builder()
		.id(1L)
		.content("최신")
		.rgb("#000000")
		.build();

	private List<Tag> tags = List.of(tag1);

	private Image image1 = Image.builder()
		.id(1L)
		.imageUrl("이미지URL")
		.build();

	List<Image> images = List.of(image1);

	private Color color1 = Color.builder()
		.id(1L)
		.name("색상1")
		.rgb("#000000")
		.stock(1)
		.images(images)
		.build();

	List<Color> colors = List.of(color1);

	Device device1 = Device.builder()
		.id(1L)
		.name("스마트폰1")
		.serialNumber("1234-5678")
		.storage("128GB")
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
		.colors(colors)
		.tags(tags)
		.build();

	@Test
	void 입력값에_따른_단말기_정보_조회_테스트() throws Exception {
		// given
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("plan", String.valueOf(plan1.getId()));
		params.add("discount-type", String.valueOf(0));
		params.add("installment-period", String.valueOf(24));

		DeviceDetailResponseDto responseDto = DeviceDetailResponseDto.fromEntity(device1);

		given(deviceService.getDeviceDetail(device1.getId()))
			.willReturn(responseDto);

		// when & then
		mockMvc.perform(get("/api/devices/1").params(params))
			.andExpect(status().isOk())
			.andDo(print());
	}

	@Test
	void 동일_기기_비교_조회_테스트() throws Exception {
		// given

		DeviceSelfCompResponseDto responseDto = DeviceSelfCompResponseDto.fromEntity(device1,
			List.of(plan1));

		given(deviceService.getDeviceSelfComp(device1.getId())).willReturn(responseDto);

		// when & then
		mockMvc.perform(get("/api/devices/1/self"))
			.andExpect(status().isOk())
			.andDo(print());
	}
}
