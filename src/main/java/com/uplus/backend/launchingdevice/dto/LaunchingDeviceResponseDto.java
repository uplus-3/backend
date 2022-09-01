package com.uplus.backend.launchingdevice.dto;

import com.uplus.backend.launchingdevice.entity.LaunchingDevice;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LaunchingDeviceResponseDto {

    @ApiModelProperty(name = "단말기 식별자", example = "1")
    private Long id;

    @ApiModelProperty(name = "시리얼번호", example = "A2633-128")
    private String serialNumber;

    @ApiModelProperty(name = "단말기 명", example = "아이폰 13 128GB")
    private String name;

    @ApiModelProperty(name = "정상가", example = "990000")
    private int price;

    @ApiModelProperty(name = "대표이미지", example = "이미지URL")
    private String repImageUrl;

    @ApiModelProperty(name = "출시일", example = "2021-01-23")
    private Date launchedDate;

    private List<LaunchingColorResponseDto> launchingColors;

    public static LaunchingDeviceResponseDto fromEntity(LaunchingDevice launchingDevice) {
        return LaunchingDeviceResponseDto.builder()
            .id(launchingDevice.getId())
            .serialNumber(launchingDevice.getSerialNumber())
            .name(launchingDevice.getName())
            .price(launchingDevice.getPrice())
            .repImageUrl(launchingDevice.getRepImageUrl())
            .launchedDate(launchingDevice.getLaunchedDate())
            .launchingColors(launchingDevice.getLaunchingColors().stream().map(LaunchingColorResponseDto::fromEntity).collect(
                Collectors.toList()))
            .build();
    }

}
