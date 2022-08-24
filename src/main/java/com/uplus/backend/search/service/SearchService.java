package com.uplus.backend.search.service;

import com.uplus.backend.device.dto.ImageResponseDto;
import com.uplus.backend.device.entity.Device;
import com.uplus.backend.device.repository.DeviceRepository;
import com.uplus.backend.search.dto.SearchResponseDto;
import com.uplus.backend.search.dto.SearchResponseListDto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchService {

	private final DeviceRepository deviceRepository;

	@Transactional(readOnly = true)
	public List<SearchResponseDto> search(String query, int networkType) {
		List<Device> deviceList = deviceRepository.findTop5ByNameContainingIgnoreCaseAndNetworkTypeOrderByLaunchedDateDesc(query, networkType);

		return SearchResponseListDto.fromEntity(deviceList);
	}
}
