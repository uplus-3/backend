package com.uplus.backend.launchingdevice.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LaunchingColorCreateRequestDto {

    private String name;
    private String rgb;

    private List<LaunchingImageCreateRequestDto> images;

}
