package com.uplus.backend.unit.device.controller;

import static com.uplus.backend.global.util.PriceUtil.PUBLIC_SUPPORT_DISCOUNT_TYPE;
import static com.uplus.backend.global.util.PriceUtil.TWO_YEAR;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uplus.backend.device.controller.DeviceController;
import com.uplus.backend.device.dto.device.DeviceCreateRequestDto;
import com.uplus.backend.device.dto.device.DeviceCreateResponseDto;
import com.uplus.backend.device.dto.device.DeviceDetailResponseDto;
import com.uplus.backend.device.dto.device.DeviceListResponseDto;
import com.uplus.backend.device.dto.device.DeviceSelfCompResponseDto;
import com.uplus.backend.device.dto.device.DeviceSimpleListResponseDto;
import com.uplus.backend.device.dto.price.PriceDetailResponseDto;
import com.uplus.backend.device.dto.price.PriceListResponseDto;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * ????????? : ?????????
 */
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
		.name("?????????1")
		.networkType(5)
		.price(59_000)
		.data("11GB(????????? 3mbps ?????????)")
		.voiceCall("?????????")
		.message("?????????")
		.build();

	private Tag tag1 = Tag.builder()
		.id(1L)
		.content("??????")
		.rgb("#000000")
		.build();

	private List<Tag> tags = List.of(tag1);

	private Image image1 = Image.builder()
		.id(1L)
		.imageUrl("?????????URL")
		.build();

	List<Image> images = List.of(image1);

	private Color color1 = Color.builder()
		.id(1L)
		.name("??????1")
		.rgb("#000000")
		.stock(1)
		.images(images)
		.build();

	List<Color> colors = List.of(color1);

	Device device1 = Device.builder()
		.id(1L)
		.name("????????????1")
		.serialNumber("1234-5678")
		.storage("128GB")
		.price(990_000)
		.launchedDate(Timestamp.valueOf(LocalDateTime.now()))
		.company("????????????")
		.networkType(5)
		.cpu("CPU")
		.display("???????????????")
		.publicSupport(200_000)
		.additionalSupport(50_000)
		.repImageUrl("???????????????URL")
		.plan(plan1)
		.colors(colors)
		.tags(tags)
		.build();

	@Test
	void ?????????_??????_?????????() throws Exception {
		// given
		DeviceCreateRequestDto requestDto = DeviceCreateRequestDto.builder()
			.name("????????????1")
			.serialNumber("1234-5679")
			.storage("256GB")
			.price(990_000)
			.launchedDate(Timestamp.valueOf(LocalDateTime.now()))
			.company("????????????")
			.networkType(5)
			.cpu("CPU")
			.display("???????????????")
			.publicSupport(200_000)
			.additionalSupport(50_000)
			.repImageUrl("???????????????URL")
			.planId(plan1.getId())
			.build();

		DeviceCreateResponseDto responseDto = DeviceCreateResponseDto.builder()
			.id(1L)
			.build();

		given(deviceService.create(requestDto))
			.willReturn(responseDto);

		// when & then
		mockMvc.perform(post("/api/devices/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(requestDto)))
			.andExpect(status().isOk())
			.andDo(print());
	}

	@Test
	void ?????????_??????_?????????_??????_?????????() throws Exception {
		// given
		DeviceSimpleListResponseDto responseDto =
			DeviceSimpleListResponseDto.fromEntity(List.of(device1));

		given(deviceService.getSimpleDevices())
			.willReturn(responseDto);

		// when & then
		mockMvc.perform(get("/api/devices/simple"))
			.andExpect(status().isOk())
			.andDo(print());
	}

	@Test
	void ?????????_????????????_??????_?????????() throws Exception {
		// given
		DeviceDetailResponseDto responseDto = DeviceDetailResponseDto.fromEntity(device1);

		given(deviceService.getDeviceDetail(device1.getId()))
			.willReturn(responseDto);

		// when & then
		mockMvc.perform(get("/api/devices/" + device1.getId()))
			.andExpect(status().isOk())
			.andDo(print());
	}

	@Test
	void ??????_??????_??????_??????_?????????() throws Exception {
		// given
		DeviceSelfCompResponseDto responseDto =
			DeviceSelfCompResponseDto.fromEntity(device1, List.of(plan1));

		given(deviceService.getDeviceSelfComp(device1.getId()))
			.willReturn(responseDto);

		// when & then
		mockMvc.perform(get("/api/devices/" + device1.getId() + "/self"))
			.andExpect(status().isOk())
			.andDo(print());
	}

	@Test
	void ???????????????_?????????_?????????_??????_?????????() throws Exception {
		// given
		given(deviceService.getDevices(device1.getNetworkType()))
			.willReturn(DeviceListResponseDto.fromEntity(List.of(device1)));

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("network-type", String.valueOf(device1.getNetworkType()));
		// when & then
		mockMvc.perform(get("/api/devices/").params(params))
			.andExpect(status().isOk())
			.andDo(print());
	}

	@Test
	void ???????????????_??????_?????????_??????_?????????() throws Exception {
		// given
		PriceListResponseDto responseDto = PriceListResponseDto.fromEntity(
			List.of(device1), plan1, PUBLIC_SUPPORT_DISCOUNT_TYPE, TWO_YEAR);

		given(deviceService.getPrices(plan1.getId(), device1.getNetworkType(),
			PUBLIC_SUPPORT_DISCOUNT_TYPE, TWO_YEAR))
			.willReturn(responseDto);

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("network-type", String.valueOf(device1.getNetworkType()));
		params.add("discount-type", String.valueOf(PUBLIC_SUPPORT_DISCOUNT_TYPE));
		params.add("installment-period", String.valueOf(TWO_YEAR));

		// when & then
		mockMvc.perform(get("/api/devices/plans/" + plan1.getId()).params(params))
			.andExpect(status().isOk())
			.andDo(print());
	}

	@Test
	void ??????_????????????_??????_?????????() throws Exception {
		// given
		PriceDetailResponseDto responseDto = PriceDetailResponseDto.fromEntity(device1, plan1,
			PUBLIC_SUPPORT_DISCOUNT_TYPE, TWO_YEAR);

		given(deviceService.getPriceDetail(device1.getId(), plan1.getId(),
			PUBLIC_SUPPORT_DISCOUNT_TYPE, TWO_YEAR))
			.willReturn(responseDto);

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("network-type", String.valueOf(device1.getNetworkType()));
		params.add("discount-type", String.valueOf(PUBLIC_SUPPORT_DISCOUNT_TYPE));
		params.add("installment-period", String.valueOf(TWO_YEAR));

		// when & then
		mockMvc.perform(
				get("/api/devices/" + device1.getId() + "/plans/" + plan1.getId()).params(params))
			.andExpect(status().isOk())
			.andDo(print());
	}
}
