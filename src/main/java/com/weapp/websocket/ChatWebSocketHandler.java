package com.weapp.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


/**
 * 微信小程序WebSocket
 * @author Shanqinag Ke
 * @since 2016-10-15
 */
public class ChatWebSocketHandler extends TextWebSocketHandler{
	private static final Logger logger =  LoggerFactory.getLogger(ChatWebSocketHandler.class);

	private final static List<WebSocketSession> sessions = Collections.synchronizedList(new ArrayList<WebSocketSession>());

	/**
	 * 处理接收文本
	 */
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String cont = message.getPayload();
		if(StringUtils.isEmpty(cont)){
			return;
		}
		//校验JSON格式
		if(!cont.startsWith("{") || !cont.endsWith("}")){
			return;
		}
		JSONObject json = JSON.parseObject(cont);
		if(!json.containsKey("user") || !json.containsKey("content")){
			return;
		}
		try {
			//发送消息
			sendChatMessage(json.getString("user"), json.getString("content"));
			super.handleTextMessage(session, message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		logger.debug("connect to the websocket chat success......");
		sessions.add(session);
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		if(session.isOpen()){
			session.close();
		}
		logger.debug("websocket chat connection closed......");
		sessions.remove(session);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		logger.debug("websocket chat connection closed......");
		sessions.remove(session);
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

	/**
	 * 判断用户是否在线
	 * @param userName	登录用户名
	 * @return
     */
	public boolean isUserConnected(String userName){
		if(org.springframework.util.StringUtils.isEmpty(userName)){
			return false;
		}
		for (WebSocketSession user : sessions) {
			if (user.getAttributes().get("user").equals(userName)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 发送消息
	 * @param userName 	昵称
	 * @param content	发送内容
	 */
	public static void sendChatMessage(String userName,String content){
		if(StringUtils.isEmpty(userName)){
			return;
		}
		if (content == null){
			return;
		}
		for (WebSocketSession session : sessions) {
			if (!session.getAttributes().get("user").equals(userName)) {
				if(session.isOpen()){
					try{
						Map<String,Object> retMap = new HashMap<String,Object>();
						retMap.put("user",userName);
						retMap.put("content",content);
						session.sendMessage(new TextMessage(JSON.toJSONString(retMap)));
					}catch (IOException ioe){
						ioe.printStackTrace();
					}
				}
				break;
			}
		}
	}
}
