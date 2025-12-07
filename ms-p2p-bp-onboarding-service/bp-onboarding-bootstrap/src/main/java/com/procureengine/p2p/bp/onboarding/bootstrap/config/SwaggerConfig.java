package com.procureengine.p2p.bp.onboarding.bootstrap.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI bpOnboardingOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("Business Partner Onboarding API")
						.description("P2P Platform BP Onboarding Microservice").version("1.0.0")
						.license(new License().name("Apache 2.0").url("http://springdoc.org")))
				.externalDocs(new ExternalDocumentation().description("System Architecture Docs")
						.url("https://company-confluence/p2p/architecture"));
	}
}
