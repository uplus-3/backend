package com.uplus.backend.global.config;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * API 문서 관련 swagger2 설정 정의.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors.any())
			.paths(PathSelectors.ant("/api/**"))
			.build()
			.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo(
			"Uplus mobile squad REST API",
			"This is mobile, plan,  API.",
			"V1",
			"Terms of service",
			new Contact("uplus IT Agile TF team 3", "www.lguplus.co.kr",
				"intern228622@lguplus.co.jr"),
			"License of API", "www.lguplus.co.kr", Collections.emptyList());
	}
}
