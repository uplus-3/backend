package com.uplus.backend.device.service;

import com.uplus.backend.device.dto.image.ImageCreateRequestDto;
import com.uplus.backend.device.dto.image.ImageCreateResponseDto;
import com.uplus.backend.device.entity.Color;
import com.uplus.backend.device.entity.Image;
import com.uplus.backend.device.repository.ColorRepository;
import com.uplus.backend.device.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 담당자 : 이일환
 */
@Service
@RequiredArgsConstructor
public class ImageService {

	private final ImageRepository imageRepository;

	private final ColorRepository colorRepository;

	@Transactional
	public ImageCreateResponseDto create(ImageCreateRequestDto requestDto) {
		Color color = colorRepository.findById(requestDto.getColorId())
			.orElseThrow(RuntimeException::new);

		Image image = requestDto.toEntity(color);
		image = imageRepository.save(image);

		return ImageCreateResponseDto.fromEntity(image);
	}
}
