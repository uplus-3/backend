package com.uplus.backend.integration.launchingdevice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uplus.backend.launchingdevice.entity.LaunchingColor;
import com.uplus.backend.launchingdevice.entity.LaunchingDevice;
import com.uplus.backend.launchingdevice.entity.LaunchingImage;
import com.uplus.backend.launchingdevice.repository.LaunchingColorRepository;
import com.uplus.backend.launchingdevice.repository.LaunchingDeviceRepository;
import com.uplus.backend.launchingdevice.repository.LaunchingImageRepository;
import java.sql.Timestamp;
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

/**
 * 담당자 : 김수현
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
public class LaunchingDeviceIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	// json to string
	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private LaunchingDeviceRepository launchingDeviceRepository;

	@Autowired
	private LaunchingColorRepository launchingColorRepository;

	@Autowired
	private LaunchingImageRepository launchingImageRepository;


	private LaunchingDevice launchingDevice1;
	private LaunchingColor launchingColor1;
	private LaunchingImage launchingImage1;

	@BeforeAll
	private void setup() {

		// given
		launchingDevice1 = LaunchingDevice.builder()
			.name("단말기 명")
			.serialNumber("시리얼 넘버")
			.storage("저장공간")
			.price(270000)
			.launchedDate(Timestamp.valueOf("2029-03-20 10:20:30.0"))
			.company("samsung")
			.networkType(5)
			.cpu("cpu 스펙")
			.display("디스플레이")
			.repImageUrl("!23123123")
			.build();

		launchingDevice1 = launchingDeviceRepository.save(launchingDevice1);

		launchingColor1 = LaunchingColor.builder()
			.name("보라 퍼플")
			.rgb("#000000")
			.launchingDevice(launchingDevice1)
			.build();

		launchingColor1 = launchingColorRepository.save(launchingColor1);

		launchingImage1 = LaunchingImage.builder()
			.imageUrl("이미지1")
			.launchingColor(launchingColor1)
			.build();

		launchingImage1 = launchingImageRepository.save(launchingImage1);
	}

	@Test
	void 네트워크별_단말기_리스트_조회_테스트() throws Exception {
		// given
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("network-type", String.valueOf(5));
		// when & then
		mockMvc.perform(get("/api/launching-devices/").params(params))
			.andExpect(status().isOk())
			.andDo(print());
	}
}
