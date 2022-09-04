package com.uplus.backend.unit.plan.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uplus.backend.plan.controller.PlanController;
import com.uplus.backend.plan.dto.PlanListResponseDto;
import com.uplus.backend.plan.entity.Plan;
import com.uplus.backend.plan.service.PlanService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PlanController.class)
public class PlanControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private PlanService planService;

    private Plan plan1 = Plan.builder()
            .id(1L)
            .name("5G 시그니처")
            .networkType(5)
            .price(130_000)
            .data("무제한")
            .voiceCall("집/이동전화 무제한")
            .message("기본제공")
            .build();

    private Plan plan2 = Plan.builder()
            .id(2L)
            .name("5G 프리미어")
            .networkType(5)
            .price(90_000)
            .data("60GB")
            .voiceCall("집/이동전화 무제한")
            .message("기본제공")
            .build();

    @Test
    void 요금제_조회_테스트() throws Exception {
        // given
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("network-type", String.valueOf(5));

        PlanListResponseDto responseDto = PlanListResponseDto.fromEntity(List.of(plan1, plan2));

        given(planService.findByNetworkType(plan1.getNetworkType())).willReturn(responseDto);

        // when & then
        mockMvc.perform(get("/api/plans").params(params))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
