package com.uplus.backend.device.service;

import com.uplus.backend.device.dto.tag.TagCreateRequestDto;
import com.uplus.backend.device.dto.tag.TagCreateResponseDto;
import com.uplus.backend.device.entity.Device;
import com.uplus.backend.device.entity.Tag;
import com.uplus.backend.device.repository.DeviceRepository;
import com.uplus.backend.device.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TagService {

	private final TagRepository tagRepository;

	private final DeviceRepository deviceRepository;

	@Transactional
	public TagCreateResponseDto create(TagCreateRequestDto requestDto) {
		Device device = deviceRepository.findById(requestDto.getDeviceId())
			.orElseThrow(RuntimeException::new);

		Tag tag = requestDto.toEntity(device);
		tag = tagRepository.save(tag);

		return TagCreateResponseDto.fromEntity(tag);
	}
}
