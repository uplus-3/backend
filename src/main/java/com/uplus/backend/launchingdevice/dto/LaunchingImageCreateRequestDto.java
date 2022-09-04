package com.uplus.backend.launchingdevice.dto;

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
public class LaunchingImageCreateRequestDto {

    @NotBlank(message = "이미지 url을 입력해 주세요")
    @Size(max = 1024, message = "이미지 url은 최대 1024자로 입력해 주세요")
    @ApiModelProperty(name = "이미지 url", example = "이미지 url 해당 이미지")
    private String imageUrl;

}
