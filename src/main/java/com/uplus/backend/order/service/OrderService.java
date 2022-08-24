package com.uplus.backend.order.service;

import com.uplus.backend.device.entity.Color;
import com.uplus.backend.device.repository.ColorRepository;
import com.uplus.backend.order.dto.OrderCreateRequestDto;
import com.uplus.backend.order.dto.OrderCreateResponsetDto;
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
	public OrderCreateResponsetDto create(OrderCreateRequestDto orderCreateRequestDto) {
		Color color = colorRepository.findById(orderCreateRequestDto.getColorId())
			.orElseThrow(RuntimeException::new);

		Plan plan = planRepository.findById(orderCreateRequestDto.getPlanId())
			.orElseThrow(RuntimeException::new);

		Order order = orderCreateRequestDto.toEntity(color, plan);

		orderRepository.save(order);

		return OrderCreateResponsetDto.fromEntity(order);
	}

	@Transactional(readOnly = true)
	public OrderResponseDto getByNameAndNumber(String name, Long number) {
		Order order = orderRepository.findByNameAndNumber(name, number);

		return OrderResponseDto.fromEntity(order);
	}
}
