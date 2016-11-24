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
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.SocketUtils;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

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
		Map<String,Integer>map1 = new HashMap<String,Integer>();
		map1.put("calltimes", 0);
		map1.put("alltimes", 10000);

		Map<String,Integer>map2 = new HashMap<String,Integer>();
		map2.put("calltimes", 0);
		map2.put("alltimes", 10000);

		Map<String,Integer>map3 = new HashMap<String,Integer>();
		map3.put("calltimes", 0);
		map3.put("alltimes", 10000);

		Map<String,Integer>map4 = new HashMap<String,Integer>();
		map4.put("calltimes", 0);
		map4.put("alltimes", 10000);
		
		Map<String,Integer>map5 = new HashMap<String,Integer>();
		map5.put("calltimes", 0);
		map5.put("alltimes", 10000);
		
		Map<String,Integer>map6 = new HashMap<String,Integer>();
		map6.put("calltimes", 0);
		map6.put("alltimes", 10000);

		Map<String, Map<String,Integer>> apiMap = Maps.newHashMap();
		apiMap.put(ApiConstant.GET_USER, map1);
		apiMap.put(ApiConstant.POST_USER, map2);
		apiMap.put(ApiConstant.PUT_USER, map3);
		apiMap.put(ApiConstant.DELETE_USER, map4);
		apiMap.put(ApiConstant.WX_CODE, map5);
		apiMap.put(ApiConstant.WX_DECODE_USERINFO, map6);
		
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