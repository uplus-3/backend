package com.uplus.backend.launchingdevice.dto;

import com.uplus.backend.launchingdevice.entity.LaunchingDevice;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LaunchingDeviceListResponseDto {

    private List<LaunchingDeviceResponseDto> launchingDevices;

    public static LaunchingDeviceListResponseDto fromEntity(
        List<LaunchingDevice> launchingDevices) {
        return LaunchingDeviceListResponseDto.builder()
            .launchingDevices(
                launchingDevices.stream().map(LaunchingDeviceResponseDto::fromEntity)
                        .collect(Collectors.toList())
            )
            .build();
    }
}
