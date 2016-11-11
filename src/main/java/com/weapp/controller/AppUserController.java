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
@RequestMapping("/api")
public class AppUserController {
	
	@Api(name=ApiConstant.GET_USER)
	@RequestMapping(name = "/user/{id}",method = RequestMethod.GET,produces="application/json")
	public Map<String, String> get(@PathVariable String id){
		ImmutableMap<String, String> map = ImmutableMap.of("id", id);
		return map;
	}
}
