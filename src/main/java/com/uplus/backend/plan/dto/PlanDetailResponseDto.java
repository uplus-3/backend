package com.uplus.backend.plan.dto;

import static com.uplus.backend.global.util.PriceUtil.PUBLIC_SUPPORT_DISCOUNT_TYPE;
import static com.uplus.backend.global.util.PriceUtil.SELECT_INSTALLMENT_DISCOUNT_TYPE;

import com.uplus.backend.device.entity.Device;
import com.uplus.backend.global.util.PriceUtil;
import com.uplus.backend.plan.entity.Plan;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PlanDetailResponseDto {

	@ApiModelProperty(name = "요금제 id", example = "1")
	private Long id;

	@ApiModelProperty(name = "요금제명", example = "윤유플")
	private String name;

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

	@ApiModelProperty(name = "공시지원금할인가격(월,기기,요금제포함)", example = "134230")
	private int psPrice;

	@ApiModelProperty(name = "선택약정할인가격(월,기기,요금제포함)", example = "120020")
	private int sdPrice;

	public static PlanDetailResponseDto fromEntity(Plan plan, Device device) {
		return PlanDetailResponseDto.builder()
			.id(plan.getId())
			.name(plan.getName())
			.data(plan.getData())
			.subData(plan.getSubData())
			.shareData(plan.getShareData())
			.voiceCall(plan.getVoiceCall())
			.subVoiceCall(plan.getSubVoiceCall())
			.psPrice(PriceUtil.getSelfCompPrice(plan, device, PUBLIC_SUPPORT_DISCOUNT_TYPE))
			.sdPrice(PriceUtil.getSelfCompPrice(plan, device, SELECT_INSTALLMENT_DISCOUNT_TYPE))
			.build();
	}
}
