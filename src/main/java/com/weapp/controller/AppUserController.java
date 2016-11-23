package com.weapp.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.ImmutableMap;
import com.weapp.common.annotation.Api;
import com.weapp.common.constant.ApiConstant;

@RestController
@RequestMapping
public class AppUserController {
	
	@Api(name=ApiConstant.GET_USER)
	@RequestMapping(value = "/api/v1/user/{id}", method = RequestMethod.GET, produces = "application/json")
	public Map<String, String> get(@PathVariable String id){
		ImmutableMap<String, String> map = ImmutableMap.of("id", id);
		return map;
	}
	
	@Api(name=ApiConstant.POST_USER)
	@RequestMapping(value = "/api/v1/user/{id}", method = RequestMethod.POST, produces = "application/json")
	public Map<String, String> post(@PathVariable String id){
		ImmutableMap<String, String> map = ImmutableMap.of("id", id);
		return map;
	}
	
	@Api(name=ApiConstant.PUT_USER)
	@RequestMapping(value = "/api/v1/user/{id}", method = RequestMethod.PUT, produces = "application/json")
	public Map<String, String> put(@PathVariable String id){
		ImmutableMap<String, String> map = ImmutableMap.of("id", id);
		return map;
	}
	
	@Api(name=ApiConstant.DELETE_USER)
	@RequestMapping(value = "/api/v1/user/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public Map<String, String> delete(@PathVariable String id){
		ImmutableMap<String, String> map = ImmutableMap.of("id", id);
		return map;
	}
}
