package com.uplus.backend.unit.search.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.uplus.backend.device.entity.Color;
import com.uplus.backend.device.entity.Device;
import com.uplus.backend.device.entity.Image;
import com.uplus.backend.device.entity.Tag;
import com.uplus.backend.device.repository.DeviceRepository;
import com.uplus.backend.plan.entity.Plan;
import com.uplus.backend.search.dto.SearchKeywordListResponseDto;
import com.uplus.backend.search.dto.SearchListResponseDto;
import com.uplus.backend.search.service.SearchService;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@Disabled
@ExtendWith(MockitoExtension.class)
public class SearchServiceUnitTest {

	@InjectMocks
	private SearchService searchService;

	@Mock
	private DeviceRepository deviceRepository;


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
		.id(1L)
		.content("최신")
		.rgb("#0F0F0F")
		.build();

	private List<Tag> tags = List.of(tag1);

	private Image image1 = Image.builder()
		.id(1L)
		.imageUrl(
			"https://image.lguplus.com/static/pc-contents/images/prdv//20220812-025607-814-9q8rtAhk.jpg")
		.build();

	List<Image> images = List.of(image1);

	private Color color1 = Color.builder()
		.id(1L)
		.name("색상1")
		.rgb("#010101")
		.stock(1)
		.images(images)
		.build();

	List<Color> colors = List.of(color1);

	Device device1 = Device.builder()
		.id(1L)
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
		.colors(colors)
		.tags(tags)
		.build();

	@Test
	void 검색_연관검색어_조회_테스트() {
		// given
		given(deviceRepository.findAutocompleteKeyword(
			"갤럭시*")).willReturn(List.of(device1));
		given(
			deviceRepository.findAutocompleteKeywordWithNetworkType(
				"갤럭시*", 5)).willReturn(List.of(device1));

		// when
		SearchKeywordListResponseDto responseDtoWithNoNetworkType = searchService.getSearchKeyword(
			"갤럭시", 0);
		SearchKeywordListResponseDto responseDtoWithNetworkType = searchService.getSearchKeyword(
			"갤럭시", 5);

		// then
		assertThat(responseDtoWithNoNetworkType.getSearchKeywordList().get(0).getName()).isEqualTo(
			device1.getName());
		assertThat(responseDtoWithNetworkType.getSearchKeywordList().get(0).getName()).isEqualTo(
			device1.getName());
	}

	@Test
	void 검색_리스트_조회_테스트() {
		// given
		given(deviceRepository.search(
			"갤럭시*")).willReturn(List.of(device1));
		given(deviceRepository.searchWithNetworkType(
			"갤럭시*", 5)).willReturn(List.of(device1));

		// when
		SearchListResponseDto responseDtoWithNoNetworkType = searchService.searchByKeyword("갤럭시",
			0);
		SearchListResponseDto responseDtoWithNetworkType = searchService.searchByKeyword("갤럭시", 5);

		// then
		assertThat(responseDtoWithNoNetworkType.getSearchList().get(0).getName()).isEqualTo(
			device1.getName());
		assertThat(responseDtoWithNetworkType.getSearchList().get(0).getName()).isEqualTo(
			device1.getName());
	}
}
