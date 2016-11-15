package com.weapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weapp.entity.auth.AppKey;
import com.weapp.repository.AppKeyRepository;

@Service
@Transactional(readOnly=true)
public class AppKeyService {
	@Autowired
	AppKeyRepository appKeyRepository;
	@Autowired
	MongoTemplate mongoTemplate;
	
	public AppKey getByAppId(String appId){
		return appKeyRepository.findByAppId(appId);
	}
	
	public void update(AppKey appKey){
		mongoTemplate.updateFirst(query(where("_id").is(appKey.getId())), Update.update("apis", appKey.getApis()), AppKey.class);
	}
}
