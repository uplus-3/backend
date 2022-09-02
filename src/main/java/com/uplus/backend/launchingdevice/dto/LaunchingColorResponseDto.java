package com.uplus.backend.launchingdevice.dto;

import com.uplus.backend.launchingdevice.entity.LaunchingColor;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LaunchingColorResponseDto {

    private Long id;

    private String name;

    private String rgb;

    private List<LaunchingImageResponseDto> launchingImages;

    public static LaunchingColorResponseDto fromEntity(LaunchingColor launchingColor) {
        return LaunchingColorResponseDto.builder()
            .id(launchingColor.getId())
            .name(launchingColor.getName())
            .rgb(launchingColor.getRgb())
            .launchingImages(launchingColor.getLaunchingImages().stream()
                .map(LaunchingImageResponseDto::fromEntity).collect(
                    Collectors.toList()))
            .build();
    }
}
