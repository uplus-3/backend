package com.uplus.backend.device.service;

import com.uplus.backend.device.dto.DeviceListResponseDto;
import com.uplus.backend.device.entity.Device;
import com.uplus.backend.device.repository.DeviceRepository;
import com.uplus.backend.plan.entity.Plan;
import com.uplus.backend.plan.repository.PlanRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DeviceService {

	private final DeviceRepository deviceRepository;

	private final PlanRepository planRepository;

	public DeviceListResponseDto getDeviceList(Long planId, int discountType) {

		// TODO : 예외처리
		Plan plan = planRepository.findById(planId)
			.orElseThrow(RuntimeException::new);

		List<Device> devices = deviceRepository.findByNetworkType(plan.getNetworkType());

		return DeviceListResponseDto.fromEntity(devices, plan, discountType);
	}
}
