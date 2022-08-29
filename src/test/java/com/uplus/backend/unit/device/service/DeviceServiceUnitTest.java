package com.uplus.backend.unit.device.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import com.uplus.backend.device.dto.DeviceDetailResponseDto;
import com.uplus.backend.device.dto.DeviceListResponseDto;
import com.uplus.backend.device.dto.DeviceSelfCompResponseDto;
import com.uplus.backend.device.entity.Color;
import com.uplus.backend.device.entity.Device;
import com.uplus.backend.device.entity.Image;
import com.uplus.backend.device.entity.Tag;
import com.uplus.backend.device.repository.DeviceRepository;
import com.uplus.backend.device.service.DeviceService;
import com.uplus.backend.plan.entity.Plan;
import com.uplus.backend.plan.repository.PlanRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DeviceServiceUnitTest {

	@InjectMocks
	private DeviceService deviceService;

	@Mock
	private DeviceRepository deviceRepository;

	@Mock
	private PlanRepository planRepository;

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
		.url("이미지URL")
		.build();

	List<Image> images = List.of(image1);

	private Color color1 = Color.builder()
		.id(1L)
		.name("색상1")
		.rdb("#000000")
		.stock(1)
		.images(images)
		.build();

	List<Color> colors = List.of(color1);

	Device device1 = Device.builder()
		.id(1L)
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
		.colors(colors)
		.tags(tags)
		.build();

	@Test
	void 단말기_리스트_조회_테스트() {
		// given
		given(planRepository.findById(plan1.getId())).willReturn(Optional.of(plan1));
		given(deviceRepository.findByNetworkType(device1.getNetworkType())).willReturn(
			List.of(device1));

		// when
		DeviceListResponseDto responseDto = deviceService.getDeviceList(device1.getNetworkType(),
			plan1.getId(), 0, 24);

		// then
		assertThat(responseDto.getDevices().get(0).getName()).isEqualTo("스마트폰1");
		assertThat(responseDto.getDevices().get(0).getTags().get(0).getContent())
			.isEqualTo("최신");
		assertThat(responseDto.getDevices().get(0).getColors().get(0).getName())
			.isEqualTo("색상1");
		assertThat(responseDto.getDevices().get(0).getColors().get(0).getImages().get(0).getImageUrl())
			.isEqualTo("이미지URL");
	}

	@Test
	void 입력값에_따른_단말기_정보_조회_테스트() {
		// given
		given(planRepository.findById(plan1.getId())).willReturn(Optional.of(plan1));
		given(deviceRepository.findById(device1.getId())).willReturn(Optional.of(device1));

		// when
		DeviceDetailResponseDto responseDto = deviceService.getDeviceDetail(device1.getId(),
			plan1.getId(), 0, 24);

		// then
		assertThat(responseDto.getName()).isEqualTo("스마트폰1");
		assertThat(responseDto.getTags().get(0).getContent()).isEqualTo("최신");
		assertThat(responseDto.getColors().get(0).getName()).isEqualTo("색상1");
		assertThat(responseDto.getColors().get(0).getImages().get(0).getImageUrl())
			.isEqualTo("이미지URL");
	}

	@Test
	void 동일_기기_비교_조회_테스트() {
		// given
		given(deviceRepository.findById(device1.getId())).willReturn(Optional.of(device1));
		given(planRepository.findByNetworkType(plan1.getNetworkType())).willReturn(List.of(plan1));

		// when
		DeviceSelfCompResponseDto responseDto = deviceService.getDeviceSelfComp(device1.getId());

		// then
		assertThat(responseDto.getPlans().get(0).getName()).isEqualTo("요금제1");
	}
}
