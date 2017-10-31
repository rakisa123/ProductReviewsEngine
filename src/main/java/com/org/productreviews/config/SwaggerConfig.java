package com.org.productreviews.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket productReviewsApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.org.productreviews.api"))//.paths(regex("/products.*"))
				.build()
				.apiInfo(metaData());
	}
	
	private ApiInfo metaData() {
	       return new ApiInfo(
	                "Product Reviews Engine REST API",
	                "Product Reviews Engine REST API",
	                "1.0",
	                "Product Reviews Service",
	                new Contact("Sagar Mummidivarapu", "", "rks_sagar@yahoo.com"),
	               "",
	                "");
	    }
}
