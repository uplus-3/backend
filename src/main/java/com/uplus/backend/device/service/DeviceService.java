package com.uplus.backend.device.service;

import com.uplus.backend.device.dto.DeviceCreateRequestDto;
import com.uplus.backend.device.dto.DeviceCreateResponseDto;
import com.uplus.backend.device.dto.DeviceDetailResponseDto;
import com.uplus.backend.device.dto.DeviceListResponseDto;
import com.uplus.backend.device.dto.DeviceSelfCompResponseDto;
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
public class DeviceService {

	private final DeviceRepository deviceRepository;

	private final PlanRepository planRepository;

	@Transactional
	public DeviceCreateResponseDto create(DeviceCreateRequestDto requestDto) {
		Plan plan = planRepository.findById(requestDto.getPlanId())
			.orElseThrow(RuntimeException::new);

		Device device = requestDto.toEntity(plan);
		device = deviceRepository.save(device);

		return DeviceCreateResponseDto.fromEntity(device);
	}

	@Transactional(readOnly = true)
	public DeviceListResponseDto getDeviceList(int networkType, Long planId, int discountType,
		int installmentPeriod) {
		Plan plan = null;

		if (planId != -1) {
			plan = planRepository.findById(planId)
				.orElseThrow(RuntimeException::new);
		}

		List<Device> devices = deviceRepository.findByNetworkType(networkType);

		return DeviceListResponseDto.fromEntity(devices, plan, discountType, installmentPeriod);
	}

	@Transactional(readOnly = true)
	public DeviceDetailResponseDto getDeviceDetail(Long deviceId, Long planId, int discountType,
		int installmentPeriod) {
		Device device = deviceRepository.findById(deviceId)
			.orElseThrow(RuntimeException::new);

		Plan plan = null;

		if (planId != -1) {
			plan = planRepository.findById(planId)
				.orElseThrow(RuntimeException::new);
		}

		return DeviceDetailResponseDto.fromEntity(device, plan, discountType, installmentPeriod);
	}

	@Transactional(readOnly = true)
	public DeviceSelfCompResponseDto getDeviceSelfComp(Long deviceId) {
		Device device = deviceRepository.findById(deviceId)
			.orElseThrow(RuntimeException::new);

		List<Plan> plans = planRepository.findByNetworkType(device.getNetworkType());

		return DeviceSelfCompResponseDto.fromEntity(device, plans);
	}
}