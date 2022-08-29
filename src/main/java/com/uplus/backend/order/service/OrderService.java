package com.uplus.backend.order.service;

import com.uplus.backend.device.entity.Color;
import com.uplus.backend.device.repository.ColorRepository;
import com.uplus.backend.global.util.OrderNumberUtil;
import com.uplus.backend.order.dto.OrderCreateRequestDto;
import com.uplus.backend.order.dto.OrderCreateResponseDto;
import com.uplus.backend.order.dto.OrderResponseDto;
import com.uplus.backend.order.entity.Order;
import com.uplus.backend.order.repository.OrderRepository;
import com.uplus.backend.plan.entity.Plan;
import com.uplus.backend.plan.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
			.orElseThrow(RuntimeException::new);

		Plan plan = planRepository.findById(orderCreateRequestDto.getPlanId())
			.orElseThrow(RuntimeException::new);

		Order order = orderCreateRequestDto.toEntity(color, plan, OrderNumberUtil.createOrderNumber());

		orderRepository.save(order);

		return OrderCreateResponseDto.fromEntity(order);
	}

	@Transactional(readOnly = true)
	public OrderResponseDto getByNameAndNumber(String name, Long number) {
		Order order = orderRepository.findByNameAndNumber(name, number)
				.orElseThrow(RuntimeException::new);

		return OrderResponseDto.fromEntity(order);
	}
}
