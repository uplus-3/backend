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

    @ApiModelProperty(name = "단말기 명", example = "아이폰 13 128GB")
    private String name;

    @ApiModelProperty(name = "시리얼번호", example = "A2633-128")
    private String serialNumber;

    @ApiModelProperty(name = "저장 용량", example = "256GB")
    private String storage;

    @ApiModelProperty(name = "정상가", example = "990000")
    private int price;

    @ApiModelProperty(name = "출시일", example = "2021-01-23")
    private Date launchedDate;

    @ApiModelProperty(name = "제조사", example = "애플")
    private String company;

    @ApiModelProperty(name = "네트워크 유형", example = "5")
    private int networkType;

    @ApiModelProperty(name = "CPU", example = "Apple A15 Bionic")
    private String cpu;

    @ApiModelProperty(name = "디스플레이", example = "6.1형 Super Retina XDR 디스플레이")
    private String display;

    @ApiModelProperty(name = "대표이미지", example = "이미지URL")
    private String repImageUrl;

    @ApiModelProperty(name = "출시 예정 여부", example = "true")
    private Boolean isLaunching;

    private List<LaunchingColorResponseDto> launchingColors;

    public static LaunchingDeviceResponseDto fromEntity(LaunchingDevice launchingDevice) {
        return LaunchingDeviceResponseDto.builder()
            .id(launchingDevice.getId())
            .name(launchingDevice.getName())
            .serialNumber(launchingDevice.getSerialNumber())
            .storage(launchingDevice.getStorage())
            .price(launchingDevice.getPrice())
            .launchedDate(launchingDevice.getLaunchedDate())
            .company(launchingDevice.getCompany())
            .networkType(launchingDevice.getNetworkType())
            .cpu(launchingDevice.getCpu())
            .display(launchingDevice.getDisplay())
            .repImageUrl(launchingDevice.getRepImageUrl())
            .isLaunching(true)
            .launchingColors(launchingDevice.getLaunchingColors().stream().map(LaunchingColorResponseDto::fromEntity).collect(
                Collectors.toList()))
            .build();
    }

}
