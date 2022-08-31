package com.uplus.backend.search.dto;

import static com.uplus.backend.global.util.PriceUtil.TWO_YEAR;
import static com.uplus.backend.global.util.PriceUtil.divideByMonth;
import static com.uplus.backend.global.util.PriceUtil.getTDevicePriceByDiscountType;
import static com.uplus.backend.global.util.PriceUtil.getRecommendedDiscountType;

import com.uplus.backend.device.dto.ColorResponseDto;
import com.uplus.backend.device.dto.TagResponseDto;
import com.uplus.backend.device.entity.Device;
import com.uplus.backend.plan.dto.PlanPriceResponseDto;
import com.uplus.backend.plan.entity.Plan;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SearchResponseDto {

	@ApiModelProperty(name = "단말기 ID", example = "123")
	private Long id;

	@ApiModelProperty(name = "단말기 명", example = "갤럭시 S20 중고폰")
	private String name;

	@ApiModelProperty(name = "시리얼번호", example = "A2633-128")
	private String serialNumber;

	@ApiModelProperty(name = "저장 용량", example = "128GB")
	private String storage;

	@ApiModelProperty(name = "CPU", example = "Apple A14 Bionic")
	private String cpu;

	@ApiModelProperty(name = "디스플레이", example = "15.4cm")
	private String display;

	@ApiModelProperty(name = "제조사", example = "삼성")
	private String company;

	@ApiModelProperty(name = "네트워크유형", example = "5(5G)")
	private int networkType;
	
	@ApiModelProperty(name = "단말기 정상가", example = "2000000")
	private int price;

	@ApiModelProperty(name = "단말기 대표이미지Url", example = "대표이미지 url")
	private String repImageUrl;

	@ApiModelProperty(name = "단말기 월 납부금액(정상가 / 약정 달수)", example = "120000")
	private int mPrice;

	@ApiModelProperty(name = "기기 월 납부금액(할인 적용)", example = "56420")
	private int dPrice;

	@ApiModelProperty(name = "할인유형", example = "0")
	private int discountType;

	@ApiModelProperty(name = "공시지원금", example = "200000")
	private int pSupport;

	@ApiModelProperty(name = "추가지원금", example = "50000")
	private int aSupport;

	@ApiModelProperty(name = "요금제 정보 리스트")
	private PlanPriceResponseDto plan;

	@ApiModelProperty(name = "태그 정보 리스트")
	private List<TagResponseDto> tags;

	@ApiModelProperty(name = "색상 정보 리스트")
	private List<ColorResponseDto> colors;

	public static SearchResponseDto fromEntity(Device device) {
		Plan plan = device.getPlan();
		int discountType = getRecommendedDiscountType(device, plan);

		return SearchResponseDto.builder()
			.id(device.getId())
			.name(device.getName())
			.serialNumber(device.getSerialNumber())
			.storage(device.getStorage())
			.cpu(device.getCpu())
			.display(device.getDisplay())
			.company(device.getCompany())
			.networkType(device.getNetworkType())
			.repImageUrl(device.getRepImageUrl())
			.price(device.getPrice())
			.mPrice(divideByMonth(device.getPrice(), TWO_YEAR))
			.dPrice(divideByMonth(getTDevicePriceByDiscountType(device, discountType),
				TWO_YEAR))
			.discountType(discountType)
			.plan(PlanPriceResponseDto.fromEntity(device.getPlan(), discountType))
			.tags(device.getTags().stream()
				.map(TagResponseDto::fromEntity)
				.collect(Collectors.toList()))
			.colors(device.getColors().stream()
				.map(ColorResponseDto::fromEntity)
				.collect(Collectors.toList()))
			.build();
	}
}
