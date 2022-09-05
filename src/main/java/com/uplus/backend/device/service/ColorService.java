package com.uplus.backend.device.service;

import com.uplus.backend.device.dto.color.ColorCreateRequestDto;
import com.uplus.backend.device.dto.color.ColorCreateResponseDto;
import com.uplus.backend.device.entity.Color;
import com.uplus.backend.device.entity.Device;
import com.uplus.backend.device.repository.ColorRepository;
import com.uplus.backend.device.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 담당자 : 이일환
 */
@Service
@RequiredArgsConstructor
public class ColorService {

	private final ColorRepository colorRepository;

	private final DeviceRepository deviceRepository;

	@Transactional
	public ColorCreateResponseDto create(ColorCreateRequestDto requestDto) {
		Device device = deviceRepository.findById(requestDto.getDeviceId())
			.orElseThrow(RuntimeException::new);

		Color color = requestDto.toEntity(device);
		color = colorRepository.save(color);

		return ColorCreateResponseDto.fromEntity(color);
	}
}
