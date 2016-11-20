package com.weapp.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.ImmutableMap;
import com.weapp.common.annotation.Api;
import com.weapp.common.constant.ApiConstant;


@RestController
public class UploadController extends BaseController{
	//文件存储路径
	@Value("${img.local.path}")
	private String imgLocalPath;
	//文件网络访问路径
	@Value("${img.host}")
	private String imgHost;
	
	/**
	 * 上传文件
	 * @param file
	 * @return
	 */
	@Api(name = ApiConstant.UPLOAD_IMAGE)
	@RequestMapping(value = "/api/v1/upload/image", method = RequestMethod.POST, produces = "application/json")
	public Map<String,Object> uploadImage(@RequestParam(required=true,value="file")MultipartFile file){
		if(null == file){
			return rtnParam(40010, null);
		}
		String random = RandomStringUtils.randomAlphabetic(16);
		String fileName = random + ".jpg";
		try {
			FileCopyUtils.copy(file.getBytes(), new File(imgLocalPath + "/", fileName));
			return rtnParam(0, ImmutableMap.of("url", imgHost + "/" + fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rtnParam(40011, null);
	}
}
