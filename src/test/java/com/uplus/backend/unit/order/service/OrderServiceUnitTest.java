package com.uplus.backend.unit.order.service;

import com.uplus.backend.device.entity.Color;
import com.uplus.backend.device.entity.Device;
import com.uplus.backend.device.entity.Image;
import com.uplus.backend.device.entity.Tag;
import com.uplus.backend.device.repository.ColorRepository;
import com.uplus.backend.order.dto.OrderCreateRequestDto;
import com.uplus.backend.order.dto.OrderCreateResponseDto;
import com.uplus.backend.order.dto.OrderResponseDto;
import com.uplus.backend.order.entity.Order;
import com.uplus.backend.order.repository.OrderRepository;
import com.uplus.backend.order.service.OrderService;
import com.uplus.backend.plan.entity.Plan;
import com.uplus.backend.plan.repository.PlanRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class OrderServiceUnitTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ColorRepository colorRepository;

    @Mock
    private PlanRepository planRepository;

    private Image image1 = Image.builder()
            .id(1L)
            .imageUrl("https://image.lguplus.com/static/pc-contents/images/prdv//20220812-025607-814-9q8rtAhk.jpg")
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
            .tags(tags)
            .build();

    private Color color1 = Color.builder()
            .id(1L)
            .name("색상1")
            .rdb("#010101")
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
    void 주문_생성_테스트() {
        // given
        given(colorRepository.findById(color1.getId())).willReturn(Optional.of(color1));
        given(planRepository.findById(plan1.getId())).willReturn(Optional.of(plan1));
        given(orderRepository.save(any())).willReturn(order1);

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

        // when
        OrderCreateResponseDto responseDto = orderService.create(requestDto);

        // then
        verify(orderRepository).save(any(Order.class));
        assertThat(responseDto.getNumber()).isNotNull();

    }

    @Test
    void 주문_조회_테스트() {
        // given
        given(orderRepository.findByNameAndNumber(order1.getName(), order1.getNumber())).willReturn(
                Optional.of(order1));
        order1.setCreatedAt(LocalDateTime.now());
        // when
        OrderResponseDto responseDto = orderService.getByNameAndNumber(order1.getName(), order1.getNumber()
        );

        // then
        verify(orderRepository).findByNameAndNumber(anyString(), anyLong());
        assertThat(responseDto.getNumber()).isEqualTo(2208281234L);

    }


}
