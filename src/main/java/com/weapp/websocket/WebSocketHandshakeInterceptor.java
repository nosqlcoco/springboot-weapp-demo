package com.weapp.websocket;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import com.alibaba.fastjson.JSON;

/**
 * websocket拦截器
 * @author Shanqinag Ke
 * @since 2016-10-15
 */
public class WebSocketHandshakeInterceptor extends HttpSessionHandshakeInterceptor implements HandshakeInterceptor{

	@Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
		
        if (request instanceof ServletServerHttpRequest) {
            //解决The extension [x-webkit-deflate-frame] is not supported问题
            if(request.getHeaders().containsKey("Sec-WebSocket-Extensions")) {
                request.getHeaders().set("Sec-WebSocket-Extensions", "permessage-deflate");
            }
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpSession session = servletRequest.getServletRequest().getSession();
            if (session != null) {
                //使用user区分WebSocketHandler，以便定向发送消息
            	HttpServletRequest req = servletRequest.getServletRequest();
                String name = req.getParameter("name");
                System.out.println(JSON.toJSONString(req.getParameterNames()));
                if(null != name && !"".equals(name)){
                	attributes.put("user", name);
                }
                
            }
        }
        return super.beforeHandshake(request, response, wsHandler, attributes);
    }

	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception exception) {
		super.afterHandshake(request, response, wsHandler, exception);
	}
    
}
