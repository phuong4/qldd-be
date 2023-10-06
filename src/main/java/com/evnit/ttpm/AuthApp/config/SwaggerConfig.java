/*
****************************************************
*
*			TTPM-EVNICT
*
****************************************************
 */
package com.evnit.ttpm.AuthApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.evnit.ttpm.AuthApp.annotation.CurrentUser;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).ignoredParameterTypes(CurrentUser.class).select()
				.apis(RequestHandlerSelectors.basePackage("com.evnit.ttpm.AuthApp"))
				.paths(PathSelectors.regex("/api.*")).build().apiInfo(metaInfo());
	}

	private ApiInfo metaInfo() {
		return new ApiInfoBuilder().description("Backend API For the Auth/User Service").title("Auth/User API")
				.version("Unreleased [WIP]").build();
	}

}
