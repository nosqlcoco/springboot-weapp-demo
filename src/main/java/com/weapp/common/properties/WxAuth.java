package com.weapp.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix = "wxapp")
@Data
public class WxAuth {
	private String appId;
	
	private String secret;
	
	private String grantType;
	
	private String sessionHost;
}
