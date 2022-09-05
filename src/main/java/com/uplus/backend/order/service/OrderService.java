package com.uplus.backend.order.service;

import static com.uplus.backend.global.exception.ErrorCode.COLOR_NO_DATA_ERROR;
import static com.uplus.backend.global.exception.ErrorCode.NO_STOCK_ERROR;
import static com.uplus.backend.global.exception.ErrorCode.ORDER_NO_DATA_ERROR;
import static com.uplus.backend.global.exception.ErrorCode.PLAN_NO_DATA_ERROR;

import com.uplus.backend.device.entity.Color;
import com.uplus.backend.device.repository.ColorRepository;
import com.uplus.backend.global.exception.CustomException;
import com.uplus.backend.global.util.OrderNumberUtil;
import com.uplus.backend.order.dto.OrderCreateRequestDto;
import com.uplus.backend.order.dto.OrderCreateResponseDto;
import com.uplus.backend.order.dto.OrderResponseDto;
import com.uplus.backend.order.dto.OrderUpdateRequestDto;
import com.uplus.backend.order.entity.Order;
import com.uplus.backend.order.repository.OrderRepository;
import com.uplus.backend.plan.entity.Plan;
import com.uplus.backend.plan.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 담당자 : 윤병찬, 김수현
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final ColorRepository colorRepository;

    private final PlanRepository planRepository;

    @Transactional
    public OrderCreateResponseDto create(OrderCreateRequestDto orderCreateRequestDto) {
        Color color = colorRepository.findById(orderCreateRequestDto.getColorId())
            .orElseThrow(() -> new CustomException(COLOR_NO_DATA_ERROR));

        Plan plan = planRepository.findById(orderCreateRequestDto.getPlanId())
            .orElseThrow(() -> new CustomException(PLAN_NO_DATA_ERROR));

        // 재고 확인
        if (color.getStock() > 0) {
            color.setStock(color.getStock() - 1);
            colorRepository.save(color);
        } else {
            throw new CustomException(NO_STOCK_ERROR);
        }

        // 주문번호 부여
        Long orderNumber;
        boolean checkOrderNumberDuplicated;
        do {
            orderNumber = OrderNumberUtil.createOrderNumber();
            checkOrderNumberDuplicated = orderRepository.existsByNumber(
                orderNumber);
        } while (checkOrderNumberDuplicated);

        Order order = orderCreateRequestDto.toEntity(color, plan,
            orderNumber);

        orderRepository.save(order);

        return OrderCreateResponseDto.fromEntity(order);
    }

    @Transactional(readOnly = true)
    public OrderResponseDto getByNameAndNumber(String name, Long number) {
        Order order = orderRepository.findByNameAndNumber(name, number)
            .orElseThrow(() -> new CustomException(ORDER_NO_DATA_ERROR));

        return OrderResponseDto.fromEntity(order);
    }

    @Transactional
    public OrderResponseDto update(Long orderId, OrderUpdateRequestDto orderUpdateRequestDto) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new CustomException(ORDER_NO_DATA_ERROR));

        order.setAddress(orderUpdateRequestDto.getAddress());
        order = orderRepository.save(order);

        return OrderResponseDto.fromEntity(order);
    }

    @Transactional
    public void delete(Long orderId) {
        try {
            orderRepository.deleteById(orderId);
        } catch (EmptyResultDataAccessException e) {
            throw new CustomException(ORDER_NO_DATA_ERROR);
        }
    }
}
