package com.uplus.backend.unit.search.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uplus.backend.device.entity.Color;
import com.uplus.backend.device.entity.Device;
import com.uplus.backend.device.entity.Image;
import com.uplus.backend.device.entity.Tag;
import com.uplus.backend.plan.entity.Plan;
import com.uplus.backend.search.controller.SearchController;
import com.uplus.backend.search.dto.SearchKeywordListResponseDto;
import com.uplus.backend.search.dto.SearchListResponseDto;
import com.uplus.backend.search.service.SearchService;

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

@WebMvcTest(controllers = SearchController.class)
public class SearchControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SearchService searchService;

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

    private Image image1 = Image.builder()
            .id(1L)
            .imageUrl(
                    "https://image.lguplus.com/static/pc-contents/images/prdv//20220812-025607-814-9q8rtAhk.jpg")
            .build();

    List<Image> images = List.of(image1);

    List<Tag> tags = List.of(tag1);

    private Color color1 = Color.builder()
            .id(1L)
            .name("색상1")
            .rgb("#010101")
            .stock(1)
            .images(images)
            .build();

    List<Color> colors = List.of(color1);

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
            .colors(colors)
            .tags(tags)
            .build();


    @Test
    void 검색_연관검색어_조회_테스트() throws Exception {
        // given
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("q", "갤럭시");
        params.add("network-type", String.valueOf(5));

        SearchKeywordListResponseDto responseDto = SearchKeywordListResponseDto.fromEntity(
                List.of(device1));

        String query = "갤럭시";

        given(searchService.getSearchKeyword(query, device1.getNetworkType())).willReturn(
                responseDto);

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
        SearchListResponseDto responseDto = SearchListResponseDto.fromEntity(List.of(device1));

        String query = "갤럭시";

        given(searchService.searchByKeyword(query, device1.getNetworkType())).willReturn(
                responseDto);

        // when & then
        mockMvc.perform(get("/api/search").params(params))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
