package com.uplus.backend.launchingdevice.dto;

import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 담당자 : 김수현
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString
public class LaunchingDeviceCreateRequestDto {

    @NotBlank(message = "단말기 명을 입력해 주세요")
    @Size(max = 30, message = "단말기 명은 최대 30자로 입력해 주세요")
    @ApiModelProperty(name = "단말기 명", example = "아이폰 14 128GB")
    private String name;

    @NotBlank(message = "시리얼 번호를 입력해 주세요")
    @Size(max = 30, message = "시리얼 번호는 최대 30자로 입력해 주세요")
    @ApiModelProperty(name = "시리얼 번호", example = "A4633-128")
    private String serialNumber;

    @NotBlank(message = "저장 용량을 입력해 주세요")
    @Size(max = 30, message = "저장 용량은 최대 30자로 입력해 주세요")
    @ApiModelProperty(name = "저장 용량", example = "256GB")
    private String storage;

    @Positive(message = "정상가는 양수를 입력해 주세요")
    @ApiModelProperty(name = "정상가", example = "1690000")
    private int price;

    @ApiModelProperty(name = "출시일", example = "2022-09-23")
    private Date launchedDate;

    @NotBlank(message = "제조회사를 입력해 주세요")
    @Size(max = 30, message = "제조회사는 최대 30자로 입력해 주세요")
    @ApiModelProperty(name = "제조회사", example = "Apple")
    private String company;

    @PositiveOrZero(message = "네트워크 유형은 4(4G) 또는 5(5G)로 입력해 주세요")
    @DecimalMax(value = "9", message = "네트워크 유형은 0 ~ 9 값으로 입력해 주세요")
    @ApiModelProperty(name = "4G or 5G", example = "5")
    private int networkType;

    @NotBlank(message = "CPU스펙을 입력해 주세요")
    @Size(max = 100, message = "CPU스펙은 최대 100자로 입력해 주세요")
    @ApiModelProperty(name = "CPU", example = "Apple A15 Bionic")
    private String cpu;

    @NotBlank(message = "디스플레이 스펙을 입력해 주세요")
    @Size(max = 100, message = "디스플레이 스펙은 최대 100자로 입력해 주세요")
    @ApiModelProperty(name = "디스플레이", example = "6.1형 Super Retina XDR 디스플레이")
    private String display;

    @NotBlank(message = "대표이미지Url을 입력해 주세요")
    @Size(max = 1024, message = "대표이미지 url은 최대 1024자로 입력해 주세요")
    @ApiModelProperty(name = "대표이미지", example = "이미지 URL")
    private String repImageUrl;

    private List<LaunchingColorCreateRequestDto> colors;

}
