package com.uplus.backend.plan.dto;

import com.uplus.backend.plan.entity.Plan;
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

	private String name;

	private int networkType;

	private int price;

	private String data;

	private String subData;

	private String shareData;

	private String voiceCall;

	private String subVoiceCall;

	private String message;

	private String smartDevice;

	private String premiumService;

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