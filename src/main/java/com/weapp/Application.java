package com.weapp;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.SocketUtils;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.weapp.common.constant.ApiConstant;
import com.weapp.common.properties.WxAuth;
import com.weapp.entity.auth.AppKey;
import com.weapp.repository.AppKeyRepository;

/**
 *
 * @author Shanqiang Ke
 * @version 1.0.0
 * @blog http://nosqlcoco.cnblogs.com
 * @since 2016-10-15
 */
@SpringBootApplication
@ComponentScan(value = "com.weapp")
@EnableConfigurationProperties(value={WxAuth.class})
public class Application implements CommandLineRunner{
	@Autowired
	private AppKeyRepository repository;

	private static ImmutableMap<String, String>errorCodeMap = null;
	static {
		try {
			Properties prop = PropertiesLoaderUtils.loadAllProperties("error_code.properties");
			errorCodeMap = Maps.fromProperties(prop);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		repository.deleteAll();
		String[] apiNames = new String[]{ApiConstant.GET_USER,ApiConstant.POST_USER,ApiConstant.PUT_USER,
				ApiConstant.DELETE_USER,ApiConstant.WX_CODE,ApiConstant.WX_DECODE_USERINFO,
				ApiConstant.WX_CLUB_ARTICLES,ApiConstant.WX_CLUB_SEARCH};
		
		Map<String, Map<String,Integer>> apiMap = Maps.newHashMap();
		for(String apiName : apiNames){
			Map<String,Integer>tmpMap = new HashMap<String,Integer>();
			tmpMap.put("calltimes", 0);
			tmpMap.put("alltimes", 10000);
			apiMap.put(apiName, tmpMap);
		}
		repository.save(new AppKey("JWEJIJ345QHWJKENVKF", "sdsd", new Date(), new Date(), "1", false, apiMap));
	}
	@Bean
	public ImmutableMap<String, String> errorCodeMap(){
		return errorCodeMap;
	}

	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
		tomcat.addAdditionalTomcatConnectors(createStandardConnector());
		return tomcat;
	}
	@Bean
	public Integer port() {
		return SocketUtils.findAvailableTcpPort();
	}
	private Connector createStandardConnector() {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		connector.setScheme("http");
		connector.setPort(port());
		return connector;
	}
}