package com.weapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weapp.entity.auth.ApiInfo;
import com.weapp.repository.ApiInfoRepository;

@Transactional(readOnly=false)
@Service
public class ApiInfoService {
	@Autowired
	private ApiInfoRepository apiInfoRepository;
	
	public void deleteAll(){
		apiInfoRepository.deleteAll();
	}
	
	public void saveList(Iterable<ApiInfo>list){
		apiInfoRepository.save(list);
	}
	
	public ApiInfo getByApiName(String apiName){
		return apiInfoRepository.findByName(apiName);
	}
}
