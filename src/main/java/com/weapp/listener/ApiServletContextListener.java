package com.weapp.listener;

import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.weapp.common.annotation.Api;
/**
 * Get == 1
 * POST == 2 
 * PUT == 4
 * DELETE == 8
 * @author xiaoqiang
 *
 */
@WebListener
public class ApiServletContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("destory");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		WebApplicationContext wc = WebApplicationContextUtils.getRequiredWebApplicationContext(arg0.getServletContext());
		RequestMappingHandlerMapping rmhp = wc.getBean(RequestMappingHandlerMapping.class);  
		Map<RequestMappingInfo, HandlerMethod> map = rmhp.getHandlerMethods();  
		for(RequestMappingInfo info : map.keySet()){  

		}  
	}

}
