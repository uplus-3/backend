package com.uplus.backend.launchingdevice.dto;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LaunchingColorCreateRequestDto {

    @NotBlank(message = "색상 명을 입력해 주세요")
    @Size(max = 30, message = "색상 명은 최대 20자로 입력해 주세요")
    @ApiModelProperty(name = "색상 명", example = "보라 퍼플")
    private String name;

    @NotBlank(message = "색상 코드를 입력해 주세요")
    @Size(max = 7, message = "색상 코드는 #000000으로 입력해 주세요")
    @ApiModelProperty(name = "색상 코드", example = "#000000")
    private String rgb;

    private List<LaunchingImageCreateRequestDto> images;

}
