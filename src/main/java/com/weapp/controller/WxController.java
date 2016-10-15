package com.weapp.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
/** 
 * 微信小程序测试接口
 * @author Shanqinag Ke
 * @since 2016-10-15
 */
@RestController
public class WxController {
	@Value("${imgPath}")
	private String imgPath;
	@Value("${localPath}")
	private String localPath;

	/**
	 * 测试小程序wx.request接口
	 * @param name
	 * @return Map
	 */
	@RequestMapping("/test")
	public Map<String,String> test(@RequestParam(defaultValue="",required=false,value="name")String name){
		Map<String,String>map = new HashMap<String, String>();
		map.put("content", "Your name is " + name);
		return map;
	}
	/**
	 * 测试小程序wx.uploadFile接口
	 * @param file
	 * @return Map
	 * @throws IOException
	 */
	@RequestMapping("/uploadfile")
	public Map<String,Object> upload(@RequestParam(value = "file", required = false) MultipartFile file) throws IOException{
		Map<String,Object>retMap = new HashMap<String,Object>();
		if(file == null){
			retMap.put("errcode", "-1");
			retMap.put("msg", "请选择上传文件！");
			return retMap;
		}
		
		String fileName = file.getOriginalFilename() + ".png";
		
		String path = imgPath + fileName;

		BufferedOutputStream out;
		try {
			out = new BufferedOutputStream(new FileOutputStream(new File(localPath + fileName)));
			out.write(file.getBytes());
			out.flush();
			out.close();
			retMap.put("errcode", "0");
			retMap.put("msg", path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			retMap.put("errcode", "-2");
			retMap.put("msg", "文件上传异常");
		}
		return retMap;
	}
}
