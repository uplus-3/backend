package com.uplus.backend.unit.order.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.uplus.backend.device.entity.Color;
import com.uplus.backend.device.entity.Device;
import com.uplus.backend.device.entity.Tag;
import com.uplus.backend.order.entity.Order;
import com.uplus.backend.order.repository.OrderRepository;
import com.uplus.backend.plan.entity.Plan;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

/**
 * 담당자 : 윤병찬
 */
@DataJpaTest
public class OrderRepositoryUnitTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TestEntityManager em;

    private Device device1;

    private Tag tag1;

    private Color color1;

    private Plan plan1;

    private Order order1;

    @BeforeEach
    void setup() {
        // given
        plan1 = Plan.builder()
                .name("5G 시그니처")
                .networkType(5)
                .price(130_000)
                .data("무제한")
                .voiceCall("집/이동전화 무제한")
                .message("기본제공")
                .build();
        plan1 = em.persist(plan1);

        device1 = Device.builder()
                .name("갤럭시 Z Fold 4 512GB")
                .serialNumber("SM-F936N512")
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
                .build();
        device1 = em.persist(device1);

        tag1 = Tag.builder()
                .content("최신")
                .rgb("#D44602")
                .device(device1)
                .build();
        tag1 = em.persist(tag1);

        color1 = Color.builder()
                .name("색상1")
                .rgb("#B8AAC8")
                .stock(1_000)
                .device(device1)
                .build();
        color1 = em.persist(color1);

        order1 = Order.builder()
                .number(2208281235L)
                .name("윤유플")
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

        order1 = em.persist(order1);

    }

    @Test
    void 주문_조회_테스트() {
        // when
        Order order = orderRepository.findByNameAndNumber("윤유플", 2208281235L).get();

        // then
        assertThat(order.getNumber()).isEqualTo(2208281235L);
    }

    @Test
    void 주문_존재여부_테스트() {
        // when
        Boolean orderIsExisted = orderRepository.existsByNumber(2208281235L);

        // then
        assertThat(orderIsExisted).isEqualTo(true);
    }

}

