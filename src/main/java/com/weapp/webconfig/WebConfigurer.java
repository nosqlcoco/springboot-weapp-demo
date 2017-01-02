/**
 *
 * @project xundaowei
 * @filename WebConfigurer.java
 * @date 2016年12月8日
 * @author KeShanqiang
 *
 */

package com.weapp.webconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class WebConfigurer extends WebMvcConfigurerAdapter {
	@Value("${img.local.path}")
	private String imgPath;
	
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String localPath = "file://" + imgPath;
		
		String osName = System.getProperty("os.name");
		//判断操作系统类型
		if(osName.toLowerCase().contains("win")){
			localPath += "/";
		}
        registry.addResourceHandler("/upload/**").addResourceLocations(localPath);  
    }  
}
