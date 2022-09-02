package com.uplus.backend.launchingdevice.service;

import com.uplus.backend.launchingdevice.dto.LaunchingDeviceListResponseDto;
import com.uplus.backend.launchingdevice.entity.LaunchingDevice;
import com.uplus.backend.launchingdevice.repository.LaunchingDeviceRepository;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LaunchingDeviceService {

    private final LaunchingDeviceRepository launchingDeviceRepository;

    @Transactional
    public LaunchingDeviceListResponseDto getLaunchingDevices(int networkType) {
        List<LaunchingDevice> launchingDevices = launchingDeviceRepository.findByNetworkType(networkType);

        return LaunchingDeviceListResponseDto.fromEntity(launchingDevices);
    }

}
