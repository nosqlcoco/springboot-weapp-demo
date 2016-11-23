package com.weapp.listener;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.weapp.common.annotation.Api;
import com.weapp.entity.auth.ApiInfo;
import com.weapp.service.ApiInfoService;
/**
 * Get == 1
 * POST == 2 
 * PUT == 4
 * DELETE == 8
 * @author xiaoqiang
 *
 */
@WebListener
@Component
public class ApiServletContextListener implements ServletContextListener {
	private static ImmutableMap<String,Integer>methodMap = ImmutableMap.of("GET", 1, "POST", 2, "PUT", 4, "DELETE", 8);
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("destory");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		WebApplicationContext wc = WebApplicationContextUtils.getRequiredWebApplicationContext(arg0.getServletContext());
		
		ApiInfoService apiInfoService = wc.getBean(ApiInfoService.class);
		apiInfoService.deleteAll();
		
		RequestMappingHandlerMapping rmhp = wc.getBean(RequestMappingHandlerMapping.class);  
		Map<RequestMappingInfo, HandlerMethod> map = rmhp.getHandlerMethods();
		List<ApiInfo>apiInfolist = Lists.newArrayList();
		ApiInfo apiInfo = null;
		Date curDate = new Date();
		for(RequestMappingInfo info : map.keySet()){
			RequestMethodsRequestCondition requestMethodsRequestCondition = info.getMethodsCondition();
			Set<RequestMethod>methods = requestMethodsRequestCondition.getMethods();
			if(methods.size() == 0){
				continue;
			}
			HandlerMethod handlerMethod = map.get(info);
			if(!handlerMethod.hasMethodAnnotation(Api.class)){
				continue;
			}
			Api api = handlerMethod.getMethodAnnotation(Api.class);
			
			apiInfo = new ApiInfo();
			apiInfo.setUri(info.getPatternsCondition().getPatterns().toArray()[0].toString());
			apiInfo.setVersion(api.version());
			apiInfo.setName(api.name());
			apiInfo.setDisabled(api.disabled());
			apiInfo.setAlgorithm(api.algorithm());
			apiInfo.setAccessLimit(api.accessLimit());
			apiInfo.setCrud(methodMap.get(methods.toArray()[0].toString()));
			apiInfo.setCreateDate(curDate);
			apiInfolist.add(apiInfo);
		}  
		apiInfoService.saveList(apiInfolist);
	}

}
