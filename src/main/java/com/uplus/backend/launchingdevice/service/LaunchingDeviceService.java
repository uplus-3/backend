package com.uplus.backend.launchingdevice.service;

import com.uplus.backend.launchingdevice.dto.LaunchingColorCreateRequestDto;
import com.uplus.backend.launchingdevice.dto.LaunchingDeviceCreateRequestDto;
import com.uplus.backend.launchingdevice.dto.LaunchingDeviceCreateResponseDto;
import com.uplus.backend.launchingdevice.dto.LaunchingDeviceListResponseDto;
import com.uplus.backend.launchingdevice.dto.LaunchingImageCreateRequestDto;
import com.uplus.backend.launchingdevice.entity.LaunchingColor;
import com.uplus.backend.launchingdevice.entity.LaunchingDevice;
import com.uplus.backend.launchingdevice.entity.LaunchingImage;
import com.uplus.backend.launchingdevice.repository.LaunchingDeviceRepository;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 담당자 : 김수현
 */
@Service
@RequiredArgsConstructor
public class LaunchingDeviceService {

	private final LaunchingDeviceRepository launchingDeviceRepository;

	@Transactional
	public LaunchingDeviceCreateResponseDto create(LaunchingDeviceCreateRequestDto requestDto) {
		LaunchingDevice launchingDevice = LaunchingDevice.builder()
			.name(requestDto.getName())
			.serialNumber(requestDto.getSerialNumber())
			.storage(requestDto.getStorage())
			.price(requestDto.getPrice())
			.launchedDate(requestDto.getLaunchedDate())
			.company(requestDto.getCompany())
			.networkType(requestDto.getNetworkType())
			.cpu(requestDto.getCpu())
			.display(requestDto.getDisplay())
			.repImageUrl(requestDto.getRepImageUrl())
			.launchingColors(new ArrayList<>())
			.build();

		List<LaunchingColorCreateRequestDto> colorDtos = requestDto.getColors();

		for (LaunchingColorCreateRequestDto colorDto : colorDtos) {
			LaunchingColor color = LaunchingColor.builder()
				.name(colorDto.getName())
				.rgb(colorDto.getRgb())
				.launchingImages(new ArrayList<>())
				.build();

			List<LaunchingImageCreateRequestDto> imageDtos = colorDto.getImages();
			for (LaunchingImageCreateRequestDto imageDto : imageDtos) {
				LaunchingImage image = LaunchingImage.builder()
					.imageUrl(imageDto.getImageUrl())
					.build();

				color.addLaunchingImage(image);
			}

			launchingDevice.addLaunchingColor(color);
		}

		launchingDevice = launchingDeviceRepository.save(launchingDevice);

		return LaunchingDeviceCreateResponseDto.fromEntity(launchingDevice);
	}

	@Transactional
	public LaunchingDeviceListResponseDto getLaunchingDevices(int networkType) {
		List<LaunchingDevice> launchingDevices = launchingDeviceRepository.findByNetworkType(
			networkType);

		return LaunchingDeviceListResponseDto.fromEntity(launchingDevices);
	}

}
