package com.uplus.backend.plan.dto;

import com.uplus.backend.device.entity.Device;
import com.uplus.backend.global.util.DiscountUtil;
import com.uplus.backend.plan.entity.Plan;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PlanDetailResponseDto {

	private Long id;

	private String name;

	private String data;

	private String subData;

	private String shareData;

	private String voiceCall;

	private String subVoiceCall;

	private int psPrice;

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
			.psPrice(DiscountUtil.getSelfCompPrice(plan, device, 0))
			.sdPrice(DiscountUtil.getSelfCompPrice(plan, device, 1))
			.build();
	}
}
