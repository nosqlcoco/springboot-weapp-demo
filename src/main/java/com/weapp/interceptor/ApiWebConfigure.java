package com.weapp.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * API接口拦截配置
 * @author xiaoqiang
 *
 */
@Configuration
public class ApiWebConfigure extends WebMvcConfigurerAdapter {
	@Override
	public void addInterceptors(InterceptorRegistry registry){
		registry.addInterceptor(getApiInterceptor())
			.addPathPatterns("/api/**");
	}
	@Bean
	public ApiInterceptor getApiInterceptor(){
		return new ApiInterceptor();
	}
}
