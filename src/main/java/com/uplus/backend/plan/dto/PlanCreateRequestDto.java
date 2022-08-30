package com.uplus.backend.plan.dto;

import com.uplus.backend.plan.entity.Plan;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class PlanCreateRequestDto {

	@NotBlank(message = "요금제 명을 입력해 주세요")
	@Size(max = 50, message = "단말기 명은 최대 50자로 입력해 주세요")
	@ApiModelProperty(name = "요금제명", example = "윤유플")
	private String name;

	@PositiveOrZero(message = "네트워크 유형은 4(4G) 또는 5(5G)로 입력해 주세요")
	@DecimalMax(value = "9", message = "네트워크 유형은 0 ~ 9 값으로 입력해 주세요")
	@ApiModelProperty(name = "네트워크 유형", example = "4(4G) or 5(5G)")
	private int networkType;

	@Positive(message = "월정액요금은 양수를 입력해 주세요")
	@ApiModelProperty(name = "월정액요금", example = "130000")
	private int price;

	@NotBlank(message = "데이터 스펙을 입력해 주세요")
	@Size(max = 50, message = "데이터 스펙은 최대 50자로 입력해 주세요")
	@ApiModelProperty(name = "데이터", example = "무제한 or 매일 5GB")
	private String data;

	@Size(max = 50, message = "부가 데이터 스펙은 50자 이하로 입력해 주세요")
	@ApiModelProperty(name = "부가 데이터", example = "다 쓰면 최대 5Mbps")
	private String subData;

	@Size(max = 50, message = "공유 데이터 스펙은 50자 이하로 입력해 주세요")
	@ApiModelProperty(name = "공유 데이터", example = "60GB+60GB")
	private String shareData;

	@NotBlank(message = "음성 통화 스펙을 입력해 주세요")
	@Size(max = 50, message = "음성 통화 스펙은 최대 50자로 입력해 주세요")
	@ApiModelProperty(name = "음성 통화", example = "집/이동전화 무제한")
	private String voiceCall;

	@Size(max = 50, message = "부가 통화 스펙은 50자 이하로 입력해 주세요")
	@ApiModelProperty(name = "부가 통화", example = "부가통화300분")
	private String subVoiceCall;

	@NotBlank(message = "음성 통화 스펙을 입력해 주세요")
	@Size(max = 50, message = "음성 통화 스펙은 최대 50자로 입력해 주세요")
	@ApiModelProperty(name = "문자 메시지", example = "기본제공")
	private String message;

	@Size(max = 50, message = "스마트기기 스펙은 50자 이하로 입력해 주세요")
	@ApiModelProperty(name = "스마트기기", example = "윤유플")
	private String smartDevice;

	@Size(max = 50, message = "프리미엄 서비스 스펙은 50자 이하로 입력해 주세요")
	@ApiModelProperty(name = "프리미엄 서비스", example = "카테고리 선택팩")
	private String premiumService;

	@Size(max = 50, message = "기본혜택 스펙은 50자 이하로 입력해 주세요")
	@ApiModelProperty(name = "기본혜택", example = "U+모바일tv 무료")
	private String basicPromotion;

	public Plan toEntity() {
		return Plan.builder()
			.name(name)
			.networkType(networkType)
			.price(price)
			.data(data)
			.subData(subData)
			.shareData(shareData)
			.voiceCall(voiceCall)
			.subVoiceCall(subVoiceCall)
			.message(message)
			.smartDevice(smartDevice)
			.premiumService(premiumService)
			.basicPromotion(basicPromotion)
			.build();
	}
}