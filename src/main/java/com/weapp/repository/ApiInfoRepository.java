package com.weapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.weapp.entity.auth.ApiInfo;

/**
 * api管理操作
 * @author xiaoqiang
 *
 */
public interface ApiInfoRepository extends MongoRepository<ApiInfo, String> {

	ApiInfo findByName(String apiName);

}
