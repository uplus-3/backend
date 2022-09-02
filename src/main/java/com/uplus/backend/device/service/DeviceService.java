package com.uplus.backend.device.service;

import com.uplus.backend.device.dto.device.DeviceCreateRequestDto;
import com.uplus.backend.device.dto.device.DeviceCreateResponseDto;
import com.uplus.backend.device.dto.device.DeviceDetailResponseDto;
import com.uplus.backend.device.dto.device.DeviceListResponseDto;
import com.uplus.backend.device.dto.device.DeviceSelfCompResponseDto;
import com.uplus.backend.device.dto.device.DeviceSimpleListResponseDto;
import com.uplus.backend.device.dto.price.PriceListResponseDto;
import com.uplus.backend.device.dto.price.PriceDetailResponseDto;
import com.uplus.backend.device.entity.Device;
import com.uplus.backend.device.repository.DeviceRepository;
import com.uplus.backend.global.exception.CustomException;
import com.uplus.backend.global.exception.ErrorCode;
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
			.orElseThrow(() -> new CustomException(ErrorCode.PLAN_NO_DATA_ERROR));

		Device device = requestDto.toEntity(plan);
		device = deviceRepository.save(device);

		return DeviceCreateResponseDto.fromEntity(device);
	}

	@Transactional(readOnly = true)
	public DeviceSimpleListResponseDto getSimpleDevices() {
		List<Device> devices = deviceRepository.findAll();

		return DeviceSimpleListResponseDto.fromEntity(devices);
	}

	@Transactional(readOnly = true)
	public DeviceListResponseDto getDevices(int networkType) {
		List<Device> devices = deviceRepository.findByNetworkType(networkType);

		return DeviceListResponseDto.fromEntity(devices);
	}

	@Transactional(readOnly = true)
	public PriceListResponseDto getPrices(Long planId, int networkType, int discountType,
		int installmentPeriod) {
		List<Device> devices = deviceRepository.findByNetworkType(networkType);
		Plan plan = null;
		if (planId != -1) {
			plan = planRepository.findById(planId)
				.orElseThrow(() -> new CustomException(ErrorCode.PLAN_NO_DATA_ERROR));
		}

		return PriceListResponseDto.fromEntity(devices, plan, discountType, installmentPeriod);
	}

	@Transactional(readOnly = true)
	public DeviceDetailResponseDto getDeviceDetail(Long deviceId) {
		Device device = deviceRepository.findById(deviceId)
			.orElseThrow(() -> new CustomException(ErrorCode.DEVICE_NO_DATA_ERROR));

		return DeviceDetailResponseDto.fromEntity(device);
	}

	public PriceDetailResponseDto getPriceDetail(Long deviceId, Long planId,
		int discountType, int installmentPeriod) {
		Device device = deviceRepository.findById(deviceId)
			.orElseThrow(() -> new CustomException(ErrorCode.DEVICE_NO_DATA_ERROR));
		Plan plan = null;
		if (planId != -1) {
			plan = planRepository.findById(planId)
				.orElseThrow(() -> new CustomException(ErrorCode.PLAN_NO_DATA_ERROR));
		}

		return PriceDetailResponseDto.fromEntity(device, plan, discountType, installmentPeriod);
	}

	@Transactional(readOnly = true)
	public DeviceSelfCompResponseDto getDeviceSelfComp(Long deviceId) {
		Device device = deviceRepository.findById(deviceId)
			.orElseThrow(() -> new CustomException(ErrorCode.DEVICE_NO_DATA_ERROR));
		List<Plan> plans = planRepository.findByNetworkType(device.getNetworkType());

		return DeviceSelfCompResponseDto.fromEntity(device, plans);
	}
}