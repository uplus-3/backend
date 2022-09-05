package com.uplus.backend.global.config;

import javax.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 담당자 : 이일환, 윤병찬
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry
			.addMapping("/**")
			.allowedOrigins("*")
			.allowedMethods("*")
			.maxAge(3600);

	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**")
			.addResourceLocations("/WEB-INF/resources/");

		registry.addResourceHandler("swagger-ui.html")
			.addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**")
			.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	public Filter requestLoggingFilter() {
		// request 로깅 설정
		CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
		loggingFilter.setIncludeClientInfo(true);
		loggingFilter.setIncludeQueryString(true);
		loggingFilter.setIncludePayload(true);
		loggingFilter.setIncludeHeaders(true);
		loggingFilter.setMaxPayloadLength(64000);
		loggingFilter.setAfterMessagePrefix("REQUEST DATA : ");
		return loggingFilter;
	}

	@Bean
	public FilterRegistrationBean loggingFilterRegistration() {
		// /api/로 시작하는 url request 로깅 설정
		FilterRegistrationBean registration = new FilterRegistrationBean(requestLoggingFilter());
		registration.addUrlPatterns("/api/*");
		return registration;
	}
}

