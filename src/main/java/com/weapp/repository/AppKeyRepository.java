package com.weapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.weapp.entity.auth.AppKey;
/**
 * appkey管理操作
 * @author xiaoqiang
 *
 */
public interface AppKeyRepository extends MongoRepository<AppKey, String> {
	
	AppKey findByAppId(String appId);
}
