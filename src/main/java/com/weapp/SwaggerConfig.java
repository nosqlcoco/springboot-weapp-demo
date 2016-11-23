package com.weapp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Value("${server.context-path}")
	private String pathMapping;

	@Bean
	public Docket createRestApi() {
		System.out.println("http://localhost:8080" + pathMapping + "/swagger-ui.html");
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("test")
				.genericModelSubstitutes(ResponseEntity.class)
				.useDefaultResponseMessages(true)
				.forCodeGeneration(false)
				.pathMapping(pathMapping)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.weapp.controller"))
				.paths(PathSelectors.any())
				.build();
				//.apiInfo(apiInfo());
	}
//	private ApiInfo apiInfo() {
//		return new ApiInfoBuilder()
//				.title("微信小程序后台服务API")
//				.description("更多小程序知识，请关注微信公众号『柯善强的随思笔记』")
//				.termsOfServiceUrl("http://www.cnblogs.com/nosqlcoco/")
//				.contact("柯善强")
//				.version("1.0").build();
//	}
}
