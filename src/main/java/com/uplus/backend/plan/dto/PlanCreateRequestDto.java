package com.uplus.backend.plan.dto;

import com.uplus.backend.plan.entity.Plan;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
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

	@ApiModelProperty(name = "요금제명", example = "윤유플")
	private String name;

	@ApiModelProperty(name = "네트워크 유형", example = "4(4G) or 5(5G)")
	private int networkType;

	@ApiModelProperty(name = "월정액요금", example = "130000")
	private int price;

	@ApiModelProperty(name = "데이터", example = "무제한 or 매일 5GB")
	private String data;

	@ApiModelProperty(name = "부가 데이터", example = "다 쓰면 최대 5Mbps")
	private String subData;

	@ApiModelProperty(name = "공유 데이터", example = "60GB+60GB")
	private String shareData;

	@ApiModelProperty(name = "음성 통화", example = "집/이동전화 무제한")
	private String voiceCall;

	@ApiModelProperty(name = "부가 통화", example = "부가통화300분")
	private String subVoiceCall;

	@ApiModelProperty(name = "문자 메시지", example = "기본제공")
	private String message;

	@ApiModelProperty(name = "스마트기기", example = "윤유플")
	private String smartDevice;

	@ApiModelProperty(name = "프리미엄 서비스", example = "카테고리 선택팩")
	private String premiumService;

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