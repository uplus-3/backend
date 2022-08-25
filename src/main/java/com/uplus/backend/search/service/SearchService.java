package com.uplus.backend.search.service;

import com.uplus.backend.device.entity.Device;
import com.uplus.backend.device.repository.DeviceRepository;
import com.uplus.backend.search.dto.SearchListResponseDto;
import java.util.List;
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
	public SearchListResponseDto search(String query, int networkType) {
		List<Device> searchList = deviceRepository.findTop5ByNameContainingIgnoreCaseAndNetworkTypeOrderByLaunchedDateDesc(
			query, networkType);

		return SearchListResponseDto.fromEntity(searchList);
	}
}
