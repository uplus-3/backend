package com.uplus.backend.unit.order.repository;

import com.uplus.backend.device.entity.Color;
import com.uplus.backend.order.entity.Order;
import com.uplus.backend.order.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class OrderRepositoryUnitTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TestEntityManager em;

    private Color color1;

    private Order order1;

    @BeforeEach
    void setup() {
        color1 = Color.builder()
            .build();

        // given
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
                .build();
        order1 = em.persist(order1);
    }

    @Disabled
    @Test
    void 주문_조회_테스트() {
        // when
        Order order = orderRepository.findByNameAndNumber("윤유플",2208281235L).get();

        // then
        assertThat(order.getNumber()).isEqualTo(2208281235L);
    }

    @Disabled
    @Test
    void 주문_존재여부_테스트() {
        // when
        Boolean orderIsExisted = orderRepository.existsByNumber(2208281235L);

        // then
        assertThat(orderIsExisted).isEqualTo(true);
    }

}

