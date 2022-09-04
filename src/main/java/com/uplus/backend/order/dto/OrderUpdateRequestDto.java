package com.uplus.backend.order.dto;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderUpdateRequestDto {
    @NotBlank(message = "수정할 주소를 입력해 주세요")
    @Size(max = 100, message = "주소는 최대 100자로 입력해 주세요")
    @ApiModelProperty(name = "배송 주소", example = "(12345) 서울특별시 서대문구 연희동")
    private String address;
}
